package subway.domain

object LineRepository {
    private val lines: MutableList<Line> = mutableListOf()

    fun lines(): List<Line> {
        return lines
    }

    fun addLine(line: Line) {
        lines.add(line)
    }

    fun deleteLineByName(name: String): Boolean {
        return lines.removeAll { line -> line.isSame(name) }
    }

    fun clear() {
        lines.clear()
    }

    fun existByName(name: String): Boolean {
        return lines.any { it.isSame(name) }
    }
}