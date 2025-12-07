package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import org.junit.jupiter.api.Test;

class ProvideTest {
    
    @Test
    void 무료인데_수강료가_있으면_에러전파() {
        assertThatThrownBy(() -> {
            new Provide(ProvideType.FREE, 0, 10);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("무료 강의에는 수강료는 0 이어야 한다");
    }
    
    @Test
    void 유료인데_수강료가_없으면_에러전파() {
        assertThatThrownBy(() -> {
            new Provide(ProvideType.PAID, 0, 0);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("유료 강의에는 수강료는 1 이상이다");
    }
    
    @Test
    void 유료강의에_수강신청한다() throws Exception {
        Provide provide = new Provide(ProvideType.PAID, 0, 10);
        provide.apply(10, 10);
        
        Provide expected = new Provide(ProvideType.PAID, 1, 10);
        assertThat(provide).isEqualTo(expected);
    }
    
    @Test
    void 유료인데_수강료와_지불금액이_다르면_에러전파() throws Exception {
        Provide provide = new Provide(ProvideType.PAID, 0, 10);
        assertThatThrownBy(() -> {
            provide.apply(9, 10);
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("지불한 금액과 수강료 금액이 다르다");
    }
    
    @Test
    void 무료인데_수강료_납부하면_에러전파() throws Exception {
        Provide provide = new Provide(ProvideType.FREE, 0);
        assertThatThrownBy(() -> {
            provide.apply(10, 10);
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("지불한 금액과 수강료 금액이 다르다");
    }
    
    @Test
    void 수강신청_인원이_초과하면_에러전파() throws Exception {
        Provide provide = new Provide(ProvideType.PAID, 5, 10);
        assertThatThrownBy(() -> {
            provide.apply(10, 5);
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("이미 정원을 초과했다");
    }
    
}