package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotCreateException;
import org.junit.jupiter.api.Test;

class SessionBodyTest {
    
    @Test
    void session_body의_제목과_본문은_null이면_에러전파() {
        assertThatThrownBy(() -> {
            new SessionBody(null, "content");
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("제목에 내용이 없다");
        
        assertThatThrownBy(() -> {
            new SessionBody("title", null);
        }).isInstanceOf(CanNotCreateException.class)
            .hasMessage("컨텐츠에 내용이 없다");
    }
    
}