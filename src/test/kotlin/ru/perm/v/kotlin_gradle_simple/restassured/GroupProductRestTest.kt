package ru.perm.v.kotlin_gradle_simple.restassured

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.qameta.allure.Epic
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.apache.http.HttpStatus
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.perm.v.kotlin_gradle_simple.restassured.dto.GroupProductDTO
import java.io.FileOutputStream
import java.util.*
import kotlin.test.assertEquals


@DisplayName("Group products tests")
class GroupProductRestTest {

    val mapper = ObjectMapper()

    // for documentation demo
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() {
            val fileProps = FileOutputStream("./props.txt")
            val props = Properties()
            props.setProperty("CPU", "Intel i3")
            props.setProperty("RAM", "16GB")
            props.store(fileProps, "")
            fileProps.close()
        }
    }

    @Test
    @Epic("REST API getById()")
    @DisplayName("GET Request GroupProductRest.getById() is status=200")
    fun getById() {
        given().`when`().get(CONSTS.GROUP_PATH + "").then()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test
    @Epic("REST API getAll()")
    @DisplayName("GET Request GroupProductRest.all() with message is status=200")
    fun getAll_HttpStatusIsOK() {
        given().`when`().get(CONSTS.GROUP_PATH).then()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test
    @Epic("REST API getAll()")
    @DisplayName("GET Request GroupProductRest.all() check dtos")
    fun getAll_check_DTO() {
        val json: String = given().get(CONSTS.GROUP_PATH).asString()
        val groups: List<GroupProductDTO> = mapper.readValue(json, object : TypeReference<List<GroupProductDTO>>() {})
        assertEquals(6, groups.size)

        val g0 = groups.get(0)
        assertEquals(1, g0.n)
        assertEquals(GroupProductDTO(1, "IT products", true, -1), groups.get(0))
        assertEquals(GroupProductDTO(2, "Computers", true, 1), groups.get(1))
        assertEquals(GroupProductDTO(3, "Desktop Computers", false, 2), groups.get(2))
    }

    @Test
    @Epic("Example")
    @DisplayName("Example convert array to json and assert DTO")
    fun exampleConvertJsonToGroupProductDTO() {
        val response: Response = given().get(CONSTS.GROUP_PATH)
        val jsonPathEvaluator = response.jsonPath()

        val groups = jsonPathEvaluator.getList<GroupProductDTO>(".", GroupProductDTO::class.java);
        assertEquals(6, groups.size)
        assertEquals(GroupProductDTO(1, "IT products", true, -1), groups.get(0))
    }
}