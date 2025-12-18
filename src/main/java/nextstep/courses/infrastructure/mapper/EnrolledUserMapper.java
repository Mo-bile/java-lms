package nextstep.courses.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;
import nextstep.courses.domain.enrollment.EnrolledUsers;
import nextstep.courses.infrastructure.entity.EnrolledUserEntity;

public final class EnrolledUserMapper {

    public static EnrolledUsers toDomain(List<EnrolledUserEntity> enrolledUserEntities) {
        List<Long> userIdList = enrolledUserEntities.stream()
            .map(EnrolledUserEntity::getUserId)
            .collect(Collectors.toList());
        return new EnrolledUsers(userIdList);
    }
    
    public static EnrolledUserEntity toEntity(Long enrollmentId, Long userId) {
        return new EnrolledUserEntity(enrollmentId, null, userId, null, null);
    }

    public static List<EnrolledUserEntity> toEntityList(Long enrollmentId, EnrolledUsers enrolledUsers) {
        return toEntityList(enrollmentId, null, enrolledUsers);
    }

    public static List<EnrolledUserEntity> toEntityList(Long enrollmentId, Long id, EnrolledUsers enrolledUsers) {
        return enrolledUsers.getEnrolledUserList()
            .stream()
            .map(enrolledUser -> new EnrolledUserEntity(
                enrollmentId,
                id,
                enrolledUser,
                null,
                null
            ))
            .collect(Collectors.toList());
    }
}
