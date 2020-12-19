package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class LineRepositoryTest {
    private lateinit var start: Station
    private lateinit var end: Station

    @BeforeEach
    fun setUp() {
        start = Station("시작역")
        end = Station("종착역")
    }

    @AfterEach
    fun tearDown() {
        LineRepository.clear()
    }

    @Test
    fun `노선을 조회한다`() {
        val lines = LineRepository.lines()
        assertThat(lines).isNotNull
    }

    @Test
    fun `노선을 추가한다`() {
        val expected = Line("2호선", Stations(start, end))
        LineRepository.addLine(expected)

        assertThat(LineRepository.lines()).contains(expected)
    }

    @CsvSource(value = ["2호선,true", "3호선,false"])
    @ParameterizedTest
    fun `존재하는 노선을 삭제하면 노선을 삭제하고 true를 반환하고 존재하지 않는 역을 삭제하면 false를 반환한다`(name: String, expected: Boolean) {
        val line = Line("2호선", Stations(start, end))
        LineRepository.addLine(line)

        assertThat(LineRepository.deleteLineByName(name)).isEqualTo(expected)
    }
}