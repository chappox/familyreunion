package teamhierro.familyreunion.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterMapper implements RowMapper<Login> {
    public Login mapRow(ResultSet resut, int rowNum) throws SQLException {
        Login register = new Login();
        register.setId(resut.getInt("id"));
        register.setUsername(resut.getString("username"));
        register.setEmail(resut.getString("email"));
        register.setLastlogin(resut.getString("lastlogin"));
        register.setLastip(resut.getString("lastip"));
        register.setLevel(resut.getInt("level"));
        return register;
    }

}
