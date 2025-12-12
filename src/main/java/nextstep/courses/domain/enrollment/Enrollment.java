package nextstep.courses.domain.enrollment;

import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.course.SessionApply;
import nextstep.courses.domain.enrollment.enrollmentcondition.FreeEnrollmentCondition;
import nextstep.courses.domain.enumerate.EnrollmentType;
import nextstep.payments.domain.Payment;

public class Enrollment {
    
    private final EnrollmentType type;
    private final EnrollmentPolicy policy;
    
    public Enrollment(EnrollmentType type) throws CanNotCreateException {
        this(type, new EnrollmentPolicy(FreeEnrollmentCondition.INSTANCE));
    }
    
    public Enrollment(EnrollmentType type, EnrollmentPolicy policy) throws CanNotCreateException {
        validate(type, policy);
        this.type = type;
        this.policy = policy;
    }
    
    private void validate(EnrollmentType type, EnrollmentPolicy policy) throws CanNotCreateException {
        if(policy.isNotCorrectBetween(type)) {
            throw new CanNotCreateException("강의타입과 정책이 일치하지 않습니다");
        }
    }
    
    public void enroll(SessionApply sessionApply) throws CanNotJoinException {
        policy.enroll(sessionApply);
    }
    
    public void enroll(Long userid) throws CanNotJoinException {
        enroll(new SessionApply(userid, null));
    }
    
    public void enroll(Long userid, Payment payment) throws CanNotJoinException {
        enroll(new SessionApply(userid, payment));
    }
    
    public boolean isPaid() {
        return this.type.isPaid();
    }
    
    public boolean isFree() {
        return this.type.isFree();
    }
}
