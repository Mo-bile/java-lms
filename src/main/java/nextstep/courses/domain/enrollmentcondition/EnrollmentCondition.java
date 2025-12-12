package nextstep.courses.domain.enrollmentcondition;

import java.util.Optional;
import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.EnrolledUsers;
import nextstep.payments.domain.Payment;

public interface EnrollmentCondition {
    
    void isPaid(Payment payment) throws CanNotJoinException;
    
    void isFull(EnrolledUsers enrolledUsers) throws CanNotJoinException;
    
    Optional<Long> tuitionFee();
    
    Optional<Integer> maxEnrollment();
    
}