package nextstep.courses.domain;

import java.util.Objects;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.CanNotJoinException;

public class Provide {
    
    private final ProvideType type;
    private int enrolledCount;
    private final int tuitionFee;
    
    public Provide(ProvideType type, int enrolledCount) throws CanNotCreateException {
        this(type, enrolledCount, 0);
    }
    
    public Provide(ProvideType type, int enrolledCount, int tuitionFee) throws CanNotCreateException {
        valdate(type, tuitionFee);
        this.type = type;
        this.enrolledCount = enrolledCount;
        this.tuitionFee = tuitionFee;
    }
    
    private void valdate(ProvideType type, int money) throws CanNotCreateException {
        if(isFreeAndNonZeroMoney(type, money)) {
            throw new CanNotCreateException("무료 강의에는 수강료는 0 이어야 한다");
        }
        
        if(isPaidAndZeroMoney(type, money)) {
            throw new CanNotCreateException("유료 강의에는 수강료는 1 이상이다");
        }
    }
    
    private boolean isFreeAndNonZeroMoney(ProvideType type, int money) {
        return type == ProvideType.FREE && money != 0;
    }
    
    private boolean isPaidAndZeroMoney(ProvideType type, int money) {
        return type == ProvideType.PAID && money == 0;
    }
    
    public void apply(int pay, int maxEnrollment) throws CanNotJoinException {
        isCorrectPay(pay);
        isAvailableEnroll(maxEnrollment);
        this.enrolledCount += 1;
    }
    
    private void isCorrectPay(int pay) throws CanNotJoinException {
        if(this.tuitionFee != pay) {
            throw new CanNotJoinException("지불한 금액과 수강료 금액이 다르다");
        }
    }
    
    private void isAvailableEnroll(int maxEnrollment) throws CanNotJoinException {
        if(this.enrolledCount >= maxEnrollment) {
            throw new CanNotJoinException("이미 정원을 초과했다");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Provide provide = (Provide) o;
        return enrolledCount == provide.enrolledCount && tuitionFee == provide.tuitionFee && type == provide.type;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(type, enrolledCount, tuitionFee);
    }
}
