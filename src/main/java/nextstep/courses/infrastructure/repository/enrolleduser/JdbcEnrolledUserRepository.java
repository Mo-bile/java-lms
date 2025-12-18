package nextstep.courses.infrastructure.repository.enrolleduser;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.enrollment.EnrolledUsers;
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
        List<Long> enrolledUserList = enrolledUsers.getEnrolledUserList();
        String sql = "INSERT INTO enrolled_user (enrollment_id, user_id, created_date, updated_date) VALUES (?, ?, ?, ?)";
        int[][] ints = jdbcTemplate.batchUpdate(sql, enrolledUserList, enrolledUserList.size(),
            (PreparedStatement ps, Long userId) -> {
                ps.setLong(1, enrollmentId);
                ps.setLong(2, userId);
                ps.setObject(3, LocalDateTime.now());
                ps.setObject(4, LocalDateTime.now());
            });
        return ints.length;
    }
    
    @Override
    public int save(Long enrollmentId, Long userId) {
        String sql = "INSERT INTO enrolled_user (enrollment_id, user_id, created_date, updated_date) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            enrollmentId,
            userId,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }
    
    @Override
    public EnrolledUsers findById(Long id) {
        String sql = "SELECT * FROM enrolled_user WHERE id = ?";
        RowMapper<EnrolledUsers> rowMapper = (rs, rowNum) -> new EnrolledUsers(
            rs.getLong("user_id")
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    
    @Override
    public EnrolledUsers findByEnrollmentId(Long enrollmentId) {
        String sql = "SELECT * FROM enrolled_user WHERE enrollment_id = ?";
        List<Long> userIds = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> rs.getLong("user_id"),
            enrollmentId
        );
        return new EnrolledUsers(userIds);
    }
    
}
