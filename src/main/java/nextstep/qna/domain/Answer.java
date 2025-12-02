package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends Base {
    private Long id;
    
    private CreateAnswerCommand createAnswerCommand;

    private boolean deleted = false;

    public Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        this.id = id;
        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }
        
        createAnswerCommand = new CreateAnswerCommand(writer, question, contents);
    }

    public Long getId() {
        return id;
    }

    public Answer deleteAnswer() {
        this.deleted = true;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.createAnswerCommand.isOwner(writer);
    }

    public NsUser getWriter() {
        return this.createAnswerCommand.getWriter();
    }

    public String getContents() {
        return this.createAnswerCommand.getContents();
    }

    public void toQuestion(Question question) {
        this.createAnswerCommand.toQuestion(question);
    }
    
    @Override
    public String toString() {
        return "Answer{" +
            "id=" + id +
            ", createAnswerCommand=" + createAnswerCommand +
            ", deleted=" + deleted +
            '}';
    }
}
