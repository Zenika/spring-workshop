package com.zenika.lab5

/** Given the following Java implementation, find the best Kotlin code

public void sendMessageToClient(@Nullable Client client, @Nullable String message, @NotNull Mailer mailer) {
    if (client == null || message == null) return;

    PersonalInfo personalInfo = client.getPersonalInfo();
    if (personalInfo == null) return;

    String email = personalInfo.getEmail();
    if (email == null) return;

    mailer.sendMessage(email, message);
}
 */
fun sendMessageToClient(client: Client?, message: String?, mailer: Mailer) {
    val email = client?.personalInfo?.email
    if (email != null && message != null) mailer.sendMessage(email, message)

    // Solution with let
    // if(client != null && message != null) {
    //     client.personalInfo?.email?.let { mailer.sendMessage(it, message)}
    // }

    // Solution without if
    // val email = client?.personalInfo?.email ?: return
    // message ?: return
    // mailer.sendMessage(email, message)

    // One line solution
    // client?.personalInfo?.email?.let { email -> message?.let { mailer.sendMessage(email, message) } }
}

data class Client(val personalInfo: PersonalInfo?)
data class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}
