package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {
    
    @Test
    void 삭제된_정보를_저장한다() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistories addeddeleteHistories = new DeleteHistories(
            new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        deleteHistories.addHistories(addeddeleteHistories);
    }
    
    @Test
    void saveAll하기위해_다시List로_반환한다() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        
        DeleteHistories deleteHistories = new DeleteHistories(
            new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        
        List<DeleteHistory> deleteHistoryList = deleteHistories.toList();
        assertThat(deleteHistoryList).hasSize(2);
    }
}