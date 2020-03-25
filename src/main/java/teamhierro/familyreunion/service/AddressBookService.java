package teamhierro.familyreunion.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import teamhierro.familyreunion.model.AddressBook;
import teamhierro.familyreunion.repository.AddressBookRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository repository;

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private Integer id;
    private String lastname, firstname, streetaddress, city, state, country, zipcode, sql, dbField, dbField2, dbField3, dbValue, dbValue2, dbValue3;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<AddressBook> findAll() {
        var it = repository.findAll();

        var address = new ArrayList<AddressBook>();
        it.forEach(e -> address.add(e));

        return address;
    }

    public boolean findById(int id) {
        if (checkifIdExists(id)) {

            this.sql = "SELECT * FROM addressbook WHERE id = ?";

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            var userData = jdbcTemplate.update(this.sql, id);

            return true;
        }

        return false;
    }

    public Optional<AddressBook> findById(Integer id) {
        var it = repository.findById(id);

        return it;
    }

    public Long count() {
        return repository.count();
    }

    public List<AddressBook> singleView(Integer id) {
        var it = repository.findAll();

        var address = new ArrayList<AddressBook>();
        address.stream().filter(AddressBook -> id.equals(AddressBook.getId()));

        return address;
    }

    public boolean addAddress(String firstname, String lastname, String streetaddress, String city, String state, String country, String zipcode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.streetaddress = streetaddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (checkIfExists("firstname", this.firstname, "lastname", this.lastname)) {
            /*
             * Troubleshooting
             * System.out.println("The combination was found in database. Ending.");
             * */
            return false;
        }

        this.sql = "INSERT INTO addressbook(lastname, firstname, streetaddress, city, state, country, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(this.sql, this.lastname, this.firstname, this.streetaddress, this.city, this.state, this.country, this.zipcode);

        /*
         * Troubleshooting
         * System.out.println("Checking.. The record was not successfully added to the database.");
         * */
        return checkIfExists("firstname", this.firstname, "lastname", this.lastname);
        /*
         * Troubleshooting
         * System.out.println("Checking.. The following record data was added to the database: " + this.firstname + " " + this.lastname);
         * */
    }

    public boolean editAddress(Integer id, String firstname, String lastname, String streetaddress, String city, String state, String country, String zipcode) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.streetaddress = streetaddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        this.sql = "UPDATE addressbook SET lastname = ?, firstname = ?, streetaddress = ?, city = ?, state = ?, country = ?, zipcode = ? WHERE id = ?";

        jdbcTemplate.update(this.sql, this.lastname, this.firstname, this.streetaddress, this.city, this.state, this.country, this.zipcode, this.id);

        return true;
    }

    public boolean removeAddress(Integer id) {
        this.id = id;

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        if (!checkifIdExists(this.id)) {
            /*
             * Troubleshooting
             * System.out.println("The combination was found in database. Ending.");
             * */
            return false;
        }

        this.sql = "DELETE FROM addressbook WHERE id = ?";

        jdbcTemplate.update(this.sql, this.id);

        return !checkifIdExists(this.id);
    }

    public boolean checkifIdExists(Integer id) {
        int dbRecordCount;
        this.id = id;
        this.sql = "SELECT COUNT(*) FROM addressbook WHERE id = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);

        dbRecordCount = jdbcTemplate.queryForObject(sql, new Object[]{this.id}, Integer.class);

        if (dbRecordCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkIfExists(String dbField, String dbValue, String dbField2, String dbValue2) {

        this.dbField = dbField;
        this.dbField2 = dbField2;
        this.dbValue = dbValue;
        this.dbValue2 = dbValue2;
        int dbAddressCount;
        this.sql = "SELECT COUNT(*) FROM addressbook WHERE " + this.dbField + " = ? AND " + this.dbField2 + " = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        dbAddressCount = jdbcTemplate.queryForObject(sql, new Object[]{this.dbValue, this.dbValue2}, Integer.class);
        /*
         * Troubleshooting
         * System.out.println("The " + this.dbField + " and " + this.dbField2 + "exists.");
         * */
        return dbAddressCount > 0;
        /*
         * Troubleshooting
         * System.out.println("The " + this.dbField + ": " + this.dbValue + " AND " + this.dbField2 + ": " + this.dbValue2 + " is not taken yet.");
         */
    }

    public boolean tripleCheckIfExists(String dbField, String dbValue, String dbField2, String dbValue2, String dbField3, String dbValue3) {

        this.dbField = dbField;
        this.dbField2 = dbField2;
        this.dbField2 = dbField3;
        this.dbValue = dbValue;
        this.dbValue2 = dbValue2;
        this.dbValue2 = dbValue3;
        int dbAddressCount;
        this.sql = "SELECT COUNT(*) FROM addressbook WHERE " + this.dbField + " = ? AND " + this.dbField2 + " = ? AND " + this.dbField3 + " = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        dbAddressCount = jdbcTemplate.queryForObject(sql, new Object[]{this.dbValue, this.dbValue2, this.dbValue3}, Integer.class);
        /*
         * Troubleshooting
         * System.out.println("The " + this.dbField + " and " + this.dbField2 + "exists.");
         * */
        return dbAddressCount > 0;
        /*
         * Troubleshooting
         * System.out.println("The " + this.dbField + ": " + this.dbValue + " AND " + this.dbField2 + ": " + this.dbValue2 + "AND " + this.dbField3 + ": " + this.dbValue3 + " is not taken yet.");
         */
    }
}
