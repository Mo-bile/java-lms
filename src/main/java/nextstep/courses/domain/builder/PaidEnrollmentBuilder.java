package nextstep.courses.domain.builder;

import static nextstep.courses.domain.builder.PaidEnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentPolicy;
import nextstep.courses.domain.enumerate.EnrollmentType;

public class PaidEnrollmentBuilder {
    
    private EnrollmentType enrollmentType = EnrollmentType.PAID;
    private EnrollmentPolicy enrollmentPolicy = aPaidEnrollmentPolicyBuilder().build();
    
    public static PaidEnrollmentBuilder aPaidEnrollmentBuilder() {
        return new PaidEnrollmentBuilder();
    }
    
    private PaidEnrollmentBuilder() {}
    
    private PaidEnrollmentBuilder(PaidEnrollmentBuilder copy) {
        this.enrollmentType = copy.enrollmentType;
        this.enrollmentPolicy = copy.enrollmentPolicy;
    }
    
    public PaidEnrollmentBuilder withEnrollmentType(EnrollmentType enrollmentType) {
        this.enrollmentType = enrollmentType;
        return this;
    }
    
    public PaidEnrollmentBuilder withEnrollmentPolicy(EnrollmentPolicy enrollmentPolicy) {
        this.enrollmentPolicy = enrollmentPolicy;
        return this;
    }
    
    public Enrollment build() throws CanNotCreateException {
        return new Enrollment(enrollmentType, enrollmentPolicy);
    }
    
    public PaidEnrollmentBuilder but() {
        return new PaidEnrollmentBuilder(this);
    }
    
    
}
