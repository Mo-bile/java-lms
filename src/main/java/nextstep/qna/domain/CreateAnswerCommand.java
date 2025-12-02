package nextstep.qna.domain;

import java.util.Objects;
import nextstep.users.domain.NsUser;

public class CreateAnswerCommand {
    
    private NsUser writer;
    
    private Question question;
    
    private String contents;
    
    public CreateAnswerCommand(NsUser writer, Question question, String contents) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }
    
    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }
    
    public NsUser getWriter() {
        return writer;
    }
    
    public Question getQuestion() {
        return question;
    }
    
    public String getContents() {
        return contents;
    }
    
    public void toQuestion(Question question) {
        this.question = question;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateAnswerCommand that = (CreateAnswerCommand) o;
        return Objects.equals(writer, that.writer) && Objects.equals(question, that.question) && Objects.equals(contents, that.contents);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(writer, question, contents);
    }
    
    @Override
    public String toString() {
        return "CreateAnswerCommand{" +
            "writer=" + writer +
            ", question=" + question +
            ", contents='" + contents + '\'' +
            '}';
    }
}