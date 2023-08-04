package com.example.MutsaSNS.dtos;

import com.example.MutsaSNS.entities.UserFriendsEntity;
import lombok.Data;

@Data
public class UserFriendsDto {
    private Long id;
    private String fromUser;
    private String toUser;

    public static UserFriendsDto fromEntity(UserFriendsEntity userFriendsEntity) {
        UserFriendsDto userFriendsDto = new UserFriendsDto();
        userFriendsDto.setId(userFriendsEntity.getId());
        userFriendsDto.setFromUser(userFriendsEntity.getFromUser().getUsername());
        userFriendsDto.setToUser(userFriendsEntity.getToUser().getUsername());
        return userFriendsDto;
    }
}
