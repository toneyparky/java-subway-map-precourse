package subway.domain


object LineRepository {
    private val lines: MutableList<Line> = mutableListOf()

    fun lines(): List<Line> {
        return lines.toList()
    }

    fun addLine(line: Line?) {
        if (line != null) {
            lines.add(line)
        }
    }

    fun deleteLineByName(name: String?): Boolean {
        return lines.removeAll { line -> line.name === name }
    }
}