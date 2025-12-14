package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.builder.EnrollmentBuilder;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.mapper.EnrollmentMapper;
import nextstep.courses.infrastructure.repository.enrollment.EnrollmentRepository;
import nextstep.courses.infrastructure.repository.enrollment.JdbcEnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class EnrollmentRepositoryTest {
    
    private static Logger logger = LoggerFactory.getLogger(EnrollmentRepositoryTest.class);
    
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
        EnrollmentEntity freeEnrollmentEntity = EnrollmentMapper.toEntity(freeSessionId, freeEnrollment);
        EnrollmentEntity paidEnrollmentEntity = EnrollmentMapper.toEntity(paidSessionId, paidEnrollment);
        
        int freeSave = enrollmentRepository.save(freeEnrollmentEntity);
        int paidSave = enrollmentRepository.save(paidEnrollmentEntity);
        
        assertThat(freeSave).isEqualTo(1);
        assertThat(paidSave).isEqualTo(1);
        
        EnrollmentEntity savedFreeEnrollmentEntity = enrollmentRepository.findBySessionId(freeSessionId);
        EnrollmentEntity savedPaidEnrollmentEntity = enrollmentRepository.findBySessionId(paidSessionId);
        Enrollment savedFreeEnrollment = EnrollmentMapper.toModel(savedFreeEnrollmentEntity);
        Enrollment savedPaidEnrollment = EnrollmentMapper.toModel(savedPaidEnrollmentEntity);
        
        assertThat(freeEnrollment.getType()).isEqualTo(savedFreeEnrollment.getType());
        assertThat(paidEnrollment.getType()).isEqualTo(savedPaidEnrollment.getType());
    }
}