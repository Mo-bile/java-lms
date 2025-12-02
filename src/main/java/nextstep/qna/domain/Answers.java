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
    
    public boolean deleteAll() {
        return this.answers.stream()
            .map(answer -> answer.setDeleted(true))
            .allMatch(Answer::isDeleted);
    }
    
    public boolean isAllDelete() {
        return this.answers.stream()
            .allMatch(Answer::isDeleted);
    }
    
    public List<DeleteHistory> isAllAddInDeleteHistory() {
        return this.answers.stream()
            .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
            .collect(Collectors.toList());
    }
    
    public void isAllOwner(NsUser loginUser) throws CannotDeleteException {
        if(isNotAllMatch(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
    
    private boolean isNotAllMatch(NsUser loginUser) {
        return this.answers.stream()
            .anyMatch(answer -> !answer.isOwner(loginUser));
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
