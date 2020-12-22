package subway.domain

class Stations(start: Station, end: Station){
    init {
        require(start != end) { "[ERROR] 구간의 양 끝 종점이 같을 수 없습니다." }
    }

    private val stations: MutableList<Station> = mutableListOf(start, end)

    fun list(): List<Station> {
        return stations
    }

    fun add(station: Station, position: Int) {
        require(station !in stations) { "[ERROR] 이미 해당 노선에 구간으로 추가된 역입니다." }
        stations.add(position - 1, station)
    }

    fun contains(station: Station): Boolean {
        return station in stations
    }
}