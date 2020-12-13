package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class StationRepositoryTest {
    @Test
    fun `역을 조회한다`() {
        val stations = StationRepository.stations()
        assertThat(stations).isNotNull
    }

    @Test
    fun `역을 추가한다`() {
        val expected = Station("강남역")
        StationRepository.addStation(expected)

        assertThat(StationRepository.stations()).contains(expected)
    }

    @Test
    fun `이미 추가된 역을 추가할 경우 예외가 발생한다`() {
        val station = Station("강남역")
        StationRepository.addStation(station)

        assertThatThrownBy { StationRepository.addStation(station) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 역을 추가할 수 없습니다.")
    }

    @CsvSource(value = ["강남역,true", "잠실역,false"])
    @ParameterizedTest
    fun `존재하는 역을 삭제하면 역을 삭제하고 true를 반환하고 존재하지 않는 역을 삭제하면 false를 반환한다`(name: String, expected: Boolean) {
        val station = Station("강남역")
        StationRepository.addStation(station)

        assertThat(StationRepository.deleteStation(name)).isEqualTo(expected)
    }
}