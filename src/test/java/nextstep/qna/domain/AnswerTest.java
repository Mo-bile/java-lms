package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    
    @Test
    void 답변을_쓴_사람과_제거하는_사람이_다르면_에러전파() {
        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        
    }
    
    @Test
    void 답변을_삭제하고_성공하면True를_반환한다() throws Exception {
        
        A1.delete(NsUserTest.JAVAJIGI);
        
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isFalse();
    }
}
