package application.IRTC.Service;

import application.IRTC.DTO.UserDTO;
import application.IRTC.Entity.Users;

import java.util.List;

public interface Userservice {

    public List<Users> GetAllUser();

    public UserDTO CreateUser(UserDTO userDTO);
}

