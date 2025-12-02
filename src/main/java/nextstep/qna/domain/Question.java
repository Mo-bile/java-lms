package nextstep.qna.domain;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Question extends Base{
    private Long id;
    
    private CreateQuestionCommand createQuestionCommand;

    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.createQuestionCommand = new CreateQuestionCommand(title, contents, writer);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return createQuestionCommand.getTitle();
    }

    public Question setTitle(String title) {
        createQuestionCommand.setTitle(title);
        return this;
    }

    public String getContents() {
        return createQuestionCommand.getContents();
    }

    public Question setContents(String contents) {
        createQuestionCommand.setContents(contents);
        return this;
    }

    public NsUser getWriter() {
        return createQuestionCommand.getWriter();
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }
    
    public void isHaveAuthority(NsUser loginUser) throws CannotDeleteException {
        if(createQuestionCommand.isNotOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }
    
    public boolean delete() {
        Question question = this.deleteQuestion();
        return question.isDeleted();
    }
    
    public DeleteHistory addInDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.id, createQuestionCommand.getWriter(), LocalDateTime.now());
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
        answers.isHaveAuthority(createQuestionCommand.getWriter());
        return answers;
    }
    
    @Override
    public String toString() {
        return "Question{" +
            "id=" + id +
            ", createQuestionCommand=" + createQuestionCommand +
            ", answers=" + answers +
            ", deleted=" + deleted +
            '}';
    }
}
