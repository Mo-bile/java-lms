package nextstep.courses.infrastructure.repository.course;

import nextstep.courses.infrastructure.entity.CourseEntity;

public interface CourseRepository {
    
    int save(CourseEntity course);
    
    CourseEntity findById(Long id);
}
