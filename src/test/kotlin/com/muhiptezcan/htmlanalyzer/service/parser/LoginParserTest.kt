package com.muhiptezcan.htmlanalyzer.service.parser

import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoginParserTest {
    @Test
    @Throws(Exception::class)
    fun hasLogin() {
        val form = IOUtils.toString(javaClass.classLoader.getResourceAsStream("loginform.html"))
        assertTrue(LoginParser().hasLogin(Jsoup.parseBodyFragment(form)))
    }

    @Test
    @Throws(Exception::class)
    fun hasLogin_false() {
        val form = IOUtils.toString(javaClass.classLoader.getResourceAsStream("standardpage.html"))
        assertFalse(LoginParser().hasLogin(Jsoup.parseBodyFragment(form)))
    }

}