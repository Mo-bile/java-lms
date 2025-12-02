package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Question {
    private Long id;

    private String title;

    private String contents;

    private NsUser writer;
    
    private Answers answers = new Answers();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

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
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
    }

    public NsUser getWriter() {
        return writer;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }
    
    public void isHaveAuthority(NsUser loginUser) throws CannotDeleteException {
        if (!this.isNotOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public boolean isNotOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }
    
    public boolean delete() {
        Question question = this.deleteQuestion();
        return question.isDeleted();
    }
    
    public DeleteHistory addInDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, this.writer, LocalDateTime.now());
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
        answers.isHaveAuthority(writer);
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
