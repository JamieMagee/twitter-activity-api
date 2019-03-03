package dk.jamiemagee.twitteractivityapi.models

import com.google.gson.annotations.SerializedName

data class DirectMessageEvents(
        val apps: Map<Int, MapValue> = mapOf(),
        @SerializedName("direct_message_events")
        val directMessageEvents: List<DirectMessageEvent> = listOf(),
        @SerializedName("for_user_id")
        val forUserId: String = "",
        val users: Map<Long, MapValue> = mapOf()
)