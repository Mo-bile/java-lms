package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class AnswersTest {
    
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");
    
    @Test
    void 답변을_쓴_사람과_제거하는_사람이_다르면_에러전파() throws Exception {
        Answers answers = new Answers(A1, A2);
        
        assertThatThrownBy(() -> {
            answers.isHaveAuthority(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
    
    @Test
    void 답변을_상태를_변경하여_삭제한다() {
        Answers answers = new Answers(A1, A2);
        
        assertThat(answers.deleteAll()).isTrue();
        assertThat(answers.isAllDelete()).isTrue();
    }
    
    @Test
    void 삭제한_답변의_기록을_남긴다() {
        Answers answers = new Answers(A1, A2);
        
        assertThat(answers.addInDeleteHistory()).hasSize(2);
    }
}