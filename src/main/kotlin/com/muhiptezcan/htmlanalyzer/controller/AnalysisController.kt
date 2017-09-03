package com.muhiptezcan.htmlanalyzer.controller

import com.muhiptezcan.htmlanalyzer.HttpValidator
import com.muhiptezcan.htmlanalyzer.model.Link
import com.muhiptezcan.htmlanalyzer.service.HtmlAnalyzer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import java.io.IOException

@Controller
class AnalysisController {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AnalysisController::class.java)
    }

    @Autowired lateinit var htmlAnalyzer: HtmlAnalyzer
    @Autowired lateinit var httpValidator: HttpValidator

    @GetMapping("/")
    fun analyzeForm(model: ModelMap): String {
        return "main"
    }

    @GetMapping("/analyze")
    fun analyzeSubmit(@RequestParam url: String, model: Model): String {
        try {
            val result = htmlAnalyzer.analyze(url)
            model.addAttribute("result", result)
            return "result :: resultsTable"
        } catch (e: RuntimeException) {
            LOGGER.error("Caught exception!")
            return "error :: errorMessage"
        } catch (e: IOException) {
            LOGGER.error("Caught exception!")
            return "error :: errorMessage"
        }
    }

    @GetMapping("/validate")
    @ResponseBody
    fun validateHttp(@RequestParam url: String): Link {
        return httpValidator.validate(url)
    }
}
