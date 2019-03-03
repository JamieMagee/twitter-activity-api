package dk.jamiemagee.twitteractivityapi.properties

import java.io.FileInputStream
import java.util.*

object TwitterProperties {
    private val properties: Properties = Properties()

    val consumerKey: String?
    val consumerSecret: String?
    val accessToken: String?
    val accessTokenSecret: String?

    init {
        val stream = TwitterProperties::class.java.getClassLoader().getResourceAsStream("config.properties")
        properties.load(stream)

        consumerKey = properties.getProperty("consumerKey")
        consumerSecret = properties.getProperty("consumerSecret")
        accessToken = properties.getProperty("accessToken")
        accessTokenSecret = properties.getProperty("accessTokenSecret")
    }
}
