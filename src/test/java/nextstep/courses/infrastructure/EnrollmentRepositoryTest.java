package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.builder.EnrollmentBuilder;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.enrollment.JdbcEnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class EnrollmentRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private EnrollmentRepository enrollmentRepository;
    
    @BeforeEach
    void setUp() {
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
    }
    
    @Test
    void crud() throws CanNotCreateException {
        Long freeSessionId = 1L;
        Long paidSessionId = 2L;
        Enrollment freeEnrollment = EnrollmentBuilder.aFreeEnrollmentBuilder().build();
        Enrollment paidEnrollment = EnrollmentBuilder.aPaidEnrollmentBuilder().build();

        int freeSave = enrollmentRepository.save(freeSessionId, freeEnrollment);
        int paidSave = enrollmentRepository.save(paidSessionId, paidEnrollment);
        
        assertThat(freeSave).isEqualTo(1);
        assertThat(paidSave).isEqualTo(1);

        Enrollment savedFreeEnrollment = enrollmentRepository.findBySessionId(freeSessionId);
        Enrollment savedPaidEnrollment = enrollmentRepository.findBySessionId(paidSessionId);
        
        assertThat(freeEnrollment.getType()).isEqualTo(savedFreeEnrollment.getType());
        assertThat(paidEnrollment.getType()).isEqualTo(savedPaidEnrollment.getType());
    }
}