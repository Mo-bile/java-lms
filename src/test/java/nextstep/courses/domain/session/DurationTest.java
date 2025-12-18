package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.CanNotCreateException;
import org.junit.jupiter.api.Test;

class DurationTest {
    
    @Test
    void 강의의_시작과_종료일은_오늘보다_앞에있으면_에러전파() {
        LocalDate startDate = LocalDate.now().minusDays(2);
        LocalDate endDate = LocalDate.now().minusDays(1);
        
        assertThatThrownBy(() -> {
            new Duration(startDate, endDate);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("강의 날짜는 오늘에서 다음날에 존재해야 한다");
    }
    
    @Test
    void 강의_종료일이_시작일보다_앞에있으면_에러전파() {
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(1);
        
        assertThatThrownBy(() -> {
            new Duration(startDate, endDate);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("강의 종료일이 시작일보다 앞에 있을 수 없다");
    }
}