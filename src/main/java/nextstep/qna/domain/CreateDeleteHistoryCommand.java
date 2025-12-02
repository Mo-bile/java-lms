package nextstep.qna.domain;

import java.util.Objects;
import nextstep.users.domain.NsUser;

public class CreateDeleteHistoryCommand {
    
    private ContentType contentType;
    
    private Long contentId;
    
    private NsUser deletedBy;
    
    public CreateDeleteHistoryCommand(ContentType contentType, Long contentId, NsUser deletedBy) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateDeleteHistoryCommand that = (CreateDeleteHistoryCommand) o;
        return contentType == that.contentType && Objects.equals(contentId, that.contentId) && Objects.equals(deletedBy, that.deletedBy);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(contentType, contentId, deletedBy);
    }
    
    @Override
    public String toString() {
        return "CreateDeleteHistoryCommand{" +
            "contentType=" + contentType +
            ", contentId=" + contentId +
            ", deletedBy=" + deletedBy +
            '}';
    }
}