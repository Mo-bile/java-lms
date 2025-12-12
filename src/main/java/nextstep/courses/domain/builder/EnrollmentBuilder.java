package nextstep.courses.domain.builder;

import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aFreeEnrollmentPolicyBuilder;
import static nextstep.courses.domain.builder.EnrollmentPolicyBuilder.aPaidEnrollmentPolicyBuilder;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentPolicy;
import nextstep.courses.domain.enumerate.EnrollmentType;

public class EnrollmentBuilder {
    
    private EnrollmentType enrollmentType;
    private EnrollmentPolicy enrollmentPolicy;
    
    public static EnrollmentBuilder aFreeEnrollmentBuilder() {
        return new EnrollmentBuilder()
            .withEnrollmentType(EnrollmentType.FREE)
            .withEnrollmentPolicy(aFreeEnrollmentPolicyBuilder().build());
    }
    
    public static EnrollmentBuilder aPaidEnrollmentBuilder() {
        return new EnrollmentBuilder()
            .withEnrollmentType(EnrollmentType.PAID)
            .withEnrollmentPolicy(aPaidEnrollmentPolicyBuilder().build());
    }
    
    private EnrollmentBuilder() {}
    
    private EnrollmentBuilder(EnrollmentBuilder copy) {
        this.enrollmentType = copy.enrollmentType;
        this.enrollmentPolicy = copy.enrollmentPolicy;
    }
    
    public EnrollmentBuilder withEnrollmentType(EnrollmentType enrollmentType) {
        this.enrollmentType = enrollmentType;
        return this;
    }
    
    public EnrollmentBuilder withEnrollmentPolicy(EnrollmentPolicy enrollmentPolicy) {
        this.enrollmentPolicy = enrollmentPolicy;
        return this;
    }
    
    public Enrollment build() throws CanNotCreateException {
        return new Enrollment(enrollmentType, enrollmentPolicy);
    }
    
    public EnrollmentBuilder but() {
        return new EnrollmentBuilder(this);
    }
}