package nextstep.courses.domain.enumerate;

public enum EnrollmentType {
    PAID("paid", true),
    FREE("free", false);
    
    private String provideType;
    private boolean isExistTuitionFee;
    
    EnrollmentType(String provideType, boolean isExistTuitionFee) {}
    
    public boolean isFree() {
        return this == EnrollmentType.FREE;
    }
    
    public boolean isPaid() {
        return this == EnrollmentType.PAID;
    }
}
