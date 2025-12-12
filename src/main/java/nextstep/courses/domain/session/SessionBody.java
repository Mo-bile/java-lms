package nextstep.courses.domain.session;

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
}
