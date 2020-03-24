package teamhierro.familyreunion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import teamhierro.familyreunion.model.AddressBook;

@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {

}
