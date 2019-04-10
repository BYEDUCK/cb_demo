package com.example.cbDemo.domain

import com.couchbase.client.java.repository.annotation.Field
import org.springframework.data.couchbase.core.mapping.Document

@Document
class Brewery {

    @Field
    var name: String? = null

    @Field
    var city: String? = null

    @Field
    var state: String? = null

    @Field
    var code: String? = null

    @Field
    var country: String? = null

    @Field
    var description: String? = null

}