package dk.jamiemagee.twitteractivityapi.models

import com.google.gson.annotations.SerializedName

data class Target(
        @SerializedName("recipient_id")
        val recipientId: Long = 0
)