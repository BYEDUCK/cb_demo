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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Brewery

        if (name != other.name) return false
        if (city != other.city) return false
        if (state != other.state) return false
        if (code != other.code) return false
        if (country != other.country) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (state?.hashCode() ?: 0)
        result = 31 * result + (code?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }


}