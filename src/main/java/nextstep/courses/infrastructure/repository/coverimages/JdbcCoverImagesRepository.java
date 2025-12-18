package nextstep.courses.infrastructure.repository.coverimages;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enumerate.CoverImageType;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImages;
import nextstep.courses.domain.session.Dimensions;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("coverImagesRepository")
public class JdbcCoverImagesRepository implements CoverImagesRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImagesRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAll(Long sessionId, CoverImages coverImages) {
        List<CoverImage> coverImageList = coverImages.getCoverImages();
        String sql = "INSERT INTO cover_image (session_id, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int[][] ints = jdbcTemplate.batchUpdate(sql, coverImageList, coverImageList.size(),
            (PreparedStatement ps, CoverImage coverImage) -> {
                ps.setLong(1, sessionId);
                ps.setInt(2, coverImage.getSize());
                ps.setString(3, coverImage.getType().name());
                ps.setDouble(4, coverImage.getDimensions().getWidth());
                ps.setDouble(5, coverImage.getDimensions().getHeight());
                ps.setDouble(6, coverImage.getDimensions().getRatio());
                ps.setObject(7, LocalDateTime.now());
                ps.setObject(8, LocalDateTime.now());
            });
        return ints.length;
    }

    @Override
    public int save(Long sessionId, CoverImage coverImage) {
        String sql = "INSERT INTO cover_image (session_id, cover_image_size, cover_image_type, dimensions_width, dimensions_height, dimensions_ratio, created_date, updated_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            sessionId,
            coverImage.getSize(),
            coverImage.getType().name(),
            coverImage.getDimensions().getWidth(),
            coverImage.getDimensions().getHeight(),
            coverImage.getDimensions().getRatio(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    @Override
    public CoverImages findById(Long id) {
        String sql = "SELECT * FROM cover_image WHERE id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> mapToCoverImage(
            rs.getInt("cover_image_size"),
            rs.getString("cover_image_type"),
            rs.getDouble("dimensions_width"),
            rs.getDouble("dimensions_height")
        );
        CoverImage coverImage = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return new CoverImages(coverImage);
    }

    @Override
    public CoverImages findBySessionId(Long sessionId) {
        String sql = "SELECT * FROM cover_image WHERE session_id = ?";
        List<CoverImage> coverImageList = jdbcTemplate.query(
            sql,
            (rs, rowNum) -> mapToCoverImage(
                rs.getInt("cover_image_size"),
                rs.getString("cover_image_type"),
                rs.getDouble("dimensions_width"),
                rs.getDouble("dimensions_height")
            ),
            sessionId
        );
        return new CoverImages(coverImageList);
    }

    private CoverImage mapToCoverImage(int size, String type, double width, double height) {
        try {
            return new CoverImage(size, CoverImageType.valueOf(type), new Dimensions(width, height));
        } catch (CanNotCreateException e) {
            throw new IllegalStateException("Failed to map cover_images row to CoverImage", e);
        }
    }
}
