package com.muhiptezcan.htmlanalyzer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class HtmlanalyzerApplication {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(HtmlanalyzerApplication::class.java, *args)
        }
    }
}
