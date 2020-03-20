package teamhierro.familyreunion.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "login")
@ToString
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Getter @Setter private Integer id;

    @NotEmpty
    @Getter @Setter private String username;

    @NotEmpty
    @Getter @Setter private String password;

    @NotEmpty
    @Getter @Setter private String email;

    @NotEmpty
    @Getter @Setter private String lastlogin;

    @NotEmpty
    @Getter @Setter private String lastip;

    @NotNull
    @Getter @Setter private Integer level;
    
    @Getter @Setter private String errormessage;

}
