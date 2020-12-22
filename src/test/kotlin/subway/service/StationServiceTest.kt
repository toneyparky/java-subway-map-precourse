package subway.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import subway.dto.LineRequest
import subway.dto.StationRequest

internal class StationServiceTest {
    @AfterEach
    fun tearDown() {
        StationService.clear()
    }

    @Test
    fun `역을 추가한다`() {
        val request = StationRequest("강남역")
        StationService.create(request)

        assertThat(StationService.findAll()).usingFieldByFieldElementComparator().contains(request.toEntity())
    }

    @Test
    fun `이미 추가된 역을 추가할 경우 예외가 발생한다`() {
        val request = StationRequest("강남역")
        StationService.create(request)

        assertThatThrownBy { StationService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 이미 추가된 이름의 역을 추가할 수 없습니다.")
    }

    @Test
    fun `역을 제거한다`() {
        val request = StationRequest("강남역")
        StationService.create(request)

        StationService.delete(request)

        assertThat(StationService.findAll()).isEmpty()
    }

    @Test
    fun `노선에 등록된 역을 제거할 경우 예외가 발생한다`() {
        val startName = "시작역"
        val start = StationRequest(startName)
        StationService.create(start)

        val endName = "종착역"
        val end = StationRequest(endName)
        StationService.create(end)

        val lineRequest = LineRequest("노선", startName, endName)
        LineService.create(lineRequest)

        assertThatThrownBy { StationService.delete(start) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 노선에 포함된 역은 제거할 수 없습니다.")
    }
}