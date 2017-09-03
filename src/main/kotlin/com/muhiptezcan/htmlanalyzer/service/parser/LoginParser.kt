package com.muhiptezcan.htmlanalyzer.service.parser

import org.jsoup.nodes.Document
import org.jsoup.nodes.FormElement
import org.springframework.stereotype.Service

@Service
class LoginParser {

    /**
     * Traverse all forms in a Document to find out if any of them is a login form.
     * A form is a login form if it contains one text or email field, one password field and at least one submit button.
     * @param document Document to be checked for login forms
     * *
     * @return True if the Document contains a login form. False otherwise.
     */
    fun hasLogin(document: Document): Boolean {
        val forms = document.getElementsByTag("form")
        return forms.forms()
                .filter(this::formHasLogin)
                .any()
    }

    private fun formHasLogin(it: FormElement): Boolean {
        val texts = it.select("input[type=text]").size
        val emails = it.select("input[type=email]").size
        val passwords = it.select("input[type=password]").size
        val submits = it.select("input[type=submit]").size
        val buttons = it.select("button[type=submit]").size

        return (texts == 1 || emails == 1) && passwords == 1 && (submits > 0 || buttons > 0)
    }
}
