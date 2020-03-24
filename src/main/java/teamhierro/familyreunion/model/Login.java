package teamhierro.familyreunion.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "login")
@ToString
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Size(max = 50)
    @NotEmpty(message = "Please fill out a username to use. This is required.")
    @Getter
    @Setter
    private String username;

    @NotEmpty(message = "Please fill out a password to use. This is required.")
    @Getter
    @Setter
    private String password;

    @NotEmpty(message = "Please fill out a email to use. This is required.")
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String lastlogin;

    @Getter
    @Setter
    private String lastip;

    @Getter
    @Setter
    private Integer level;
}
