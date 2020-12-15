package subway.service

import subway.domain.Station
import subway.domain.StationRepository
import subway.dto.StationRequest

object StationService {
    fun create(request: StationRequest) {
        require(!StationRepository.existByName(request.name)) { "[ERROR] 이미 추가된 이름의 역을 추가할 수 없습니다." }

        StationRepository.addStation(request.toEntity())
    }

    fun findAll(): List<Station> {
        return StationRepository.stations()
    }

    fun delete(request: StationRequest) {
        // TODO: 2020/12/13 노선에 등록된 역은 제거할 수 없도록 처리한다
        StationRepository.deleteStation(request.name)
    }

    fun clear() {
        StationRepository.clear()
    }
}