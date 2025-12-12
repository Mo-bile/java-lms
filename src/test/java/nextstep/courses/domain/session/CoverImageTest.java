package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enumerate.CoverImageType;
import org.junit.jupiter.api.Test;

class CoverImageTest {
    
    @Test
    void 커버이미지_크기는_1mb이상이면_에러전파() {
        assertThatThrownBy(() -> {
            new CoverImage(900_000, CoverImageType.JPEG, 300, 200);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("커버 이미지의 크기는 1MB 이상이어야 한다");
    }
}