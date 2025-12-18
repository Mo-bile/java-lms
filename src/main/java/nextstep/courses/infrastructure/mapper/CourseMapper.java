package nextstep.courses.infrastructure.mapper;

import nextstep.courses.domain.course.Course;
import nextstep.courses.infrastructure.entity.CourseEntity;

public final class CourseMapper {

    public static Course toModel(CourseEntity entity) {
        return new Course(
            entity.getId(),
            entity.getTitle(),
            entity.getCreatorId(),
            entity.getCreatedDate(),
            entity.getUpdatedDate()
        );
    }
    
    public static CourseEntity toEntity(Course course) {
        return new CourseEntity(
            course.getId(),
            course.getTitle(),
            course.getCreatorId(),
            course.getCreatedAt(),
            course.getUpdatedAt()
        );
    }
}
