package nextstep.courses.domain.session;

import java.util.Objects;
import nextstep.courses.CanNotCreateException;

public class SessionBody {
    
    private final String title;
    private final String content;
    
    public SessionBody(String title, String content) throws CanNotCreateException {
        validate(title, content);
        this.title = title;
        this.content = content;
    }
    
    private void validate(String title, String content) throws CanNotCreateException {
        if(title == null) {
            throw new CanNotCreateException("제목에 내용이 없다");
        }
        if(content == null) {
            throw new CanNotCreateException("컨텐츠에 내용이 없다");
        }
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionBody that = (SessionBody) o;
        return Objects.equals(title, that.title) && Objects.equals(content, that.content);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }
}
