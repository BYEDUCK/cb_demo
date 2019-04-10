package com.example.cbDemo.domain

import com.couchbase.client.java.repository.annotation.Field
import org.springframework.data.couchbase.core.mapping.Document

@Document
class Beer {

    @Field
    var name: String? = null

    @Field
    var abv: Float? = null

    @Field("brewery_id")
    var brewery: String? = null

    @Field
    var description: String? = null
}