package subway.dto

import subway.domain.Line

data class LineRequest(val name: String, val startStationName: String, val endStationName: String) {
    val startAndEndStationNames: List<String> = listOf(startStationName, endStationName)

    fun toEntity(): Line {
        return Line(name)
    }
}
