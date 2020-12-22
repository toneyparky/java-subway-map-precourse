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

    @Test
    fun `구간에 입력받은 위치에 역을 추가한다`() {
        val start = Station("시작역")
        val end = Station("종착역")
        val stations = Stations(start, end)

        val addingStation = Station("추가역")
        stations.add(addingStation, 1)

        assertThat(stations.list()).usingFieldByFieldElementComparator()
                .containsExactly(addingStation, start, end)
    }

    @Test
    fun `이미 구간에 추가된 역을 추가할 경우 예외가 발생한다`() {
        val start = Station("시작역")
        val end = Station("종착역")
        val stations = Stations(start, end)

        assertThatThrownBy { stations.add(start, 2) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 이미 해당 노선에 구간으로 추가된 역입니다.")
    }
}