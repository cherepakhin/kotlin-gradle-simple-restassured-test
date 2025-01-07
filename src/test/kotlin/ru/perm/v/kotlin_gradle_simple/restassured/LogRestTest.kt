package ru.perm.v.kotlin_gradle_simple.restassured

import io.qameta.allure.Epic
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


@DisplayName("Log tests /log")
class LogRestTest {

    @Disabled
    @Test
    @Epic("REST API Log")
    @DisplayName("GET log is status=200")
    fun getLog_HttpStatusIsOK() {
        RestAssured.given().`when`().get(CONSTS.LOG_PATH).then()
            .statusCode(HttpStatus.SC_OK)
    }
}