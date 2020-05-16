package university.happyCatsSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCatDto {
    private String name;
    private String photo;
    private String birthday;
    private String note;
    private String breed;
}
