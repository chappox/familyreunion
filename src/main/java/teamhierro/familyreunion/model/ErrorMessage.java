package teamhierro.familyreunion.model;

import lombok.Getter;
import lombok.Setter;

public class ErrorMessage {

    @Getter
    @Setter
    private Integer errorcode;
    @Getter
    @Setter
    private String errormessage;
}
