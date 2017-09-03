package com.muhiptezcan.htmlanalyzer.service.parser

import com.muhiptezcan.htmlanalyzer.model.Link
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URISyntaxException

@Service
class LinkParser {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(LinkParser::class.java)
    }

    /**
     * Get all internal links in the document. A link is an internal link if it has the same domain as baseUri.
     * @param document Document to be checked
     * *
     * @return List of Links
     */
    fun getInternalLinks(document: Document): List<Link> {
        return getLinks(document, { l -> isInternalLink(l.attr("abs:href"), document.baseUri()) })
    }

    /**
     * Get all external links in the document. A link is an external link if it has a different domain than baseUri.
     * @param document Document to be checked
     * *
     * @return List of Links
     */
    fun getExternalLinks(document: Document): List<Link> {
        return getLinks(document, { l -> !isInternalLink(l.attr("abs:href"), document.baseUri()) })
    }

    private fun getLinks(document: Document, predicate : (Element) -> Boolean): List<Link> {
        val links = document.select("a[href]")
        return links
                .filter(predicate)
                .map { Link(it.attr("abs:href"), false, "Pending") }
                .filter { it.url.trim().isNotEmpty() }
    }

    private fun isInternalLink(url: String, baseUri: String): Boolean {
        var domain : String
        try {
            domain = URI(baseUri).host
        } catch (e: URISyntaxException) {
            LOGGER.error("Could not parse domain from url: " + e.message)
            domain = baseUri
        }

        return url.contains(domain)
    }
}
