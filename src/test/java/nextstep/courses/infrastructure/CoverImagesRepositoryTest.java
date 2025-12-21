package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enumerate.CoverImageType;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImages;
import nextstep.courses.infrastructure.repository.coverimages.CoverImagesRepository;
import nextstep.courses.infrastructure.repository.coverimages.JdbcCoverImagesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class CoverImagesRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CoverImagesRepository coverImagesRepository;

    @BeforeEach
    public void setUp() {
        coverImagesRepository = new JdbcCoverImagesRepository(jdbcTemplate);
    }

    @Test
    void crud() throws CanNotCreateException {
        Long sessionId = 1L;
        CoverImages coverImages = new CoverImages(new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200), new CoverImage(1_500_000, CoverImageType.JPEG, 300, 200));

        int saveCount = coverImagesRepository.saveAll(sessionId, coverImages);
        assertThat(saveCount).isEqualTo(1);

        CoverImages result = coverImagesRepository.findBySessionId(sessionId);
        assertThat(result.getCoverImages()).isEqualTo(coverImages.getCoverImages());

    }

}
