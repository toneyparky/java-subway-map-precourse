package subway.domain

class Stations (start: Station, end: Station){
    init {
        require(start != end) { "[ERROR] 구간의 양 끝 종점이 같을 수 없습니다." }
    }
    private val sections: List<Station> = mutableListOf(start, end)
}