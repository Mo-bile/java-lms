package nextstep.courses.infrastructure.repository.enrollment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
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
    public int save(EnrollmentEntity enrollmentEntity) {
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
    public EnrollmentEntity findById(Long id) {
        String sql = "SELECT * FROM enrollment WHERE id = ?";
        RowMapper<EnrollmentEntity> rowMapper = (rs, rowNum) -> new EnrollmentEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getString("type"),
            rs.getLong("tuition_fee"),
            rs.getInt("max_enrollment"),
            rs.getString("session_status"),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    
    @Override
    public EnrollmentEntity findBySessionId(Long sessionId) {
        String sql = "SELECT * FROM enrollment WHERE session_id = ?";
        RowMapper<EnrollmentEntity> rowMapper = (rs, rowNum) -> new EnrollmentEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getString("type"),
            rs.getLong("tuition_fee"),
            rs.getInt("max_enrollment"),
            rs.getString("session_status"),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }
    
    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if(timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
    
}
