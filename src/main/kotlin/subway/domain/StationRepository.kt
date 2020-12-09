package subway.domain

object StationRepository {
    private val stations: MutableList<Station> = mutableListOf()

    fun stations(): List<Station> {
        return stations.toList()
    }

    fun addStation(station: Station?) {
        if (station != null) {
            stations.add(station)
        }
    }

    fun deleteStation(name: String?): Boolean {
        return stations.removeAll { station -> station.name === name }
    }
}