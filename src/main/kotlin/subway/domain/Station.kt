package subway.domain

class Station(private val name: String) {
    init {
        require(name.length >= 2) { "[ERROR] 역 이름은 2자 이상이여야 합니다." }
    }

    fun isSame(name: String?): Boolean {
        require(name != null) { "[ERROR] 역 이름을 입력해주세요." }
        return this.name == name
    }
}
