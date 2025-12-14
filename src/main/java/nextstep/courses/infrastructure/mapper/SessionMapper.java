package nextstep.courses.infrastructure.mapper;

import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CanNotCreateException;
import nextstep.courses.domain.enumerate.CoverImageType;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Duration;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionBody;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;
import nextstep.courses.infrastructure.entity.EnrollmentEntity;
import nextstep.courses.infrastructure.entity.SessionEntity;

public class SessionMapper {
    
    public static List<Session> toModelsByJoin(List<SessionEntity> sessionEntities, EnrollmentEntity enrollmentEntity, List<EnrolledUserEntity> enrolledUserList) throws CanNotCreateException {
        List<Session> sessions = new ArrayList<>();
        for(SessionEntity sessionEntity: sessionEntities) {
            sessions.add(toModelByJoin(sessionEntity, enrollmentEntity, enrolledUserList));
        }
        return sessions;
    }
    
    public static Session toModelByJoin(SessionEntity entity, EnrollmentEntity enrollmentEntity, List<EnrolledUserEntity> enrolledUserList) throws CanNotCreateException {
        return new Session(
            entity.getId(),
            entity.getCreatorId(),
            new SessionBody(entity.getTitle(), entity.getContent()),
            new Duration(entity.getStartDate(), entity.getEndDate()),
            new CoverImage(
                entity.getCoverImageSize(),
                CoverImageType.valueOf(entity.getCoverImageType()),
                entity.getDimensionsWidth(),
                entity.getDimensionsHeight()),
            EnrollmentMapper.toModelByJoin(enrollmentEntity, enrolledUserList),
            entity.getCreatedDate(),
            entity.getUpdatedDate());
        
    }
    
    public static List<Session> toModels(List<SessionEntity> sessionEntities) throws CanNotCreateException {
        List<Session> sessions = new ArrayList<>();
        for(SessionEntity sessionEntity: sessionEntities) {
            sessions.add(toModel(sessionEntity));
        }
        return sessions;
    }
    
    public static Session toModel(SessionEntity entity) throws CanNotCreateException {
        return new Session(
            entity.getId(),
            entity.getCreatorId(),
            new SessionBody(entity.getTitle(), entity.getContent()),
            new Duration(entity.getStartDate(), entity.getEndDate()),
            new CoverImage(
                entity.getCoverImageSize(),
                CoverImageType.valueOf(entity.getCoverImageType()),
                entity.getDimensionsWidth(),
                entity.getDimensionsHeight()),
            entity.getCreatedDate(),
            entity.getUpdatedDate());
        
    }
    
    public static List<SessionEntity> toEntities(Long courseId, List<Session> sessions) {
        List<SessionEntity> sessionEntities = new ArrayList<>();
        for(Session session: sessions) {
            sessionEntities.add(toEntity(courseId, session));
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
            session.getCoverImage().getType().toString(),
            session.getCoverImage().getDimensions().getWidth(),
            session.getCoverImage().getDimensions().getHeight(),
            session.getCoverImage().getDimensions().getRatio(),
            session.getCreatedDate(),
            session.getUpdatedDate()
        );
    }
}
