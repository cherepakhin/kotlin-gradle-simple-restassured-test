package ru.perm.v.kotlin_gradle_simple.restassured

import io.qameta.allure.Epic
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Echo tests")
class EchoRestTest {

    @Test
    @Epic("REST API Echo")
    @DisplayName("GET /echo/ with message. Check status is 200.")
    fun getMessage_HttpStatusIsOK() {
        val MESSAGE = "MESSAGE"
        given().`when`().get(CONSTS.ECHO_PATH + MESSAGE).then()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test
    @Epic("REST API Echo")
    @DisplayName("GET /echo/ with message. Check message.")
    fun getMessage_CheckMessage() {
        val MESSAGE = "MESSAGE"
        val response = get(CONSTS.ECHO_PATH + MESSAGE)
        val responseBody = response.asString()
        assertEquals(MESSAGE, responseBody)
    }
}