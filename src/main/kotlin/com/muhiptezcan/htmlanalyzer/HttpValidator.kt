package com.muhiptezcan.htmlanalyzer

import com.muhiptezcan.htmlanalyzer.model.Link
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

@Service
class HttpValidator {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(HttpValidator::class.java)
    }

    /**
     * Check if a URL is working or not. Working means it will return HTTP Status OK (200) or will contain a redirection
     * which in turn should return OK.
     * @param url URL to be checked
     * *
     * @return Link object with url, status and reason for any error
     */
    fun validate(url: String): Link {
        val link: Link
        try {
            val urlObject = URL(url)
            val urlConnection = urlObject.openConnection()
            if (urlConnection is HttpURLConnection) {
                val connection = urlConnection
                connection.requestMethod = "GET"
                val responseCode = connection.responseCode
                link = linkFromStatus(responseCode, url, connection)
            } else {
                // The URL is not HTTP. It can be ftp, mailto etc. For these cases, we return false with unknown reason
                link = Link(url, false, "Unknown")
            }
        } catch (e: IOException) {
            LOGGER.error(e.message)
            return Link(url, false, "Unknown")
        }

        return link
    }

    private fun linkFromStatus(responseCode: Int, url: String, connection: HttpURLConnection): Link {
        val link : Link
        if (responseCode == HttpStatus.OK.value()) {
            link = Link(url, true, "")
        } else if (isRedirected(responseCode)) {
            link = handleRedirection(connection, url)
        } else {
            link = Link(url, false, connection.responseMessage)
        }
        return link
    }

    private fun handleRedirection(connection: URLConnection, url: String): Link {
        // Try once more in case of redirection
        val newUrl = connection.getHeaderField("Location")
        val newUrlObject = URL(newUrl)
        val redirectedConnection = newUrlObject.openConnection() as HttpURLConnection
        redirectedConnection.requestMethod = "GET"

        val newResponseCode = redirectedConnection.responseCode
        if (newResponseCode == HttpStatus.OK.value()) {
            return Link(url, true, "")
        } else {
            return Link(url, false, redirectedConnection.responseMessage)
        }
    }

    private fun isRedirected(responseCode: Int): Boolean {
        return responseCode == HttpStatus.PERMANENT_REDIRECT.value()
                || responseCode == HttpStatus.TEMPORARY_REDIRECT.value()
                || responseCode == HttpStatus.MOVED_PERMANENTLY.value()
                || responseCode == HttpStatus.FOUND.value()
                || responseCode == HttpStatus.SEE_OTHER.value()
    }
}
