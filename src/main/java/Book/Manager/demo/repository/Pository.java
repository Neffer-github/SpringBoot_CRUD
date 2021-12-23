package Book.Manager.demo.repository;

import Book.Manager.demo.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface Pository extends CrudRepository<Person,Long> {
}
