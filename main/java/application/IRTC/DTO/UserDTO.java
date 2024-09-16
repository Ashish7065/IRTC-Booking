package application.IRTC.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {

    private Long id;
    private String username;
    private String password;


}
