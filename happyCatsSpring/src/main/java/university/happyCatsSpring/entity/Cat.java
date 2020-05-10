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

    private String note;

    @ManyToMany
    private Set<Breed> breeds;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Cat(String name, String photo, String birthday, String note) {
        this.name = name;
        this.photo = photo;
        this.birthday = birthday;
        this.note = note;
    }
}
