package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {
    
    private final List<Answer> answers;
    
    public Answers() {
        this(List.of());
    }
    
    public Answers(Answer... answers) {
        this(Arrays.asList(answers));
    }
    
    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }
    
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
    
    public List<DeleteHistory> deleteAll(NsUser loginUser) throws CannotDeleteException {
        deleteAllAnswers(loginUser);
        return addInDeleteHistory();
    }
    
    private void deleteAllAnswers(NsUser loginUser) throws CannotDeleteException {
        for(Answer answer: answers) {
            answer.delete(loginUser);
        }
    }
    
    public List<DeleteHistory> addInDeleteHistory() {
        return this.answers.stream()
            .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(answers);
    }
}
