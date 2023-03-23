package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreate;
import de.allianz.springboot.dto.ToDoUpdate;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final ToDoService toDoService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ToDo getToDoById(@Valid @PathVariable("id") Long id){
        return toDoService.findById(id);
    }

    @GetMapping
    public List<ToDo> getAllToDos(){
        return toDoService.getAllToDos();
    }

    @PutMapping
    public ToDo updateToDo(@Valid @RequestBody ToDoUpdate toDoUpdateDto){
        ToDo toDo = this.toDoService.findById(toDoUpdateDto.getId());
        mapper.map(toDoUpdateDto, toDo);
        return toDoService.updateToDo(toDo);
    }

    @DeleteMapping("/{id}")
    public void removeToDo(ToDo toDo){
        toDoService.removeToDo(toDo);
    }

    @PostMapping
    public ToDo createToDo(@Valid @RequestBody ToDoCreate toDoCreateDto){
       return this.toDoService.createToDo(mapper.map(toDoCreateDto, ToDo.class));
    }

    @GetMapping("/done")
    public List<ToDo> getDoneToDos(){
        return toDoService.getDoneTodos();
    }

    @GetMapping("/open")
    public List<ToDo> getOpenToDos(){
        return toDoService.getOpenTodos();
    }

    @GetMapping("/numberDone")
    public Integer getNumberOfDoneToDos(){
        return toDoService.getNumberOfDoneToDos();
    }

    @GetMapping("/numberOpen")
    public Integer getNumberOfOpenToDos(){
        return toDoService.getNumberOfOpenToDos();
    }
}
