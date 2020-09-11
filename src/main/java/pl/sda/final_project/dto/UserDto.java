package pl.sda.final_project.dto;

import pl.sda.final_project.model.user.UserEntity;

public class UserDto {

    private String userName;
    private String userCity;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public static UserDto apply(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.userCity = userEntity.getAddress().getCity();
        userDto.userName = userEntity.getFirstName();
        return userDto;
    }

}
