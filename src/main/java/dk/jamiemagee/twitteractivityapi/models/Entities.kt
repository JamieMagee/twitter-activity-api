package dk.jamiemagee.twitteractivityapi.models

import com.google.gson.annotations.SerializedName

data class Entities(
        val hashtags: List<Any> = listOf(),
        val symbols: List<Any> = listOf(),
        val urls: List<Any> = listOf(),
        @SerializedName("user_mentions")
        val userMentions: List<Any> = listOf()
)