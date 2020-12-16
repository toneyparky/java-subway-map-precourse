package subway.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import subway.domain.Line
import subway.domain.LineRepository
import subway.dto.LineRequest

internal class LineServiceTest {
    @AfterEach
    fun tearDown() {
        LineService.clear()
    }

    @Test
     fun `노선을 추가한다`() {
        val request = LineRequest("2호선")
        LineService.create(request)

        assertThat(LineService.findAll()).usingFieldByFieldElementComparator().contains(request.toEntity())
    }

    @Test
    fun `이미 추가된 노선을 추가할 경우 예외가 발생한다`() {
        val request = LineRequest("2호선")
        LineService.create(request)

        Assertions.assertThatThrownBy { LineService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 이미 추가된 이름의 노선을 추가할 수 없습니다.")
    }

    @Test
     fun `노선을 제거한다`() {
        val request = LineRequest("2호선")
        LineService.create(request)

        LineService.delete(request)

        assertThat(LineService.findAll()).isEmpty()
    }
}