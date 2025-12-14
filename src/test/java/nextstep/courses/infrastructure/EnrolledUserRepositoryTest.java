package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.mapper.EnrolledUserMapper;
import nextstep.courses.infrastructure.repository.enrolleduser.EnrolledUserRepository;
import nextstep.courses.infrastructure.repository.enrolleduser.JdbcEnrolledUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class EnrolledUserRepositoryTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EnrolledUserRepositoryTest.class);
    
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
        List<EnrolledUserEntity> enrolledUserEntities = EnrolledUserMapper.toEntities(enrollmentId, enrolledUsers);
        
        int saveCount = enrolledUserRepository.saveAll(enrolledUserEntities);
        assertThat(saveCount).isEqualTo(1);
        
        List<EnrolledUserEntity> byEnrollmentId = enrolledUserRepository.findByEnrollmentId(enrollmentId);
        EnrolledUsers model = EnrolledUserMapper.toModel(byEnrollmentId);
        
        assertThat(model.getEnrolledUserList()).isEqualTo(enrolledUsers.getEnrolledUserList());
        
    }
    
}