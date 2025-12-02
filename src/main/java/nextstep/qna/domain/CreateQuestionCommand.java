package nextstep.qna.domain;

import java.util.Objects;
import nextstep.users.domain.NsUser;

public class CreateQuestionCommand {
    
    private String title;
    
    private String contents;
    
    private NsUser writer;
    
    public CreateQuestionCommand(String title, String contents, NsUser writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setContents(String contents) {
        this.contents = contents;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContents() {
        return contents;
    }
    
    public NsUser getWriter() {
        return writer;
    }
    
    public boolean isNotOwner(NsUser loginUser) {
        return !writer.equals(loginUser);
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateQuestionCommand that = (CreateQuestionCommand) o;
        return Objects.equals(title, that.title) && Objects.equals(contents, that.contents) && Objects.equals(writer, that.writer);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, contents, writer);
    }
    
    @Override
    public String toString() {
        return "CreateQuestionCommand{" +
            "title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            ", writer=" + writer +
            '}';
    }
}