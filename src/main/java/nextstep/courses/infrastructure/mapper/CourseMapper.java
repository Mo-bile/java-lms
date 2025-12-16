package nextstep.courses.infrastructure.mapper;

import java.util.List;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;

public final class CourseMapper {

    public static Course toModelByJoin(
        CourseEntity entity,
        List<SessionEntity> sessionEntities,
        EnrollmentEntity enrollmentEntity,
        List<EnrolledUserEntity> enrolledUserList
    ) {
        return new Course(
            entity.getId(),
            entity.getTitle(),
            entity.getCreatorId(),
            new Sessions(SessionMapper.toModelsWithEnrollment(sessionEntities, enrollmentEntity, enrolledUserList)),
            entity.getCreatedDate(),
            entity.getUpdatedDate()
        );
    }
    
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
