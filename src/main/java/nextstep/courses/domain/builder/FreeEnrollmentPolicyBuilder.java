package nextstep.courses.domain.builder;

import java.util.List;
import nextstep.courses.domain.EnrolledUsers;
import nextstep.courses.domain.EnrollmentPolicy;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.enrollmentcondition.EnrollmentCondition;
import nextstep.courses.domain.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enumerate.SessionStatusType;

public class FreeEnrollmentPolicyBuilder {
    
    private EnrollmentCondition enrollmentCondition = FreeEnrollmentCondition.INSTANCE;
    private EnrolledUsers enrolledUsers = new EnrolledUsers(List.of(1L, 2L, 3L, 4L, 5L));
    private SessionStatus status = new SessionStatus(SessionStatusType.RECRUITING);
    
    public static FreeEnrollmentPolicyBuilder aFreeEnrollmentPolicyBuilder() {
        return new FreeEnrollmentPolicyBuilder();
    }
    
    private FreeEnrollmentPolicyBuilder() {}
    
    private FreeEnrollmentPolicyBuilder(FreeEnrollmentPolicyBuilder copy) {
        this.enrollmentCondition = copy.enrollmentCondition;
        this.enrolledUsers = copy.enrolledUsers;
        this.status = copy.status;
    }
    
    public FreeEnrollmentPolicyBuilder withEnrollmentCondition(EnrollmentCondition enrollmentCondition) {
        this.enrollmentCondition = enrollmentCondition;
        return this;
    }
    
    public FreeEnrollmentPolicyBuilder withEnrolledUsers(EnrolledUsers enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
        return this;
    }
    
    public FreeEnrollmentPolicyBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.status = sessionStatus;
        return this;
    }
    
    public EnrollmentPolicy build() {
        return new EnrollmentPolicy(enrollmentCondition, enrolledUsers, status);
    }
    
    public FreeEnrollmentPolicyBuilder but() {
        return new FreeEnrollmentPolicyBuilder(this);
    }
    
}
