package nextstep.courses.domain.enrollment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nextstep.courses.CanNotJoinException;

public class EnrolledUsers {
    
    private final List<Long> enrolledUserList;
    private final List<Student> students;
    
    public EnrolledUsers() {
        this(List.of(), List.of());
    }
    
    public EnrolledUsers(int size) {
        this(new ArrayList<>(Collections.nCopies(size, null)), new ArrayList<>(Collections.nCopies(size, null)));
    }

    public EnrolledUsers(List<Student> students, Long... enrolledUserList) {
        this(List.of(enrolledUserList), students);
    }

    public EnrolledUsers(List<Long> enrolledUserList) {
        this(enrolledUserList, ofIds(enrolledUserList));
    }

    public EnrolledUsers(Long... enrolledUserList) {
        this(List.of(enrolledUserList), ofIds(enrolledUserList));
    }

    public EnrolledUsers(List<Long> enrolledUserList, List<Student> students) {
        this.enrolledUserList = new ArrayList<>(enrolledUserList);
        this.students = new ArrayList<>(students);
    }

    private static List<Student> ofIds(Long... ids) {
        return Stream.of(ids)
            .map(Student::new)
            .collect(Collectors.toList());
    }

    private static List<Student> ofIds(List<Long> ids) {
        return ids.stream()
            .map(Student::new)
            .collect(Collectors.toList());
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
    
    public List<Long> getEnrolledUserList() {
        return enrolledUserList;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnrolledUsers that = (EnrolledUsers) o;
        return Objects.equals(enrolledUserList, that.enrolledUserList) && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrolledUserList, students);
    }
}
