package com.example.cbDemo.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.lang.StringBuilder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoggingInterceptor : HandlerInterceptorAdapter() {

    @Autowired
    private lateinit var summaryLogDataFactory: ObjectFactory<SummaryLogData>

    private val log: Logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val summaryLogData = summaryLogDataFactory.`object`
        summaryLogData.startTime = System.currentTimeMillis()
        log.info("PRE_HANDLE" + parseRequest(request))
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        val summaryLogData = summaryLogDataFactory.`object`
        summaryLogData.endTime = System.currentTimeMillis()
        log.info("POST_HANDLE" + parseResponse(response, summaryLogData))
        super.postHandle(request, response, handler, modelAndView)
    }

    private fun parseRequest(request: HttpServletRequest): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("\nrequest:\n{\n")
                .append(String.format("\tmethod: %s,\n", request.method))
                .append(String.format("\tqueryString: %s,\n", getStringOrNAifEmpty(request.queryString)))
                .append(String.format("\tcontextPath: %s,\n", getStringOrNAifEmpty(request.contextPath)))
                .append(String.format("\trequestUrl: %s\n", getStringOrNAifEmpty(request.requestURL.toString())))
                .append("}")
        return stringBuilder.toString()
    }

    private fun parseResponse(response: HttpServletResponse, summaryLogData: SummaryLogData): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("\nresponse:\n{\n")
                .append(String.format("\tstatusCode: %s,\n", getStringOrNAifEmpty(response.status.toString())))
                .append(String.format("\telapsedTime: %s\n}", getStringOrNAifEmpty(summaryLogData.getElapsedTime())))
        return stringBuilder.toString()
    }

    private fun getStringOrNAifEmpty(string: String): String {
        return if (string.isNotEmpty()) string else "N/A"
    }

}