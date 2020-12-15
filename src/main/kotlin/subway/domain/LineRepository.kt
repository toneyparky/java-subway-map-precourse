package subway.domain


object LineRepository {
    private val lines: MutableList<Line> = mutableListOf()

    fun lines(): List<Line> {
        return lines.toList()
    }

    fun addLine(line: Line?) {
        // TODO: 2020/12/13 종점 입력받기 
        require(line != null && !lines.contains(line)) { "[ERROR] 노선을 추가할 수 없습니다." }
        lines.add(line)
    }

    fun deleteLineByName(name: String): Boolean {
        return lines.removeAll { line -> line.isSame(name) }
    }
}