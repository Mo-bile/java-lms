package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrolleduser.JdbcEnrolledUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class EnrolledUserRepositoryTest {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private EnrolledUserRepository enrolledUserRepository;
    
    @BeforeEach
    void setUp() {
        enrolledUserRepository = new JdbcEnrolledUserRepository(jdbcTemplate);
    }
    
    @Test
    void curd() {
        Long enrollmentId = 1L;
        EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(1L, 2L, 3L, 4L, 5L));

        int saveCount = enrolledUserRepository.saveAll(enrollmentId, enrolledUsers);
        assertThat(saveCount).isEqualTo(1);

        EnrolledUsers model = enrolledUserRepository.findByEnrollmentId(enrollmentId);
        
        assertThat(model.getEnrolledUserList()).isEqualTo(enrolledUsers.getEnrolledUserList());
        
    }
    
}