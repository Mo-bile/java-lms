package nextstep.courses.infrastructure.repository.coverimages;

import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.CoverImages;

public interface CoverImagesRepository {

    int saveAll(Long sessionId, CoverImages coverImages);

    int save(Long sessionId, CoverImage coverImage);

    CoverImages findById(Long id);

    CoverImages findBySessionId(Long sessionId);
}
