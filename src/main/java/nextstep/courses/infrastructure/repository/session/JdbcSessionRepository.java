package nextstep.courses.infrastructure.repository.session;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.entity.SessionEntity;
import nextstep.courses.infrastructure.mapper.SessionMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    
    private final JdbcTemplate jdbcTemplate;
    
    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
    
    @Override
    public int save(Long courseId, Session session) {
        SessionEntity entity = SessionMapper.toEntity(courseId, session);
        String sql = "INSERT INTO session (course_id, creator_id, title, contents, start_date, end_date, created_date, updated_date) values (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getCourseId(), entity.getCreatorId(), entity.getTitle(), entity.getContent(), entity.getStartDate(), entity.getEndDate(), entity.getCreatedDate(),
            entity.getUpdatedDate());
    }
    
    @Override
    public Session findById(Long id) {
        String sql = "SELECT * FROM session WHERE id = ?";
        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
            rs.getLong("course_id"),
            rs.getLong("id"),
            rs.getString("creator_id"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("start_date").toLocalDate(),
            rs.getDate("end_date").toLocalDate(),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        SessionEntity entity = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return SessionMapper.toModel(entity);
    }
    
    @Override
    public List<Session> findAllByCourseId(Long courseId) {
        String sql = "SELECT * FROM session WHERE course_id = ?";
        RowMapper<SessionEntity> rowMapper = (rs, rowNum) -> new SessionEntity(
            rs.getLong("course_id"),
            rs.getLong("id"),
            rs.getString("creator_id"),
            rs.getString("title"),
            rs.getString("contents"),
            rs.getDate("start_date").toLocalDate(),
            rs.getDate("end_date").toLocalDate(),
            toLocalDateTime(rs.getTimestamp("created_date")),
            toLocalDateTime(rs.getTimestamp("updated_date"))
        );
        return jdbcTemplate.query(sql, rowMapper, courseId)
            .stream()
            .map(SessionMapper::toModel)
            .collect(Collectors.toList());
    }
    
    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if(timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
