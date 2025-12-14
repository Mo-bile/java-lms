package nextstep.courses.infrastructure.repository.enrolledUserRepository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrolledUserRepository")
public class JdbcEnrolledUserRepository implements EnrolledUserRepository {
    
    private JdbcOperations jdbcTemplate;
    
    public JdbcEnrolledUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int saveAll(List<EnrolledUserEntity> enrolledUserEntities) {
        String sql = "INSERT INTO enrolled_user (enrollment_id, user_id, created_date, updated_date) VALUES (?, ?, ?, ?)";
        int[][] ints = jdbcTemplate.batchUpdate(sql, enrolledUserEntities, enrolledUserEntities.size(),
            (PreparedStatement ps, EnrolledUserEntity entity) -> {
                ps.setLong(1, entity.getEnrollmentId());
                ps.setLong(2, entity.getUserId());
                ps.setObject(3, LocalDateTime.now());
                ps.setObject(4, LocalDateTime.now());
            });
        return ints.length;
    }
    
    @Override
    public int save(EnrolledUserEntity enrolledUserEntity) {
        String sql = "INSERT INTO enrolled_user (enrollment_id, user_id, created_date, updated_date) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            enrolledUserEntity.getEnrollmentId(),
            enrolledUserEntity.getUserId(),
            enrolledUserEntity.getCreatedDate(),
            enrolledUserEntity.getUpdatedDate()
        );
    }
    
    @Override
    public EnrolledUserEntity findById(Long id) {
        String sql = "SELECT * FROM enrolled_user WHERE id = ?";
        RowMapper<EnrolledUserEntity> rowMapper = (rs, rowNum) -> new EnrolledUserEntity(
            rs.getLong("id"),
            rs.getLong("enrollment_id"),
            rs.getLong("user_id"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    
    @Override
    public List<EnrolledUserEntity> findByEnrollmentId(Long enrollmentId) {
        String sql = "SELECT * FROM enrolled_user WHERE enrollment_id = ?";
        RowMapper<EnrolledUserEntity> rowMapper = (rs, rowNum) -> new EnrolledUserEntity(
            rs.getLong("id"),
            rs.getLong("enrollment_id"),
            rs.getLong("user_id"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
        );
        return jdbcTemplate.query(sql, rowMapper, enrollmentId);
    }
    
}
