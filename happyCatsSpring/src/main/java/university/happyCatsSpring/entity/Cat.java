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
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String photo;

    private String birthday;

    @Column (length=16384)
    private String note;

    @ManyToOne
    private Breed breed;

    public Cat(String name, String photo, String birthday, String note) {
        this.name = name;
        this.photo = photo;
        this.birthday = birthday;
        this.note = note;
    }

    public Cat(String name, Breed breed) {
        this.name = name;
        this.breed = breed;
    }

    public Cat(String name, String photo, String birthday, String note, Breed breed) {
        this.name = name;
        this.photo = photo;
        this.birthday = birthday;
        this.note = note;
        this.breed = breed;
    }
}
