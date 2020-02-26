package com.zenika.lab5

import kotlin.test.Test
import kotlin.test.assertEquals


class SendMessageToClientTest {
    fun invokeSendMessageToClient(
        client: Client?, message: String?, email: String? = null, shouldBeInvoked: Boolean = false
    ) {
        var invoked = false
        sendMessageToClient(client, message, object : Mailer {
            override fun sendMessage(actualEmail: String, actualMessage: String) {
                invoked = true
                assertEquals(
                    message, actualMessage, "The message is not as expected:"
                )
                assertEquals(
                    email, actualEmail, "The email is not as expected:"
                )
            }
        })
        assertEquals(
            shouldBeInvoked,
            invoked,
            "The function 'sendMessage' should${if (shouldBeInvoked) "" else "n't"} be invoked"
        )
    }

    @Test
    fun `everything is ok`() {
        invokeSendMessageToClient(
            Client(PersonalInfo("bob@gmail.com")),
            "Hi Bob! We have an awesome proposition for you...",
            "bob@gmail.com",
            true
        )
    }

    @Test
    fun `no message`() {
        invokeSendMessageToClient(Client(PersonalInfo("bob@gmail.com")), null)
    }

    @Test
    fun `no email`() {
        invokeSendMessageToClient(Client(PersonalInfo(null)), "Hi Bob! We have an awesome proposition for you...")
    }

    @Test
    fun `no personal info`() {
        invokeSendMessageToClient(Client(null), "Hi Bob! We have an awesome proposition for you...")
    }

    @Test
    fun `no client`() {
        invokeSendMessageToClient(null, "Hi Bob! We have an awesome proposition for you...")
    }
}
