package com.muhiptezcan.htmlanalyzer.service.parser

import org.jsoup.nodes.Document
import org.jsoup.nodes.DocumentType
import org.springframework.stereotype.Service

@Service
class VersionParser {

    /**
     * Parse DOCTYPE to find out the document's HTML version
     * @param document Document to be checked
     * *
     * @return DOCTYPE value or "HTML 5"
     */
    fun getHtmlVersion(document: Document): String = document.childNodes()
            .filter { it is DocumentType }
            .map { it as DocumentType }
            .map { if ("" == it.attr("publicid")) "HTML 5" else it.attr("publicid") }
            .joinToString { it }
}
