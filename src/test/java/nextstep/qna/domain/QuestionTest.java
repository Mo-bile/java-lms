package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    
    @Test
    void 작성자가_아니면_질문을_삭제하면_에러전파() throws Exception {
        assertThatThrownBy(() -> {
            Q1.isHaveAuthority(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
    
    @Test
    void 질문을_삭제한다() throws CannotDeleteException {
        assertThat(Q1.delete(NsUserTest.JAVAJIGI))
            .isNotNull();
    }
    
    @Test
    void 삭제한_답변의_기록을_남긴다() {
        assertThat(Q1.addInDeleteHistory()).isNotNull();
    }
    
}
