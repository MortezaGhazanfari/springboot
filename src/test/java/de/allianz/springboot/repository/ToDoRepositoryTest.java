package de.allianz.springboot.repository;


import de.allianz.springboot.entity.ToDo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ToDoRepositoryTest {
    private final ToDoRepository toDoRepository;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;

    @BeforeEach
    public void init(){
        toDo1 = new ToDo(null,"todo1","todo1Desc","heute",false);
        toDo2 = new ToDo(null,"todo2","todo1Desc2","Morgen",true);
        toDo3 = new ToDo(null,"todo3","todo1Desc3","Übermorgen",false);
        this.toDoRepository.saveAll(List.of(toDo1,toDo2,toDo3));
    }
    @Test
    public void findAllByStatusIsTrue(){
        assertFalse(this.toDoRepository.findAllByStatusIsTrue().contains(toDo1));
        assertTrue(this.toDoRepository.findAllByStatusIsTrue().contains(toDo2));
        assertFalse(this.toDoRepository.findAllByStatusIsTrue().contains(toDo3));
    };
    @Test
    public void findAllByStatusIsFalse(){
        assertTrue(this.toDoRepository.findAllByStatusIsFalse().contains(toDo1));
        assertFalse(this.toDoRepository.findAllByStatusIsFalse().contains(toDo2));
        assertTrue(this.toDoRepository.findAllByStatusIsFalse().contains(toDo3));
    };
    @Test
    public void countAllByStatusIsTrue(){
        assertEquals(1, this.toDoRepository.countAllByStatusIsTrue());
    };
    @Test
    public void countAllByStatusIsFalse(){

        assertEquals(2, this.toDoRepository.countAllByStatusIsFalse());
    };
}
