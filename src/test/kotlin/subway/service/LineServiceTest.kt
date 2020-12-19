package subway.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import subway.dto.LineRequest
import subway.dto.StationRequest

internal class LineServiceTest {
    @BeforeEach
    fun setUp() {
        StationService.create(StationRequest(START_STATION_NAME))
        StationService.create(StationRequest(END_STATION_NAME))
    }

    @AfterEach
    fun tearDown() {
        LineService.clear()
        StationService.clear()
    }

    @Test
    fun `노선을 추가한다`() {
        val request = LineRequest(LINE_NAME, START_STATION_NAME, END_STATION_NAME)
        LineService.create(request)

        assertThat(LineService.findAll()).usingElementComparatorOnFields("name").contains(request.toEntity())
    }

    @Test
    fun `이미 추가된 노선을 추가할 경우 예외가 발생한다`() {
        val request = LineRequest(LINE_NAME, START_STATION_NAME, END_STATION_NAME)
        LineService.create(request)

        assertThatThrownBy { LineService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("[ERROR] 이미 추가된 이름의 노선을 추가할 수 없습니다.")
    }

    @Test
    fun `노선을 제거한다`() {
        val request = LineRequest(LINE_NAME, START_STATION_NAME, END_STATION_NAME)
        LineService.create(request)

        LineService.delete(request)

        assertThat(LineService.findAll()).isEmpty()
    }

    companion object {
        private const val LINE_NAME = "2호선"
        private const val START_STATION_NAME = "잠실역"
        private const val END_STATION_NAME = "잠실나루역"
    }
}
