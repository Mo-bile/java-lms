package nextstep.courses.infrastructure.mapper;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Duration;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionBody;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;

public class SessionMapper {
    
    public static List<Session> toModels(List<SessionEntity> sessionEntities, EnrollmentEntity enrollmentEntity, List<EnrolledUserEntity> enrolledUserList) throws CanNotCreateException {
        List<Session> sessions = new ArrayList<>();
        for(SessionEntity sessionEntity: sessionEntities) {
            sessions.add(toModel(sessionEntity, enrollmentEntity, enrolledUserList));
        }
        return sessions;
    }
    
    public static Session toModel(SessionEntity entity, EnrollmentEntity enrollmentEntity, List<EnrolledUserEntity> enrolledUserList) throws CanNotCreateException {
        return new Session(
            entity.getId(),
            entity.getCreatorId(),
            new SessionBody(entity.getTitle(), entity.getContent()),
            new Duration(entity.getStartDate(), entity.getEndDate()),
            new CoverImage(
                entity.getCoverImageSize(),
                entity.getCoverImageType(),
                entity.getDimensionsWidth(),
                entity.getDimensionsHeight()),
            EnrollmentMapper.toModel(enrollmentEntity, enrolledUserList),
            entity.getCreatedDate(),
            entity.getUpdatedDate());
        
    }
    
    public static List<Long> toEntitys(List<Session> sessions) {
        List<Long> sessionEntities = new ArrayList<>();
        for(Session session: sessions) {
            sessionEntities.add(session.getId());
        }
        return sessionEntities;
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
            session.getCoverImage().getType(),
            session.getCoverImage().getDimensions().getWidth(),
            session.getCoverImage().getDimensions().getHeight(),
            session.getCoverImage().getDimensions().getRatio(),
            session.getCreatedDate(),
            session.getUpdatedDate()
        );
    }
}
