package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class ProvidePolicyTest {
    
    @Test
    void maxEnrollment와tuitionFee_두개중_하나만_null이면_에러전파() {
        
        assertThatThrownBy(() -> {
            new ProvidePolicy(10, null);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("하나만 무료정책을 가질 수없다 (하나만 null 일 수 없다)");
        
        assertThatThrownBy(() -> {
            new ProvidePolicy(null, 10L);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("하나만 무료정책을 가질 수없다 (하나만 null 일 수 없다)");
    }
    
    @Test
    void 유료인데_수강료와_지불금액이_다르면_에러전파() throws Exception {
        assertThatNoException().isThrownBy(() -> {
            new ProvidePolicy(10, 10L).validatePayment(new Payment());
        });
    }
    
    @Test
    void 무료인데_수강료_납부하면_에러전파() throws Exception {
        ProvidePolicy providePolicy = new ProvidePolicy();
        assertThatThrownBy(() -> {
            providePolicy.validatePayment(new Payment());
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("무료 강의는 지불할 수 없다");
    }
    
    @Test
    void 수강신청_인원이_초과하면_에러전파() throws Exception {
        ProvidePolicy providePolicy = new ProvidePolicy(10, 10L);
        assertThatThrownBy(() -> {
            providePolicy.validateEnrollment(new EnrolledUsers(10));
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("이미 정원을 초과했다");
    }
    
    @Test
    void 무료강의인데_수강신청_시_정원체크하면_에러전파() throws Exception {
        ProvidePolicy providePolicy = new ProvidePolicy();
        assertThatThrownBy(() -> {
            providePolicy.validateEnrollment(new EnrolledUsers(10));
        }).isInstanceOf(CanNotJoinException.class)
            .hasMessage("무료강의는 정원이 없다");
    }
}