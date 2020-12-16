package subway.domain

object StationRepository {
    private val stations: MutableList<Station> = mutableListOf()

    fun stations(): List<Station> {
        return stations.toList()
    }

    fun addStation(station: Station) {
        stations.add(station)
    }

    fun deleteStation(name: String): Boolean {
        return stations.removeAll { station -> station.isSame(name) }
    }

    fun clear() {
        stations.clear()
    }

    fun existByName(name: String): Boolean {
        return stations.any { it.isSame(name) }
    }

    fun existAllByNames(names: List<String>): Boolean {
        return names.all { existByName(it) }
    }
}