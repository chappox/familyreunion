package teamhierro.familyreunion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import teamhierro.familyreunion.model.Login;

import java.util.List;

@Repository
public interface RegisterRepository extends CrudRepository<Login, Integer> {
    Login findByEmail(String email);
}
