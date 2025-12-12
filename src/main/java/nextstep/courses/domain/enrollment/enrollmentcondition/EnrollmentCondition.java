package nextstep.courses.domain.enrollment.enrollmentcondition;

import java.util.Optional;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.payments.domain.Payment;

public interface EnrollmentCondition {
    
    void isPaid(Payment payment) throws CanNotJoinException;
    
    void isFull(EnrolledUsers enrolledUsers) throws CanNotJoinException;
    
    Optional<Long> tuitionFee();
    
    Optional<Integer> maxEnrollment();
    
}