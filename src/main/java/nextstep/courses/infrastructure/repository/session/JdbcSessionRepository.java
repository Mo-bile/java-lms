package nextstep.courses.infrastructure.repository.session;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.infrastructure.entity.SessionEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
    
    @Override
    public int save(SessionEntity session) {
        String sql = "INSERT INTO session (course_id, creator_id, title, contents, start_date, end_date, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourseId(), session.getCreatorId(), session.getTitle(), session.getContent(), session.getStartDate(), session.getEndDate(), session.getCoverImageSize(), session.getCoverImageType(), session.getDimensionsWidth(), session.getDimensionsHeight(), session.getDimensionsRatio(), session.getCreatedDate(), session.getUpdatedDate());
    }
    
    @Override
    public SessionEntity findById(Long id) {
        String sql = "SELECT * FROM session WHERE id = ?";
        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
            rs.getLong("id"),
            rs.getLong("course_id"),
            rs.getString("creator_id"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("start_date").toLocalDate(),
            rs.getDate("end_date").toLocalDate(),
            rs.getInt("cover_image_size"),
            rs.getString("cover_image_type"),
            rs.getDouble("dimensions_width"),
            rs.getDouble("dimensions_height"),
            rs.getDouble("dimensions_ratio"),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    
    @Override
    public List<SessionEntity> findAllByCourseId(Long courseId) {
        String sql = "SELECT * FROM session WHERE course_id = ?";
        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
            rs.getLong("id"),
            rs.getLong("course_id"),
            rs.getString("creator_id"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("start_date").toLocalDate(),
            rs.getDate("end_date").toLocalDate(),
            rs.getInt("cover_image_size"),
            rs.getString("cover_image_type"),
            rs.getDouble("dimensions_width"),
            rs.getDouble("dimensions_height"),
            rs.getDouble("dimensions_ratio"),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }
    
    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if(timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
