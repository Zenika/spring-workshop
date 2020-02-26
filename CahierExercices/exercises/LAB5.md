<div class="pb"></div>

# LAB 5 - Null Safety

## Preparation

- Before starting the exercise you need to copy the following files from the LAB5 folder :
    - **SendMessageToClient.kt** in *src/main/kotlin/com/zenika/lab5*
    - **SendMessageToClientTest.kt** in *src/test/kotlin/com/zenika/lab5*

You can run the test file to execute test and see that they are all failing

## Problem

- The `sendMessageToClient()` function has already been coded in Java. Your mission is to code the same function with Kotlin.

Here's the corresponding Java code :

```java
public void sendMessageToClient(@Nullable Client client, @Nullable String message, @NotNull Mailer mailer) {
    if (client == null || message == null) return;

    PersonalInfo personalInfo = client.getPersonalInfo();
    if (personalInfo == null) return;

    String email = personalInfo.getEmail();
    if (email == null) return;

    mailer.sendMessage(email, message);
}
```

You must use Null Safety operators. For code readability, you can use only one `if`.

*Reminder : The if syntax in Kotlin*
```kotlin
// Curly braces can be ommited if single expression
if (condition) { 
    // Do something 
}  
```
