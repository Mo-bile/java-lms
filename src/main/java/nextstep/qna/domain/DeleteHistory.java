package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import nextstep.users.domain.NsUser;

public class DeleteHistory extends Base{
    private Long id;
    
    private CreateDeleteHistoryCommand createDeleteHistoryCommand;

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        createDeleteHistoryCommand = new CreateDeleteHistoryCommand(contentType, contentId, deletedBy);
        this.createdDate = createdDate;
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(createDeleteHistoryCommand, that.createDeleteHistoryCommand);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, createDeleteHistoryCommand);
    }
    
    @Override
    public String toString() {
        return "DeleteHistory{" +
            "id=" + id +
            ", createDeleteHistoryCommand=" + createDeleteHistoryCommand +
            '}';
    }
}
