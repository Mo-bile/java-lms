package nextstep.courses.infrastructure.repository.enrollment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.mapper.EnrollmentMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
        String sql = "INSERT INTO enrollment (session_id, type, tuition_fee, max_enrollment, progress_status, recruitment_status, created_date, updated_date) values (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
            enrollmentEntity.getSessionId(),
            enrollmentEntity.getType(),
            enrollmentEntity.getTuitionFee(),
            enrollmentEntity.getMaxEnrollment(),
            enrollmentEntity.getProgressStatus(),
            enrollmentEntity.getRecruitmentStatus(),
            LocalDateTime.now(),
            LocalDateTime.now());
    }
    
    @Override
    public Enrollment findById(Long id) {
        String sql = "SELECT e.*, eu.user_id, eu.approval_status FROM enrollment e LEFT JOIN enrolled_user eu ON e.id = eu.enrollment_id WHERE e.id = ?";
        return queryEnrollmentWithUsers(sql, id);
    }

    @Override
    public Enrollment findBySessionId(Long sessionId) {
        String sql = "SELECT e.*, eu.user_id, eu.approval_status FROM enrollment e LEFT JOIN enrolled_user eu ON e.id = eu.enrollment_id WHERE e.session_id = ?";
        return queryEnrollmentWithUsers(sql, sessionId);
    }

    @Override
    public Long findIdBySessionId(Long sessionId) {
        String sql = "SELECT id FROM enrollment WHERE session_id = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, sessionId);
    }

    private Enrollment queryEnrollmentWithUsers(String sql, Long param) {
        List<Student> students = new ArrayList<>();
        EnrollmentEntity enrollmentEntity = jdbcTemplate.query(
            sql,
            ps -> ps.setLong(1, param),
            (ResultSetExtractor<EnrollmentEntity>) rs -> mapEnrollmentWithUsers(rs, students)
        );
        EnrolledUsers enrolledUsers = new EnrolledUsers(students);
        return EnrollmentMapper.toModelWithEnrolledUsers(enrollmentEntity, enrolledUsers);
    }

    private EnrollmentEntity mapEnrollmentWithUsers(ResultSet rs, List<Student> students) throws SQLException {
        EnrollmentEntity enrollment = null;
        while (rs.next()) {
            if (enrollment == null) {
                enrollment = new EnrollmentEntity(
                    rs.getLong("session_id"),
                    rs.getLong("id"),
                    rs.getString("type"),
                    rs.getLong("tuition_fee"),
                    rs.getInt("max_enrollment"),
                    rs.getString("progress_status"),
                    rs.getString("recruitment_status"),
                    toLocalDateTime(rs.getTimestamp("created_date")),
                    toLocalDateTime(rs.getTimestamp("updated_date"))
                );
            }
            Long userId = rs.getObject("user_id", Long.class);
            if (userId != null) {
                String approvalStatus = rs.getString("approval_status");
                Student student = new Student(userId, approvalStatus);
                students.add(student);
            }
        }
        if (enrollment == null) {
            throw new IllegalStateException("Enrollment not found");
        }
        return enrollment;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
