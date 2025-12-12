package nextstep.courses.domain.builder;

import static nextstep.courses.domain.builder.FreeEnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentPolicy;
import nextstep.courses.domain.enumerate.EnrollmentType;

public class FreeEnrollmentBuilder {
    
    private EnrollmentType enrollmentType = EnrollmentType.FREE;
    private EnrollmentPolicy enrollmentPolicy = aFreeEnrollmentPolicyBuilder().build();
    
    public static FreeEnrollmentBuilder aFreeEnrollmentBuilder() {
        return new FreeEnrollmentBuilder();
    }
    
    private FreeEnrollmentBuilder() {}
    
    private FreeEnrollmentBuilder(FreeEnrollmentBuilder copy) {
        this.enrollmentType = copy.enrollmentType;
        this.enrollmentPolicy = copy.enrollmentPolicy;
    }
    
    public FreeEnrollmentBuilder withEnrollmentType(EnrollmentType enrollmentType) {
        this.enrollmentType = enrollmentType;
        return this;
    }
    
    public FreeEnrollmentBuilder withEnrollmentPolicy(EnrollmentPolicy enrollmentPolicy) {
        this.enrollmentPolicy = enrollmentPolicy;
        return this;
    }
    
    public Enrollment build() throws CanNotCreateException {
        return new Enrollment(enrollmentType, enrollmentPolicy);
    }
    
    public FreeEnrollmentBuilder but() {
        return new FreeEnrollmentBuilder(this);
    }
}