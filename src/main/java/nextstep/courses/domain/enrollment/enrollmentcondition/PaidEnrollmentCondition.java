package nextstep.courses.domain.enrollment.enrollmentcondition;

import java.util.Optional;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.payments.domain.Payment;

public class PaidEnrollmentCondition implements EnrollmentCondition {
    
    private final long tuitionFee;
    private final int maxEnrollment;
    
    public PaidEnrollmentCondition(long tuitionFee, int maxEnrollment) {
        validate(tuitionFee, maxEnrollment);
        this.tuitionFee = tuitionFee;
        this.maxEnrollment = maxEnrollment;
    }
    
    private void validate(long tuitionFee, int maxEnrollment) {
        if(tuitionFee <= 0) {
            throw new IllegalArgumentException("수강료는 양수이어야 한다");
        }
        if(maxEnrollment <= 0) {
            throw new IllegalArgumentException("정원은 양수이어야 한다");
        }
    }
    
    @Override
    public void isPaid(Payment payment) throws CanNotJoinException {
        if(payment.isNotSameAmount(this.tuitionFee)) {
            throw new CanNotJoinException("지불한 금액과 수강료 금액이 다르다");
        }
    }
    
    @Override
    public void isFull(EnrolledUsers enrolledUsers) throws CanNotJoinException {
        if(enrolledUsers.isAlreadyExceed(this.maxEnrollment)) {
            throw new CanNotJoinException("이미 정원을 초과했다");
        }
    }
    
    @Override
    public Optional<Long> tuitionFee() {
        return Optional.of(tuitionFee);
    }
    
    @Override
    public Optional<Integer> maxEnrollment() {
        return Optional.of(maxEnrollment);
    }
}
