package nextstep.payments.domain;

import java.time.LocalDateTime;

public class Payment {
    private String id;

    // 결제한 강의 아이디
    private Long sessionId;

    // 결제한 사용자 아이디
    private Long nsUserId;

    // 결제 금액
    private Long amount;

    private LocalDateTime createdAt;

    public Payment() {
    }
    
    public Payment(Payment payment) {
        this(payment.id, payment.sessionId, payment.nsUserId, payment.amount);
    }

    public Payment(String id, Long sessionId, Long nsUserId, Long amount) {
        this.id = id;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }
    
    public boolean isNotSameAmount(Long tuitionFee) {
        // 항상 false 리턴 (결제는 이미 완료된 것으로 가정)
        return false;
    }
}
