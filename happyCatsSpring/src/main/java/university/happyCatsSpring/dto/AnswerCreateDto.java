package university.happyCatsSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreateDto {
    @Id
    private String id;

    private String value;

    private String breedName;
}
