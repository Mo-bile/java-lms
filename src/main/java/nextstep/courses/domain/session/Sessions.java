package nextstep.courses.domain.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nextstep.courses.CanNotJoinException;

public class Sessions {
    
    private final List<Session> sessions;
    
    public Sessions() {
        this.sessions = new ArrayList<>();
    }
    
    public Sessions(Session... sessions) {
        this(Arrays.asList(sessions));
    }
    
    public Sessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    
    public Session findEnrollSession(long sessionId) throws CanNotJoinException {
        for(Session session: this.sessions) {
            if(session.isSameSessionId(sessionId)) {
                return session;
            }
        }
        throw new CanNotJoinException("신청하려는 강의가 존재하지 않습니다");
    }
}
