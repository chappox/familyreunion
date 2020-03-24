package teamhierro.familyreunion.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import teamhierro.familyreunion.model.Login;
import teamhierro.familyreunion.repository.RegisterRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private String username, password, email, lastip, sql, ipAddress, dbField, dbValue;
    private Date date, today = Calendar.getInstance().getTime();
    private int level = 0;
    private HttpServletRequest remoteIp = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Login> findAll() {
        var it = repository.findAll();

        var login = new ArrayList<Login>();
        it.forEach(e -> login.add(e));

        return login;
    }

    public Long count(){
        return repository.count();
    }

    public boolean addUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.date = today;
        this.lastip = "127.0.0.1";
        /*this.lastip = remoteIp.getRemoteAddr();*/
        this.level = 0;
        this.sql = "INSERT INTO login(username, password, email, lastlogin, lastip, level) VALUES (?, ?, ?, ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(this.sql, this.username, this.password, this.email, this.date, this.lastip, this.level);
        /*
         * Troubleshooting
         * System.out.println("The user was not successfully added to the database.");
         * */
        return checkUserCredential("email", this.email);
        /*
         * Troubleshooting
         * System.out.println("The following user's data was added to the database: " + this.username);
         * */
    }

    public boolean checkUserCredential(String dbField, String dbValue) {

        this.dbField = dbField;
        this.dbValue = dbValue;
        this.sql = "SELECT COUNT(*) FROM login WHERE " + this.dbField + " = ?";

        jdbcTemplate = jdbcTemplate = new JdbcTemplate(dataSource);
        int dbEmailCount = jdbcTemplate.queryForObject(sql, new Object[]{this.dbValue}, Integer.class);
        /*
         * Troubleshooting
         * System.out.println("The " + this.dbField + " exists.");
         * */
        return dbEmailCount > 0;
        /*
         * Troubleshooting
         * System.out.println("The " + this.dbField + ": " + this.dbValue + " is not taken yet.");
         * */
    }
}