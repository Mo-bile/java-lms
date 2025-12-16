package nextstep.courses.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enumerate.CoverImageType;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Dimensions;
import nextstep.courses.domain.session.Duration;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionBody;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;

public final class SessionMapper {

    public static Session toModelWithEnrollment(SessionEntity entity, EnrollmentEntity enrollmentEntity, List<EnrolledUserEntity> enrolledUserList) {
        try {
            return new Session(
                entity.getId(),
                entity.getCreatorId(),
                new SessionBody(entity.getTitle(), entity.getContent()),
                new Duration(entity.getStartDate(), entity.getEndDate()),
                createCoverImage(entity),
                EnrollmentMapper.toModelWithEnrolledUsers(enrollmentEntity, enrolledUserList),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
            );
        } catch (CanNotCreateException e) {
            throw new MappingException("Failed to map SessionEntity to Session", e);
        }
    }

    public static Session toModelByJoin(
        SessionEntity entity,
        EnrollmentEntity enrollmentEntity,
        List<EnrolledUserEntity> enrolledUserList
    ) {
        return toModelWithEnrollment(entity, enrollmentEntity, enrolledUserList);
    }

    public static List<Session> toModels(List<SessionEntity> sessionEntities) {
        return sessionEntities.stream()
            .map(SessionMapper::toModel)
            .collect(Collectors.toList());
    }

    public static Session toModel(SessionEntity entity) {
        try {
            return new Session(
                entity.getId(),
                entity.getCreatorId(),
                new SessionBody(entity.getTitle(), entity.getContent()),
                new Duration(entity.getStartDate(), entity.getEndDate()),
                createCoverImage(entity),
                entity.getCreatedDate(),
                entity.getUpdatedDate()
            );
        } catch (CanNotCreateException e) {
            throw new MappingException("Failed to map SessionEntity to Session", e);
        }
    }

    public static Session attachEnrollment(Session session, nextstep.courses.domain.enrollment.Enrollment enrollment) {
        return new Session(
            session.getId(),
            session.getCreatorId(),
            session.getBody(),
            session.getDuration(),
            session.getCoverImage(),
            enrollment,
            session.getCreatedDate(),
            session.getUpdatedDate()
        );
    }

    private static CoverImage createCoverImage(SessionEntity entity) throws CanNotCreateException {
        return new CoverImage(
            entity.getCoverImageSize(),
            CoverImageType.valueOf(entity.getCoverImageType()),
            new Dimensions(entity.getDimensionsWidth(), entity.getDimensionsHeight())
        );
    }

    public static List<SessionEntity> toEntityList(Long courseId, List<Session> sessions) {
        return sessions.stream()
            .map(session -> toEntity(courseId, session))
            .collect(Collectors.toList());
    }
    
    public static SessionEntity toEntity(Long courseId, Session session) {
        return new SessionEntity(
            courseId,
            session.getId(),
            session.getCreatorId(),
            session.getBody().getTitle(),
            session.getBody().getContent(),
            session.getDuration().getStartDate(),
            session.getDuration().getEndDate(),
            session.getCoverImage().getSize(),
            session.getCoverImage().getType().toString(),
            session.getCoverImage().getDimensions().getWidth(),
            session.getCoverImage().getDimensions().getHeight(),
            session.getCoverImage().getDimensions().getRatio(),
            session.getCreatedDate(),
            session.getUpdatedDate()
        );
    }
}
