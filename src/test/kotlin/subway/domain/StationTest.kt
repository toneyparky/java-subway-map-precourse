package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class StationTest {
    @Test
    fun `역을 생성한다`() {
        val station = Station("강남역")
        assertThat(station).isInstanceOf(Station::class.java)
    }

    @ValueSource(strings = ["", "역"])
    @ParameterizedTest
    fun `역 생성시 이름이 2자 미만일 경우 예외가 발생한다`(invalidName: String) {
        assertThatThrownBy { Station(invalidName) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 역 이름은 2자 이상이여야 합니다.")
    }
}