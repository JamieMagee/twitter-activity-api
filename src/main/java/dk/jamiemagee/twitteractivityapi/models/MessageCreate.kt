package dk.jamiemagee.twitteractivityapi.models

import com.google.gson.annotations.SerializedName

data class MessageCreate(
        @SerializedName("message_data")
        val messageData: MessageData = MessageData(),
        @SerializedName("sender_id")
        val senderId: Long = 0,
        @SerializedName("source_app_id")
        val sourceAppId: String = "",
        val target: Target = Target()
)