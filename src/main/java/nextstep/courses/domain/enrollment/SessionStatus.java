package nextstep.courses.domain.enrollment;

import nextstep.courses.CanNotJoinException;
import nextstep.courses.domain.enumerate.ProgressStatus;
import nextstep.courses.domain.enumerate.RecruitmentStatus;

public class SessionStatus {

    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;

    public SessionStatus() {
        this(ProgressStatus.PREPARATION, RecruitmentStatus.CLOSED);
    }

    public SessionStatus(ProgressStatus progressStatus) {
        this(progressStatus, RecruitmentStatus.CLOSED);
    }

    public SessionStatus(RecruitmentStatus recruitmentStatus) {
        this(ProgressStatus.PREPARATION, recruitmentStatus);
    }

    public SessionStatus(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
    }
    
    public void isApplyStatus() throws CanNotJoinException {
        if (this.recruitmentStatus == RecruitmentStatus.CLOSED) {
            throw new CanNotJoinException("모집 중 일때만 신청 가능합니다");
        }
    }

    public ProgressStatus getSessionStatusType() {
        return progressStatus;
    }

    public ProgressStatus getSessionProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }
}
