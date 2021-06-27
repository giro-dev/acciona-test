package giro.albert.accionatest.infrastructure.db.mapper;

import giro.albert.accionatest.domain.model.User;
import giro.albert.accionatest.infrastructure.db.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromUserEntity(UserEntity userEntity){
        return User.builder()
                .userId(userEntity.getUserId())
                .screenName(userEntity.getScreenName())
                .localization(userEntity.getLocalization())
                .build();
    }
    public UserEntity toUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setScreenName(user.getScreenName());
        userEntity.setLocalization(user.getLocalization());
        return userEntity;
    }
}
