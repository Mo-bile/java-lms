package nextstep.courses.domain.session;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CoverImages {

    private final List<CoverImage> coverImages;

    public CoverImages(CoverImage... coverImages) {
        this.coverImages = Arrays.asList(coverImages);
    }

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public List<CoverImage> getCoverImages() {
        return coverImages;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoverImages that = (CoverImages) o;
        return Objects.equals(coverImages, that.coverImages);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coverImages);
    }
}
