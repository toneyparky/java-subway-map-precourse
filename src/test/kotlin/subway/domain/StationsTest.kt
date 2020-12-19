package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class StationsTest {
    @Test
    fun `구간 양 끝 종점이 다를 경우 잘 생성된다`() {
        val start = Station("시작역")
        val end = Station("종착역")

        assertThat(Stations(start, end)).isInstanceOf(Stations::class.java)
    }


    @Test
    fun `구간 양 끝 종점이 같을 경우 예외가 발생한다`() {
        val station = Station("같은역")

        assertThatThrownBy { Stations(station, station) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 구간의 양 끝 종점이 같을 수 없습니다.")
    }
}