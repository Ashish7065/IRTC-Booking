package application.IRTC.IRTCController;

import application.IRTC.DTO.UserDTO;
import application.IRTC.Entity.Users;
import application.IRTC.Service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private Userservice userservice;

    @PostMapping("/create-user")
    public ResponseEntity<UserDTO> CreateUser(@RequestBody UserDTO userDTO) {
        UserDTO Usercreate = userservice.CreateUser(userDTO);
        return new ResponseEntity<>(Usercreate, HttpStatus.CREATED);
    }


    @GetMapping("/Get-All-User")
    public ResponseEntity<List<Users>> GetAllUser() {
        List<Users> allUsers = userservice.GetAllUser();
        return ResponseEntity.ok(allUsers);
    }
}
