package subway.domain

import org.assertj.core.api.Assertions.assertThat
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

    @CsvSource(value = ["강남역,true", "잠실역,false"])
    @ParameterizedTest
    fun `존재하는 역을 삭제하면 역을 삭제하고 true를 반환하고 존재하지 않는 역을 삭제하면 false를 반환한다`(name: String, expected: Boolean) {
        val station = Station("강남역")
        StationRepository.addStation(station)

        assertThat(StationRepository.deleteStation(name)).isEqualTo(expected)
    }

    @Test
    fun `역 이름들이 존재하는 역들에 포함되면 true를 반환한다`() {
        val station1 = Station("강남역")
        val station2 = Station("잠실역")
        val station3 = Station("잠실새내역")
        StationRepository.addStation(station1)
        StationRepository.addStation(station2)
        StationRepository.addStation(station3)

        assertThat(StationRepository.existAllByNames(listOf("잠실역", "잠실새내역", "강남역"))).isTrue
    }

    @Test
    fun `역 이름들이 존재하는 역에 포함되지 않으면 false를 반환한다`() {
        val station1 = Station("강남역")
        val station2 = Station("잠실역")
        val station3 = Station("잠실새내역")
        StationRepository.addStation(station1)
        StationRepository.addStation(station2)
        StationRepository.addStation(station3)

        assertThat(StationRepository.existAllByNames(listOf("잠실역", "동작역", "강남역"))).isFalse
    }
}