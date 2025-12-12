package nextstep.courses.domain.enrollment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nextstep.courses.CanNotJoinException;

public class EnrolledUsers {
    
    private final List<Long> enrolledUserList;
    
    public EnrolledUsers() {
        this(List.of());
    }
    
    public EnrolledUsers(int size) {
        this(new ArrayList<>(Collections.nCopies(size, null)));
    }
    
    public EnrolledUsers(Long... enrolledUserList) {
        this(List.of(enrolledUserList));
    }
    
    public EnrolledUsers(List<Long> enrolledUserList) {
        this.enrolledUserList = new ArrayList<>(enrolledUserList);
    }
    
    public void registerUserId(Long userId) throws CanNotJoinException {
        this.alreadyRegisterUser(userId);
        this.enrolledUserList.add(userId);
    }
    
    private void alreadyRegisterUser(Long userId) throws CanNotJoinException {
        if(enrolledUserList.contains(userId)) {
            throw new CanNotJoinException("이미 수강신청이 완료된 유저입니다");
        }
    }
    
    public boolean isAlreadyExceed(int maxEnrollment) {
        return maxEnrollment <= this.enrolledUserList.size();
    }
    
}
