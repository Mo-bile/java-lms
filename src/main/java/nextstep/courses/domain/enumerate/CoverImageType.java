package nextstep.courses.domain.enumerate;

import nextstep.courses.CanNotCreateException;

public enum CoverImageType {
    GIF("gif"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    SVG("svg");
    
    CoverImageType(String type) {}
    
    public static CoverImageType valueOfIgnoreCase(String type) throws CanNotCreateException {
        validate(type);
        return Enum.valueOf(CoverImageType.class, type);
    }
    
    private static void validate(String type) throws CanNotCreateException {
        if(isNotCoverImageType(type)) {
            throw new CanNotCreateException("커버 이미지의 타입은 gif, jpg(jpeg), png, svg 만 가능하다");
        }
    }
    
    private static boolean isNotCoverImageType(String type) {
        return !(type.equals("gif") || type.equals("jpg") || type.equals("jpeg") || type.equals("png") || type.equals("svg"));
    }
}
