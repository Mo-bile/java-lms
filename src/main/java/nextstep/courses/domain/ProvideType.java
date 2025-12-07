package nextstep.courses.domain;

public enum ProvideType {
    PAID("paid", true),
    FREE("free", false);
    
    private String provideType;
    private boolean isExistTuitionFee;
    
    ProvideType(String provideType, boolean isExistTuitionFee) {}
}
