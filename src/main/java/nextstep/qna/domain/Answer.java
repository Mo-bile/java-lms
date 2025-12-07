package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answer extends Base {
    private Long id;
    
    private NsUser writer;
    
    private Question question;
    
    private String contents;
    
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
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }
    
    public void delete(NsUser loginUser) throws CannotDeleteException {
        isHaveAuthority(loginUser);
        deleteAnswer();
    }
    
    public void deleteAnswer() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }
    
    private void isHaveAuthority(NsUser loginUser) throws CannotDeleteException {
        if(isNotOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
    
    public boolean isNotOwner(NsUser writer) {
        return !this.writer.equals(writer);
    }

    public NsUser getWriter() {
        return this.writer;
    }

    public String getContents() {
        return this.contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }
    
    @Override
    public String toString() {
        return "Answer{" +
            "id=" + id +
            ", writer=" + writer +
            ", question=" + question +
            ", contents='" + contents + '\'' +
            ", deleted=" + deleted +
            '}';
    }
}
