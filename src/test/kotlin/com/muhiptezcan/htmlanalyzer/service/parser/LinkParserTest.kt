package com.muhiptezcan.htmlanalyzer.service.parser

import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.junit.Assert.assertTrue
import org.junit.Test

class LinkParserTest {
    @Test
    @Throws(Exception::class)
    fun getExternalLinks() {
        val html = IOUtils.toString(javaClass.classLoader.getResourceAsStream("standardpage.html"))
        assertTrue(LinkParser().getExternalLinks(Jsoup.parse(html, "http://www.google.com")).size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun getInternalLinks() {
        val html = IOUtils.toString(javaClass.classLoader.getResourceAsStream("standardpage.html"))
        assertTrue(LinkParser().getInternalLinks(Jsoup.parse(html, "http://www.example.com")).size == 1)
    }

}