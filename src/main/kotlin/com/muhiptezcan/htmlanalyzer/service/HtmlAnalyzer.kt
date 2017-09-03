package com.muhiptezcan.htmlanalyzer.service

import com.muhiptezcan.htmlanalyzer.model.HtmlAnalysis
import com.muhiptezcan.htmlanalyzer.service.parser.HeadingParser
import com.muhiptezcan.htmlanalyzer.service.parser.LinkParser
import com.muhiptezcan.htmlanalyzer.service.parser.LoginParser
import com.muhiptezcan.htmlanalyzer.service.parser.VersionParser
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.io.IOException

@Service
class HtmlAnalyzer {

    @Autowired lateinit var versionUtil: VersionParser
    @Autowired lateinit var loginUtil: LoginParser
    @Autowired lateinit var headingUtil: HeadingParser
    @Autowired lateinit var linkUtil: LinkParser

    /**
     * Create an HtmlAnalysis with title, version, login, headings and links.
     * @param url URL of the Html page to be parsed
     * *
     * @return HtmlAnalysis object
     * *
     * @throws IOException in case a URL could not be parsed properly
     */
    @Throws(IOException::class)
    fun analyze(url: String): HtmlAnalysis {
        val document = Jsoup.connect(url).get()

        return HtmlAnalysis(
                title = document.title(),
                version = versionUtil.getHtmlVersion(document),
                hasLogin = loginUtil.hasLogin(document),
                headings = headingUtil.getHeadings(document),
                externalLinks = linkUtil.getExternalLinks(document),
                internalLinks = linkUtil.getInternalLinks(document)
        )
    }
}
