package com.muhiptezcan.htmlanalyzer.service.parser

import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class HeadingParser {

    /**
     * Parse a document to get a map of heading level and number of headings used.
     * @param document Document to be parsed
     * *
     * @return Map of Heading level and number of headings used
     */
    fun getHeadings(document: Document): Map<String, Int> {
        val headings = LinkedHashMap<String, Int>()
        for (i in 1..6) {
            val numberOfHeadings = document.body().getElementsByTag("H$i").size
            if (numberOfHeadings > 0) {
                headings.put("H$i: ", numberOfHeadings)
            }
        }
        return headings
    }
}
