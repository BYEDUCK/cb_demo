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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Beer

        if (name != other.name) return false
        if (abv != other.abv) return false
        if (brewery != other.brewery) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (abv?.hashCode() ?: 0)
        result = 31 * result + (brewery?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }


}