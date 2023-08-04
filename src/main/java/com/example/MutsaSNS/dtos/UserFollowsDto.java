package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.UserFollowsEntity;
import lombok.Data;

@Data
public class UserFollowsDto {
    private Long id;
    private String follower;
    private String following;

    public static UserFollowsDto fromEntity(UserFollowsEntity userFollowsEntity) {
        UserFollowsDto userFollowsDto = new UserFollowsDto();
        userFollowsDto.setId(userFollowsEntity.getId());
        userFollowsDto.setFollower(userFollowsEntity.getFollower().getUsername());
        userFollowsDto.setFollowing(userFollowsEntity.getFollowing().getUsername());
        return userFollowsDto;
    }
}
