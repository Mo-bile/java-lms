package nextstep.courses.domain.builder;

import java.util.List;
import nextstep.courses.domain.EnrolledUsers;
import nextstep.courses.domain.EnrollmentPolicy;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.enrollmentcondition.EnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.PaidEnrollmentCondition;
import nextstep.courses.domain.enumerate.SessionStatusType;

public class PaidEnrollmentPolicyBuilder {
    
    private EnrollmentCondition enrollmentCondition = new PaidEnrollmentCondition(10L, 10);
    private EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(1L, 2L, 3L, 4L, 5L));
    private SessionStatus status = new SessionStatus(SessionStatusType.RECRUITING);
    
    public static PaidEnrollmentPolicyBuilder aPaidEnrollmentPolicyBuilder() {
        return new PaidEnrollmentPolicyBuilder();
    }
    
    private PaidEnrollmentPolicyBuilder() {}
    
    private PaidEnrollmentPolicyBuilder(PaidEnrollmentPolicyBuilder copy) {
        this.enrollmentCondition = copy.enrollmentCondition;
        this.enrolledUsers = copy.enrolledUsers;
        this.status = copy.status;
    }
    
    public PaidEnrollmentPolicyBuilder withEnrollmentCondition(EnrollmentCondition enrollmentCondition) {
        this.enrollmentCondition = enrollmentCondition;
        return this;
    }
    
    public PaidEnrollmentPolicyBuilder withEnrolledUsers(EnrolledUsers enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
        return this;
    }
    
    public PaidEnrollmentPolicyBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.status = sessionStatus;
        return this;
    }
    
    public EnrollmentPolicy build() {
        return new EnrollmentPolicy(enrollmentCondition, enrolledUsers, status);
    }
    
    public PaidEnrollmentPolicyBuilder but() {
        return new PaidEnrollmentPolicyBuilder(this);
    }
    
}
