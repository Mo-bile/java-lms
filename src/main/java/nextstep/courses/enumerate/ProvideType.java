package nextstep.courses.enumerate;

public enum ProvideType {
    PAID("paid", true),
    FREE("free", false);
    
    private String provideType;
    private boolean isExistTuitionFee;
    
    ProvideType(String provideType, boolean isExistTuitionFee) {}
    
    public boolean isFree() {
        return this == ProvideType.FREE;
    }
    
    public boolean isPaid() {
        return this == ProvideType.PAID;
    }
}
