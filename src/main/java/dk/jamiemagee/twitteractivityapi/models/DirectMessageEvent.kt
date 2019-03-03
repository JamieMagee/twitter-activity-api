package dk.jamiemagee.twitteractivityapi.models

import com.google.gson.annotations.SerializedName

data class DirectMessageEvent(
        @SerializedName("created_timestamp")
        val createdTimestamp: String = "",
        val id: String = "",
        @SerializedName("message_create")
        val messageCreate: MessageCreate = MessageCreate(),
        val type: String = ""
)