package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question extends Base{
    private Long id;
    
    private String title;
    
    private String contents;
    
    private NsUser writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return this.getTitle();
    }

    public Question setTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public String getContents() {
        return this.getContents();
    }

    public Question setContents(String contents) {
        this.setContents(contents);
        return this;
    }

    public NsUser getWriter() {
        return this.writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }
    
    public DeleteHistories delete(NsUser loginUser) throws CannotDeleteException {
        this.isHaveAuthority(loginUser);
        Question question = this.deleteQuestion();
        if(question.isDeleted()) {
            return new DeleteHistories(addInDeleteHistory(), answers.deleteAll(loginUser));
        }
        return new DeleteHistories();
    }
    
    public void isHaveAuthority(NsUser loginUser) throws CannotDeleteException {
        if(this.isNotOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }
    
    public boolean isNotOwner(NsUser loginUser) {
        return !writer.equals(loginUser);
    }
    
    public DeleteHistory addInDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, this.getWriter(), LocalDateTime.now());
    }
    
    public Question deleteQuestion() {
        this.deleted = true;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }
    
    public Answers sameUserQuestionAndAnswer() throws CannotDeleteException {
        answers.isHaveAuthority(this.getWriter());
        return answers;
    }
    
    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            ", writer=" + writer +
            ", answers=" + answers +
            ", deleted=" + deleted +
            '}';
    }
}
