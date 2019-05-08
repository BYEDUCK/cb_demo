package com.example.cbDemo.logging

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
class SummaryLogData {

    var startTime: Long = 0L
    var endTime: Long = 0L

    fun getElapsedTime(): String {
        val elapsedTime = endTime - startTime
        return "$elapsedTime ms"
    }

}