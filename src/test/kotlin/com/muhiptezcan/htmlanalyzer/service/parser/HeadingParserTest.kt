package com.muhiptezcan.htmlanalyzer.service.parser

import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HeadingParserTest {
    @Test
    @Throws(Exception::class)
    fun getHeadings() {
        val html = IOUtils.toString(javaClass.classLoader.getResourceAsStream("standardpage.html"))
        val headings = HeadingParser().getHeadings(Jsoup.parse(html))
        assertTrue(headings.size == 3)
        assertEquals(2, (headings["H2: "] as Int).toLong())
        assertEquals(1, (headings["H1: "] as Int).toLong())
        assertEquals(1, (headings["H3: "] as Int).toLong())
    }

}