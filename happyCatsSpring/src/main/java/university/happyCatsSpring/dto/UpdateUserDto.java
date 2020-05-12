package university.happyCatsSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {
    private String username;
    private String password;
    private String name;
    private String photo;
    private String birthday;
    private String phone;
    private String email;
    private String note;
}
