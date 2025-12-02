package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {
    
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Answer A1 = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    
    @Test
    void 삭제된_정보를_저장한다() {
        DeleteHistories deleteHistories = new DeleteHistories();
        DeleteHistories addeddeleteHistories = new DeleteHistories(
            new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
        deleteHistories.addHistories(addeddeleteHistories);
    }
    
    @Test
    void saveAll하기위해_다시List로_반환한다() {
        DeleteHistories deleteHistories = new DeleteHistories(
            new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
            new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
        
        List<DeleteHistory> deleteHistoryList = deleteHistories.toList();
        assertThat(deleteHistoryList).hasSize(2);
    }
}