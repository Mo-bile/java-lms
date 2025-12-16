package nextstep.courses.infrastructure.repository.enrollment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.mapper.EnrollmentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public JdbcEnrollmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int save(Long sessionId, Enrollment enrollment) {
        EnrollmentEntity enrollmentEntity = EnrollmentMapper.toEntity(sessionId, enrollment);
        String sql = "INSERT INTO enrollment (session_id, type, tuition_fee, max_enrollment, session_status, created_date, updated_date) values (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
            enrollmentEntity.getSessionId(),
            enrollmentEntity.getType(),
            enrollmentEntity.getTuitionFee(),
            enrollmentEntity.getMaxEnrollment(),
            enrollmentEntity.getSessionStatus(),
            LocalDateTime.now(),
            LocalDateTime.now());
    }
    
    @Override
    public Enrollment findById(Long id) {
        String sql = "SELECT * FROM enrollment WHERE id = ?";
        EnrollmentEntity entity = jdbcTemplate.queryForObject(sql, enrollmentRowMapper(), id);
        List<EnrolledUserEntity> enrolledUsers = findEnrolledUserEntities(entity.getId());
        return EnrollmentMapper.toModelWithEnrolledUsers(entity, enrolledUsers);
    }

    @Override
    public Enrollment findBySessionId(Long sessionId) {
        String sql = "SELECT * FROM enrollment WHERE session_id = ?";
        EnrollmentEntity entity = jdbcTemplate.queryForObject(sql, enrollmentRowMapper(), sessionId);
        List<EnrolledUserEntity> enrolledUsers = findEnrolledUserEntities(entity.getId());
        return EnrollmentMapper.toModelWithEnrolledUsers(entity, enrolledUsers);
    }

    @Override
    public Long findIdBySessionId(Long sessionId) {
        String sql = "SELECT id FROM enrollment WHERE session_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private RowMapper<EnrollmentEntity> enrollmentRowMapper() {
        return (rs, rowNum) -> new EnrollmentEntity(
            rs.getLong("session_id"),
            rs.getLong("id"),
            rs.getString("type"),
            rs.getLong("tuition_fee"),
            rs.getInt("max_enrollment"),
            rs.getString("session_status"),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
    }

    private List<EnrolledUserEntity> findEnrolledUserEntities(Long enrollmentId) {
        String sql = "SELECT * FROM enrolled_user WHERE enrollment_id = ?";
        RowMapper<EnrolledUserEntity> rowMapper = (rs, rowNum) -> new EnrolledUserEntity(
            rs.getLong("enrollment_id"),
            rs.getLong("id"),
            rs.getLong("user_id"),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        return jdbcTemplate.query(sql, rowMapper, enrollmentId);
    }
    
}
