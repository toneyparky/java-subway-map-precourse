package subway.domain

object StationRepository {
    private val stations: MutableList<Station> = mutableListOf()

    fun stations(): List<Station> {
        return stations.toList()
    }

    fun addStation(station: Station?) {
        require(station != null && !stations.contains(station)) { "[ERROR] 역을 추가할 수 없습니다." }
        stations.add(station)
    }

    fun deleteStation(name: String?): Boolean {
        // TODO: 2020/12/13 노선에 등록된 역은 제거할 수 없도록 처리한다
        return stations.removeAll { station -> station.isSame(name) }
    }
}