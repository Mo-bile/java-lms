package nextstep.courses.domain.enrollment;

import java.util.Objects;
import nextstep.courses.domain.enumerate.ApprovalStatus;

public class Student {

    private final Long id;
    private ApprovalStatus approvalStatus;

    public Student(Long id) {
        this(id, ApprovalStatus.PENDING);
    }

    public Student(Long id, String approvalStatus) {
        this(id, ApprovalStatus.valueOf(approvalStatus));
    }

    public Student(Long id, ApprovalStatus approvalStatus) {
        this.id = id;
        this.approvalStatus = approvalStatus;
    }

    public Long getId() {
        return id;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void changeApprovalStatus(ApprovalStatus approvalStatus) {
        validate(approvalStatus);
        this.approvalStatus = approvalStatus;
    }

    private void validate(ApprovalStatus newStatus) {
        if (this.approvalStatus == newStatus) {
            throw new IllegalArgumentException("변경하는 수강상태가 같다");
        }

        if (isRevertToPending(newStatus)) {
            throw new IllegalArgumentException("수강 승인,취소 상태에서는 대기상태로 변경불가하다");
        }
    }

    private boolean isRevertToPending(ApprovalStatus newStatus) {
        return this.approvalStatus != ApprovalStatus.PENDING &&
            newStatus == ApprovalStatus.PENDING;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(id, student.id) && approvalStatus == student.approvalStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, approvalStatus);
    }
}
