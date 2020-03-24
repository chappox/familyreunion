package teamhierro.familyreunion.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "addressbook")
@ToString
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @NotEmpty(message = "Please enter your First Name")
    @Size(max = 50)
    @Getter
    @Setter
    private String lastname;

    @NotEmpty(message = "Please enter your Last Name")
    @Size(max = 50)
    @Getter
    @Setter
    private String firstname;

    @NotEmpty(message = "Please enter your Street Address")
    @Getter
    @Setter
    private String streetaddress;

    @NotEmpty(message = "Please enter your City")
    @Getter
    @Setter
    private String city;

    @NotEmpty(message = "Please enter your State")
    @Getter
    @Setter
    private String state;

    @NotEmpty(message = "Please enter your Country")
    @Getter
    @Setter
    private String country;

    @NotEmpty(message = "Please enter your Zip Code")
    @Size(max = 10)
    @Getter
    @Setter
    private String zipcode;

//    @Getter @Setter private String errormessage;

}
