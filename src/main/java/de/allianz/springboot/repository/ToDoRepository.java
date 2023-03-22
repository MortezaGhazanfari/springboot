package de.allianz.springboot.repository;

import de.allianz.springboot.entity.ToDo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    List<ToDo> findAllByStatusIsTrue();
    List<ToDo> findAllByStatusIsFalse();
    List<ToDo> findAllByTitleContains(String title);
    Integer countAllByStatusIsTrue();
    Integer countAllByStatusIsFalse();
}
