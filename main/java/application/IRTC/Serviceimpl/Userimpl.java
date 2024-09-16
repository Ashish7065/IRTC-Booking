package application.IRTC.Serviceimpl;

import application.IRTC.DTO.UserDTO;
import application.IRTC.Entity.Users;
import application.IRTC.Repositories.Userrepo;
import application.IRTC.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Userimpl implements Userservice {

    @Autowired
    private Userrepo userrepo;


    @Override
    public List<Users> GetAllUser() {
        List<Users> allusers = userrepo.findAll();
        return allusers;
    }

    @Override
    public UserDTO CreateUser(UserDTO userDTO) {
        Users users = convertUserDtoToEntity(userDTO);
        Users createUsers = userrepo.save(users);
        return ConvertUserEntitytoDto(createUsers);
    }


    private UserDTO ConvertUserEntitytoDto(Users users) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setUsername(users.getUsername());
        userDTO.setPassword(users.getPassword());

        return userDTO;
    }


    private Users convertUserDtoToEntity(UserDTO userDTO) {
        Users users = new Users();
        users.setId(userDTO.getId());
        users.setUsername(userDTO.getUsername());
        users.setPassword(userDTO.getPassword());



        return users;

    }


}
