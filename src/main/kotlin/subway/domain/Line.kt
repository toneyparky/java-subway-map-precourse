package subway.domain

class Line(private val name: String, private val stations: Stations) {
    init {
        require(name.length >= 2) { "[ERROR] 노선 이름은 2자 이상이여야 합니다." }
    }

    fun isSame(name: String): Boolean {
        return this.name == name
    }

    fun addStation(station: Station, position: Int) {
        stations.add(station, position)
    }

    fun containStation(station: Station): Boolean {
        return stations.contains(station)
    }
}