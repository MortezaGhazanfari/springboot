package de.allianz.springboot.service;

import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public List<ToDo> getToDos(){
        return (List<ToDo>) this.toDoRepository.findAll();
    }

    public ToDo createToDo(ToDo toDo){
        return toDoRepository.save(toDo);
    }
    public ToDo updateToDo(ToDo toDo)
    {
        toDoRepository.findById(toDo.getId()).orElseThrow(()->new EntityNotFoundException("ToDo was not found!"));
        return toDoRepository.save(toDo);
    }
    public ToDo findById(Long id){
        return toDoRepository.findById(id).orElseThrow(()->new EntityNotFoundException("ToDo was not found!"));
    }

    public List<ToDo> getAllToDos(){
        return (List<ToDo>) toDoRepository.findAll();
    }

    public List<ToDo> getDoneTodos(){
        return toDoRepository.findAllByStatusIsTrue();
    }
    public Integer getNumberOfDoneToDos(){

        return toDoRepository.countAllByStatusIsTrue();
    }

    public Integer getNumberOfOpenToDos(){
        return toDoRepository.countAllByStatusIsFalse();
    }
    public void removeToDo(ToDo toDo){
        toDoRepository.delete(toDo);
    }


}
