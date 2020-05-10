package university.happyCatsSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String photo;

    private String description;

    @ManyToMany
    private Set<Breed> breeds;

    public Disease(String name, String photo, String description) {
        this.name = name;
        this.photo = photo;
        this.description = description;
    }
}
