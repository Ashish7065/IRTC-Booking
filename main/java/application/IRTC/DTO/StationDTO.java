package application.IRTC.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class StationDTO {

    private Long id;
    private String name;
    private String code;
    private String city;
}
