package subway.service

import subway.domain.Line
import subway.domain.LineRepository
import subway.domain.StationRepository
import subway.dto.LineRequest

object LineService {
    fun create(request: LineRequest) {
        require(!LineRepository.existByName(request.name)) { "[ERROR] 이미 추가된 이름의 노선을 추가할 수 없습니다." }
        require(StationRepository.existAllByNames(request.startAndEndStationNames)) { "[ERROR] 올바른 종점을 입력해주세요." }

        LineRepository.addLine(request.toEntity())
    }

    fun findAll(): List<Line> {
        return LineRepository.lines()
    }

    fun delete(request: LineRequest) {
        LineRepository.deleteLineByName(request.name)
    }

    fun clear() {
        LineRepository.clear()
    }
}