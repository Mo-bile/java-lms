package nextstep.courses.domain.enrollment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nextstep.courses.CanNotJoinException;

public class EnrolledUsers {
    
    private final List<Student> students;
    
    public EnrolledUsers() {
        this(List.of());
    }

    public EnrolledUsers(int size) {
        this(new ArrayList<>(Collections.nCopies(size, null)));
    }

    public EnrolledUsers(Long... enrolledUserList) {
        this(ofIds(enrolledUserList));
    }

    public EnrolledUsers(List<Student> students) {
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
        this.students.add(new Student(userId));
    }
    
    private void alreadyRegisterUser(Long userId) throws CanNotJoinException {
        if (students.stream().anyMatch(s -> s.getId().equals(userId))) {
            throw new CanNotJoinException("이미 수강신청이 완료된 유저입니다");
        }
    }
    
    public boolean isAlreadyExceed(int maxEnrollment) {
        return maxEnrollment == this.students.size();
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
        return Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(students);
    }
}
