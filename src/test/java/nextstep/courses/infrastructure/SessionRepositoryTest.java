package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.builder.SessionBuilder;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.repository.session.JdbcSessionRepository;
import nextstep.courses.infrastructure.repository.session.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class SessionRepositoryTest {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private SessionRepository sessionRepository;
    
    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }
    
    @Test
    void crud() throws CanNotCreateException {
        Long courseId = 1L;
        Session freeSession = SessionBuilder.aFreeSessionBuilder().build();
        Session paidSession = SessionBuilder.aPaidSessionBuilder().build();

        int freeSave = sessionRepository.save(courseId, freeSession);
        int paidSave = sessionRepository.save(courseId, paidSession);
        
        assertThat(freeSave).isEqualTo(1);
        assertThat(paidSave).isEqualTo(1);

        List<Session> sessionList = sessionRepository.findAllByCourseId(courseId);
        
        assertThat(sessionList).hasSize(2);
        assertThat(freeSession.getBody()).isEqualTo(sessionList.get(0).getBody());
        assertThat(paidSession.getBody()).isEqualTo(sessionList.get(1).getBody());
    }
}