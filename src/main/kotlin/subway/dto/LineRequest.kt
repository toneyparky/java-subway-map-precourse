package subway.dto

import subway.domain.Line

data class LineRequest(val name: String) {
    fun toEntity(): Line {
        return Line(name)
    }
}
