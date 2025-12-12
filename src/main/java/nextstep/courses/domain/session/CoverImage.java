package nextstep.courses.domain.session;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enumerate.CoverImageType;

public class CoverImage {
    
    public static final int ONE_MEGA_BYTE = 1_000_000; // 1mb 는 1_000_000 byte 로 가정
    private final int size; // byte
    private final CoverImageType type;
    private final Dimensions dimensions;
    
    public CoverImage(int size, CoverImageType type, double width, double height) throws CanNotCreateException {
        this(size, type, new Dimensions(width, height));
    }
    
    public CoverImage(int size, String type, Dimensions dimensions) throws CanNotCreateException {
        this(size, CoverImageType.valueOfIgnoreCase(type), dimensions);
    }
    
    public CoverImage(int size, CoverImageType type, Dimensions dimensions) throws CanNotCreateException {
        validate(size);
        this.size = size;
        this.type = type;
        this.dimensions = dimensions;
    }
    
    private void validate(int size) throws CanNotCreateException {
        if(size < ONE_MEGA_BYTE) {
            throw new CanNotCreateException("커버 이미지의 크기는 1MB 이상이어야 한다");
        }
    }
    
}
