package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LineTest {
    private lateinit var start: Station
    private lateinit var end: Station

    @BeforeEach
    fun setUp() {
        start = Station("시작역")
        end = Station("종착역")
    }

    @Test
    fun `노선을 생성한다`() {
        val line = Line("2호선", Stations(start, end))

        assertThat(line).isInstanceOf(Line::class.java)
    }

    @ValueSource(strings = ["", "호"])
    @ParameterizedTest
    internal fun `노선 생성시 이름이 2자 미만이면 예외가 발생한다`(invalidName: String) {
        assertThatThrownBy { Line(invalidName, Stations(start, end)) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 노선 이름은 2자 이상이여야 합니다.")
    }

    @Test
    fun `노선의 구간에 역을 추가한다`() {
        val line = Line("2호선", Stations(start, end))
        val addingStation = Station("추가역")
        line.addStation(addingStation, 2)

        assertThat(line.containStation(addingStation)).isTrue
    }
}