package subway.domain

class Line(private val name: String) {
    init {
        require(name.length >= 2) { "[ERROR] 노선 이름은 2자 이상이여야 합니다." }
    }

    fun isSame(name: String): Boolean {
        return this.name == name
    }
}