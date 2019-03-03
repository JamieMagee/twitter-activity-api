package dk.jamiemagee.twitteractivityapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.microsoft.azure.functions.HttpMethod.GET
import com.microsoft.azure.functions.HttpMethod.POST
import com.microsoft.azure.functions.HttpRequestMessage
import com.microsoft.azure.functions.HttpResponseMessage
import com.microsoft.azure.functions.HttpStatus.NO_CONTENT
import com.microsoft.azure.functions.HttpStatus.OK
import com.microsoft.azure.functions.annotation.FunctionName
import com.microsoft.azure.functions.annotation.HttpTrigger
import dk.jamiemagee.twitteractivityapi.models.DirectMessageEvents
import dk.jamiemagee.twitteractivityapi.properties.TwitterProperties
import java.nio.charset.StandardCharsets.UTF_8
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class WebHook {

    private val CONSUMER_SECRET = TwitterProperties.consumerSecret

    @FunctionName("Webhook")
    fun run(@HttpTrigger(name = "req", methods = [GET, POST]) request: HttpRequestMessage<Optional<String>>): HttpResponseMessage {
        if (request.queryParameters["crc_token"] != null) {
            val crcToken = request.queryParameters["crc_token"]
            return request.createResponseBuilder(OK).body(calcCrc(crcToken)).build()
        }

        val event = Gson().fromJson(request.body.get(), DirectMessageEvents::class.java)
        prettyPrint(event)

        return request.createResponseBuilder(NO_CONTENT).build()
    }

    private fun prettyPrint(events: DirectMessageEvents) {
        val (apps, directMessageEvents, forUserId, users) = events
        val senderName = users.filter { it.key == directMessageEvents[0].messageCreate.senderId }
                .map { it.value.name }
        val recipientName = users.filter { it.key == directMessageEvents[0].messageCreate.target.recipientId }
                .map { it.value.name }
        val messageContent = directMessageEvents[0].messageCreate.messageData.text

        println("""
            From: $senderName
            To : $recipientName
            Message: $messageContent
        """.trimIndent())
    }

    private fun calcCrc(crcToken: String?): String {
        try {
            val sha256HMAC = Mac.getInstance("HmacSHA256")
            val secretKey = SecretKeySpec(CONSUMER_SECRET!!.toByteArray(UTF_8), "HmacSHA256")
            sha256HMAC.init(secretKey)

            val hash = Base64.getEncoder().encodeToString(sha256HMAC.doFinal(crcToken!!.toByteArray(UTF_8)))

            return GsonBuilder().disableHtmlEscaping().create().toJson(CRC(hash))
        } catch (ignored: Exception) {

        }
        return ""
    }

    private inner class CRC(hash: String) {
        @SerializedName("response_token")
        private val responseToken: String = "sha256=$hash"
    }
}
