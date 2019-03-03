package dk.jamiemagee.twitteractivityapi.models

import com.google.gson.annotations.SerializedName

data class MapValueX(
        @SerializedName("created_timestamp")
        val createdTimestamp: String = "",
        val description: String = "",
        @SerializedName("followers_count")
        val followersCount: Int = 0,
        @SerializedName("friends_count")
        val friendsCount: Int = 0,
        val id: String = "",
        val location: String = "",
        val name: String = "",
        @SerializedName("profile_image_url")
        val profileImageUrl: String = "",
        @SerializedName("profile_image_url_https")
        val profileImageUrlHttps: String = "",
        val `protected`: Boolean = false,
        @SerializedName("screen_name")
        val screenName: String = "",
        @SerializedName("statuses_count")
        val statusesCount: Int = 0,
        val url: String = "",
        val verified: Boolean = false
)