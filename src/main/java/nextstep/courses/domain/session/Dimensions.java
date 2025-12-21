package nextstep.courses.domain.session;

import java.util.Objects;
import nextstep.courses.CanNotCreateException;

public class Dimensions {
    
    public static final double COVER_IMAGE_RATIO = 1.5;
    private final double width;
    private final double height;
    private final double ratio;
    
    public Dimensions(double width, double height) throws CanNotCreateException {
        validate(width, height);
        this.width = width;
        this.height = height;
        this.ratio = width / height;
    }
    
    private void validate(double width, double height) throws CanNotCreateException {
        if(width < 300) {
            throw new CanNotCreateException("이미지의 너비(width)는 300px 이상이어야 한다");
        }
        
        if(height < 200) {
            throw new CanNotCreateException("이미지의 높이(height)는 200px 이상이어야 한다");
        }
        
        if(width / height != COVER_IMAGE_RATIO) {
            throw new CanNotCreateException("이미지의 너비(width)와 높이(height)의 비율은 3:2 이어야 한다");
        }
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public double getRatio() {
        return ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dimensions that = (Dimensions) o;
        return Double.compare(width, that.width) == 0 && Double.compare(height, that.height) == 0 && Double.compare(ratio, that.ratio) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, ratio);
    }
}
