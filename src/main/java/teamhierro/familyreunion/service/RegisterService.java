package teamhierro.familyreunion.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import teamhierro.familyreunion.model.Login;
import teamhierro.familyreunion.repository.RegisterRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private String username, password, email, lastip, sql, ipAddress;
    private Date date, today = Calendar.getInstance().getTime();;
    private int level=0;
    private HttpServletRequest remoteIp = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Login> findAll(){
        var it = repository.findAll();

        var users = new ArrayList<Login>();
        it.forEach(e -> users.add(e));

        return users;
    }

    public Long count(){
        return repository.count();
    }

    public boolean addUser(String username, String password, String email, Date date, String lastip, int level ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.date = (date == null) ? today : date;
        this.lastip = (lastip == null) ? remoteIp.getRemoteAddr() : lastip;
        this.level = (level == 0) ? 0 : level;
        this.sql = "INSERT INTO login(username, password, email, lastlogin, lastip, level) VALUES (?, ?, ?, ?, ?)";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        try {
            jdbcTemplate.update(this.sql, this.username, this.password, this.email, this.date, this.lastip, this.level);
            System.out.println("The following user's data was added to the database: " + this.username);
            return true;
        } catch (DataAccessException e) {
            System.out.println("There was an error putting the user " + this.username + " into the database.");
        }
        return false;
    }

    public boolean checkUserByUsername(String username) {
        this.username = username;
        this.sql = "SELECT * FROM login WHERE username = ?";

        jdbcTemplate = jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            jdbcTemplate.update(sql, this.username);
            System.out.println("The username exists already");
            return true;
        } catch (DataAccessException e) {
            System.out.println("The user " + this.username + " is not taken yet.");
            return false;
        }
    }

    public boolean checkUserByEmail(String email) {
        this.email = email;
        this.sql = "SELECT * FROM login WHERE email = ?";

        jdbcTemplate = jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            jdbcTemplate.update(sql, this.email);
            System.out.println("The email exists already");
            return true;
        } catch (DataAccessException e) {
            System.out.println("The user " + this.email + " is not taken yet.");
            return false;
        }
    }
}
