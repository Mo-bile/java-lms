package nextstep.courses.infrastructure.repository.course;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.infrastructure.entity.CourseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CourseEntity course) {
        String sql = "insert into course (title, creator_id, created_date) values(?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedDate());
    }

    @Override
    public CourseEntity findById(Long id) {
        String sql = "select id, title, creator_id, created_date, updated_date from course where id = ?";
        RowMapper<CourseEntity> rowMapper = (rs, rowNum) -> new CourseEntity(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
