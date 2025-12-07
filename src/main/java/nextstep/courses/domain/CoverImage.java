package nextstep.courses.domain;

import nextstep.courses.CanNotCreateException;

public class CoverImage {
    
    public static final double COVER_IMAGE_RATIO = 1.5;
    public static final int ONE_MEGA_BYTE = 1_000_000; // 1mb 는 1_000_000 byte 로 가정
    private final int size; // byte
    private final CoverImageType type;
    private final double width;
    private final double height;
    private final double ratio;
    
    public CoverImage(int size, String type, double width, double height) throws CanNotCreateException {
        this(size, CoverImageType.valueOfIgnoreCase(type), width, height);
    }
    
    public CoverImage(int size, CoverImageType type, double width, double height) throws CanNotCreateException {
        validate(size, width, height);
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
        this.ratio = width / height;
    }
    
    private void validate(int size, double width, double height) throws CanNotCreateException {
        if(size < ONE_MEGA_BYTE) {
            throw new CanNotCreateException("커버 이미지의 크기는 1MB 이상이어야 한다");
        }
        
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
    
}
