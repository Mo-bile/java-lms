package nextstep.courses.domain.builder;

import java.util.List;
import nextstep.courses.domain.EnrolledUsers;
import nextstep.courses.domain.EnrollmentPolicy;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.enrollmentcondition.EnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.domain.enumerate.SessionStatusType;

public class EnrollmentPolicyBuilder {
    
    private EnrollmentCondition enrollmentCondition;
    private EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(1L, 2L, 3L, 4L, 5L));
    private SessionStatus status = new SessionStatus(SessionStatusType.RECRUITING);
    
    public static EnrollmentPolicyBuilder aFreeEnrollmentPolicyBuilder() {
        return new EnrollmentPolicyBuilder()
            .withEnrollmentCondition(FreeEnrollmentCondition.INSTANCE);
    }
    
    public static EnrollmentPolicyBuilder aPaidEnrollmentPolicyBuilder() {
        return new EnrollmentPolicyBuilder()
            .withEnrollmentCondition(new PaidEnrollmentCondition(10L, 10));
    }
    
    private EnrollmentPolicyBuilder() {}
    
    private EnrollmentPolicyBuilder(EnrollmentCondition enrollmentCondition) {
        this.enrollmentCondition = enrollmentCondition;
    }
    
    private EnrollmentPolicyBuilder(EnrollmentPolicyBuilder copy) {
        this.enrollmentCondition = copy.enrollmentCondition;
        this.enrolledUsers = copy.enrolledUsers;
        this.status = copy.status;
    }
    
    public EnrollmentPolicyBuilder withEnrollmentCondition(EnrollmentCondition enrollmentCondition) {
        this.enrollmentCondition = enrollmentCondition;
        return this;
    }
    
    public EnrollmentPolicyBuilder withEnrolledUsers(EnrolledUsers enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
        return this;
    }
    
    public EnrollmentPolicyBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.status = sessionStatus;
        return this;
    }
    
    public EnrollmentPolicy build() {
        return new EnrollmentPolicy(enrollmentCondition, enrolledUsers, status);
    }
    
    public EnrollmentPolicyBuilder but() {
        return new EnrollmentPolicyBuilder(this);
    }
    
}
