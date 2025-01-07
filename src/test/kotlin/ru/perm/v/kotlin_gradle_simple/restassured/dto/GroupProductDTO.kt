package ru.perm.v.kotlin_gradle_simple.restassured.dto

class GroupProductDTO(
    val n: Long = -1,
    val name: String = "",
    val haveChilds: Boolean = false,
    val parentN: Long = -1
) {

    override fun toString(): String {
        return "GroupProductDTO(n=$n, name='$name', parentN=$parentN, haveChilds=$haveChilds)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GroupProductDTO

        if (n != other.n) return false
        if (name != other.name) return false
        if (parentN != other.parentN) return false
        if (haveChilds != other.haveChilds) return false

        return true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + parentN.hashCode()
        result = 31 * result + haveChilds.hashCode()
        return result
    }
}