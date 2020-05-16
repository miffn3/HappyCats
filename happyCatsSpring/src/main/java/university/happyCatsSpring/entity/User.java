package university.happyCatsSpring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String username;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    @NotBlank
    private String name;

    private String photo;

    private String birthday;

    private String phone;

    @Email
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;

    private String note;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Cat> cats;

    public User(String username, String password, String name, String photo, String birthday,
                String phone, String email, String note) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.photo = photo;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    public User(@NotNull @NotBlank String username, @NotNull String password,
                @NotNull @NotBlank String name, @Email @NotNull @NotBlank String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String username, String password, String name, String photo, String birthday,
                String phone, String email, String note, Set<Cat> cats) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.photo = photo;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.cats = cats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
