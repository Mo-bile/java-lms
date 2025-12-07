package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class AnswersTest {
    
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");
    
    @Test
    void 답변을_상태를_변경하여_삭제한다() throws CannotDeleteException {
        Answers answers = new Answers(A1, A1);
        
        assertThat(answers.deleteAll(NsUserTest.JAVAJIGI)).isNotNull();
    }
    
    @Test
    void 삭제한_답변의_기록을_남긴다() {
        Answers answers = new Answers(A1, A2);
        
        assertThat(answers.addInDeleteHistory()).hasSize(2);
    }
}