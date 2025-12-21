package nextstep.courses.domain.builder;

import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.domain.enrollment.EnrollmentPolicy;
import nextstep.courses.domain.enrollment.SessionStatus;
import nextstep.courses.domain.enrollment.enrollmentcondition.EnrollmentCondition;
import nextstep.courses.domain.enrollment.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enrollment.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.domain.enumerate.ProgressStatus;
import nextstep.courses.domain.enumerate.RecruitmentStatus;

public class EnrollmentPolicyBuilder {
    
    private EnrollmentCondition enrollmentCondition;
    private EnrolledUsers enrolledUsers = new EnrolledUsers(1L, 2L, 3L, 4L, 5L);
    private SessionStatus status = new SessionStatus(ProgressStatus.IN_PROGRESS, RecruitmentStatus.OPEN);
    
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
