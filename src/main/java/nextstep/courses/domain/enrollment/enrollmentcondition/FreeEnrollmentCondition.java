package nextstep.courses.domain.enrollment.enrollmentcondition;

import java.util.Optional;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.payments.domain.Payment;

public class FreeEnrollmentCondition implements EnrollmentCondition {
    
    public static final FreeEnrollmentCondition INSTANCE = new FreeEnrollmentCondition();
    
    private FreeEnrollmentCondition() {}
    
    @Override
    public void isPaid(Payment payment) {
        return;
    }
    
    @Override
    public void isFull(EnrolledUsers enrolledUsers) {
        return;
    }
    
    @Override
    public Optional<Long> tuitionFee() {
        return Optional.empty();
    }
    
    @Override
    public Optional<Integer> maxEnrollment() {
        return Optional.empty();
    }
}
