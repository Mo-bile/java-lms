package nextstep.courses.infrastructure.repository.enrolleduser;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.mapper.EnrolledUserMapper;
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
    public int saveAll(Long enrollmentId, EnrolledUsers enrolledUsers) {
        List<EnrolledUserEntity> enrolledUserEntities = EnrolledUserMapper.toEntityList(enrollmentId, enrolledUsers);
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
    public int save(Long enrollmentId, Long userId) {
        EnrolledUserEntity enrolledUserEntity = EnrolledUserMapper.toEntity(enrollmentId, userId);
        String sql = "INSERT INTO enrolled_user (enrollment_id, user_id, created_date, updated_date) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            enrolledUserEntity.getEnrollmentId(),
            enrolledUserEntity.getUserId(),
            enrolledUserEntity.getCreatedDate(),
            enrolledUserEntity.getUpdatedDate()
        );
    }
    
    @Override
    public EnrolledUsers findById(Long id) {
        String sql = "SELECT * FROM enrolled_user WHERE id = ?";
        RowMapper<EnrolledUserEntity> rowMapper = (rs, rowNum) -> new EnrolledUserEntity(
            rs.getLong("enrollment_id"),
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
        );
        EnrolledUserEntity entity = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return EnrolledUserMapper.toDomain(List.of(entity));
    }
    
    @Override
    public EnrolledUsers findByEnrollmentId(Long enrollmentId) {
        String sql = "SELECT * FROM enrolled_user WHERE enrollment_id = ?";
        RowMapper<EnrolledUserEntity> rowMapper = (rs, rowNum) -> new EnrolledUserEntity(
            rs.getLong("enrollment_id"),
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("updated_date").toLocalDateTime()
        );
        List<EnrolledUserEntity> entities = jdbcTemplate.query(sql, rowMapper, enrollmentId);
        return EnrolledUserMapper.toDomain(entities);
    }
    
}
