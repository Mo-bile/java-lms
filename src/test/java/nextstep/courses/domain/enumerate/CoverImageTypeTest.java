package nextstep.courses.domain.enumerate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import org.junit.jupiter.api.Test;

class CoverImageTypeTest {
    
    @Test
    void 커버이미지의_정해진타입이_아니면_에러전파() {
        assertThatThrownBy(() -> {
            CoverImageType.valueOfIgnoreCase("BMP");
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("커버 이미지의 타입은 gif, jpg(jpeg), png, svg 만 가능하다");
    }
}