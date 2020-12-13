package subway.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LineTest {
    @Test
    fun `노선을 생성한다`() {
        val line = Line("2호선")

        assertThat(line).isInstanceOf(Line::class.java)
    }

    @ValueSource(strings = ["", "호"])
    @ParameterizedTest
    internal fun `노선 생성시 이름이 2자 미만이면 예외가 발생한다`(invalidName: String) {
        assertThatThrownBy { Line(invalidName) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 노선 이름은 2자 이상이여야 합니다.")
    }
}