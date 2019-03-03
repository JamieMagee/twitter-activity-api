package dk.jamiemagee.twitteractivityapi.models

data class MessageData(
        val entities: Entities = Entities(),
        val text: String = ""
)