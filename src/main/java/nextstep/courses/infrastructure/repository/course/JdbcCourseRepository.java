package nextstep.courses.infrastructure.repository.course;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.domain.course.Course;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.mapper.CourseMapper;
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
    public int save(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);
        String sql = "insert into course (title, creator_id, created_date) values(?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getTitle(), entity.getCreatorId(), entity.getCreatedDate());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_date, updated_date from course where id = ?";
        RowMapper<CourseEntity> rowMapper = (rs, rowNum) -> new CourseEntity(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        CourseEntity entity = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return CourseMapper.toModel(entity);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
