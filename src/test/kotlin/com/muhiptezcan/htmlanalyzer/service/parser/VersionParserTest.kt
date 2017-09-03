package com.muhiptezcan.htmlanalyzer.service.parser

import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.junit.Assert.assertEquals
import org.junit.Test

class VersionParserTest {
    @Test
    @Throws(Exception::class)
    fun getHtmlVersion_html5() {
        val html = IOUtils.toString(javaClass.classLoader.getResourceAsStream("standardpage.html"))
        assertEquals("HTML 5", VersionParser().getHtmlVersion(Jsoup.parse(html)))
    }

}