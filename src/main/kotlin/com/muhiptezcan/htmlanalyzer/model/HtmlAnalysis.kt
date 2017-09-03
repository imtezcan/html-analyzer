package com.muhiptezcan.htmlanalyzer.model

data class HtmlAnalysis (
        val title: String = "",
        val version: String = "",
        val hasLogin: Boolean = false,
        val headings: Map<String, Int>,
        val internalLinks: List<Link>,
        val externalLinks: List<Link>
)