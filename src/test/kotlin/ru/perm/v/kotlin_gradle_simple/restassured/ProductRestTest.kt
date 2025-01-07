package ru.perm.v.kotlin_gradle_simple.restassured

import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import ru.perm.v.kotlin_gradle_simple.restassured.CONSTS.Companion.PRODUCT_PATH
import ru.perm.v.kotlin_gradle_simple.restassured.dto.ProductDTO
import kotlin.test.assertEquals

@DisplayName("Products tests /product")
class ProductRestTest {
    val mapper = ObjectMapper()
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @Test
    @DisplayName("GET count_names of products is status=200")
    fun getNumberOfProductNames_HttpStatusIsOK() {
        val path = PRODUCT_PATH + "count_names"
        logger.info("GET "+ path)
        RestAssured.given().`when`().get(path).then()
            .statusCode(HttpStatus.SC_OK)
    }

    @Test
    @DisplayName("GET number of product NAMES RESULT is 9")
    fun getNumberOfProductNames_checkCount() {
        val path = PRODUCT_PATH + "count_names"
        logger.info("GET "+ path)
        val result = RestAssured.given().`when`().get(path).asString()
        assertEquals("9", result)
    }

    @Test
    @DisplayName("GET number of product NAMES RESULT is 9 and status=Ok (for example)")
    fun getNumberOfProductNames_checkStatus_checkCount() {
        val path = PRODUCT_PATH + "count_names"
        logger.info("GET "+ path)
        val response = RestAssured.given().`when`().get(path)

        assertEquals(HttpStatus.SC_OK, response.statusCode())
        assertEquals(9, response.asString().toInt())
    }

    @Test
    //TODO: not released
    @Disabled
    @DisplayName("Get Product by ID")
    fun getByN_Test() {
        val ID:Long = 33
        val path = PRODUCT_PATH + ID
        logger.info("GET "+ path)
        val response = RestAssured.given().`when`().get(path)

        assertEquals(HttpStatus.SC_OK, response.statusCode())

        val json=response.asString()
        assertEquals("{\"n\":33,\"name\":\"Desktop3\",\"groupDtoN\":3}", json)

        val receivedProductDto=mapper.readValue(json, ProductDTO::class.java)
        assertEquals(ProductDTO(ID, "Desktop3", 3), receivedProductDto)
    }
}