package de.allianz.springboot.database;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements CommandLineRunner {
    private final ToDoRepository toDoRepository;
    @Override
    public void run(String... args) throws Exception {
        final ToDo toDo = new ToDo(null,"Schulung","Springboot","März",true);
        final ToDo toDo1 = new ToDo(null,"Termin", "Hausarzt", "Morgen", false);
        final ToDo toDo2 = new ToDo(null,"Termin", "Hausarzt", "Morgen", false);
        toDoRepository.saveAll(List.of(toDo,toDo1,toDo2));
        System.out.println(toDoRepository.count());
    }
}
