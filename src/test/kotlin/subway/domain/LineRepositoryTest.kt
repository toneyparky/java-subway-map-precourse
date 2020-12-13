package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class LineRepositoryTest {
    @Test
    fun `노선을 조회한다`() {
        val lines = LineRepository.lines()
        assertThat(lines).isNotNull
    }

    @Test
    fun `노선을 추가한다`() {
        val expected = Line("2호선")
        LineRepository.addLine(expected)

        assertThat(LineRepository.lines()).contains(expected)
    }

    @Test
    fun `이미 추가된 노선을 추가할 경우 예외가 발생한다`() {
        val line = Line("2호선")
        LineRepository.addLine(line)

        assertThatThrownBy { LineRepository.addLine(line) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 노선을 추가할 수 없습니다.")
    }

    @CsvSource(value = ["2호선,true", "3호선,false"])
    @ParameterizedTest
    fun `존재하는 노선을 삭제하면 노선을 삭제하고 true를 반환하고 존재하지 않는 역을 삭제하면 false를 반환한다`(name: String, expected: Boolean) {
        val line = Line("2호선")
        LineRepository.addLine(line)

        assertThat(LineRepository.deleteLineByName(name)).isEqualTo(expected)
    }
}