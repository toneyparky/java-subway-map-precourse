package subway.domain

class Station(private val name: String) {
    init {
        require(name.length >= 2) { "[ERROR] 역 이름은 2자 이상이여야 합니다." }
    }

    fun isSame(name: String): Boolean {
        return this.name == name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Station

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
