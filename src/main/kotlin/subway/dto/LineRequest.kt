package subway.dto

import subway.domain.Line
import subway.domain.Station
import subway.domain.Stations

data class LineRequest(val name: String, val startStationName: String, val endStationName: String) {
    val startAndEndStationNames: List<String> = listOf(startStationName, endStationName)

    fun toEntity(): Line {
        return Line(name, Stations(Station(startStationName), Station(endStationName)))
    }
}
