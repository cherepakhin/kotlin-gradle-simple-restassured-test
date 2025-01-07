package ru.perm.v.kotlin_gradle_simple.restassured

class CONSTS {
    companion object {
        val IP = System.getenv("KOTLIN_GRADLE_SIMPLE_IP") ?: "127.0.0.1:8780"
        val HOST = "http://"+ IP +"/api"
        val ECHO_PATH = HOST + "/echo/"
        val GROUP_PATH = HOST + "/group_product/"
        val PRODUCT_PATH = HOST + "/product/"
        val LOG_PATH = HOST + "/log/"
    }
}