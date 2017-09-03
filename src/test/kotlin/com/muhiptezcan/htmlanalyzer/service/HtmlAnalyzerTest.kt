package com.muhiptezcan.htmlanalyzer.service

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class HtmlAnalyzerTest {

    @Autowired lateinit var analyzer: HtmlAnalyzer
    @Test
    @Throws(Exception::class)
    fun analyze() {

        val (title, _, _, headings) = analyzer.analyze("http://www.example.com")
        assertTrue(title.isNotEmpty())
        assertTrue(headings.isNotEmpty())
    }

}