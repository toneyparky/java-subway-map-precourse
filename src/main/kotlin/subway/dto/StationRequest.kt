package subway.dto

import subway.domain.Station

data class StationRequest(val name: String) {
    fun toEntity(): Station {
        return Station(name)
    }
}
