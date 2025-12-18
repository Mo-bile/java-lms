package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import org.junit.jupiter.api.Test;

class DimensionsTest {
    
    @Test
    void 커버이미지의_정해진_크기_이하이면_에러전파() {
        assertThatThrownBy(() -> {
            new Dimensions(299, 200);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("이미지의 너비(width)는 300px 이상이어야 한다");
    }
    
    @Test
    void 커버이미지의_정해진_비율이_아니면_에러전파() {
        assertThatThrownBy(() -> {
            new Dimensions(300, 199);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("이미지의 높이(height)는 200px 이상이어야 한다");
    }
    
    @Test
    void 커버이미지의_너비와_높이_비율은_3대2가_아니면_에러전파() {
        assertThatThrownBy(() -> {
            new Dimensions(300, 300);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("이미지의 너비(width)와 높이(height)의 비율은 3:2 이어야 한다");
    }
    
}