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
    TODO("Implements according to Java code above")
}

data class Client(val personalInfo: PersonalInfo?)
data class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}
