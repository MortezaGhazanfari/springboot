package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreate;
import de.allianz.springboot.dto.ToDoUpdate;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final ToDoService toDoService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(@Valid @PathVariable("id") Long id){
        return new ResponseEntity<>(toDoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> getAllToDos(){
        return new ResponseEntity<>(toDoService.getAllToDos(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ToDo> updateToDo(@Valid @RequestBody ToDoUpdate toDoUpdateDto) throws Exception {
        ToDo toDo = this.toDoService.findById(toDoUpdateDto.getId());
        mapper.map(toDoUpdateDto, toDo);
        return new ResponseEntity<>(toDoService.updateToDo(toDo),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeToDo(ToDo toDo){
        toDoService.removeToDo(toDo);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreate toDoCreateDto){
       return new ResponseEntity<>(this.toDoService.createToDo(mapper.map(toDoCreateDto, ToDo.class)),HttpStatus.CREATED) ;
    }

    @GetMapping("/done")
    public ResponseEntity<List<ToDo>> getDoneToDos(){
        return new ResponseEntity<>(toDoService.getDoneTodos(),HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<List<ToDo>> getOpenToDos(){
        return new ResponseEntity<>(toDoService.getOpenTodos(),HttpStatus.OK);
    }

    @GetMapping("/numberDone")
    public ResponseEntity<Integer> getNumberOfDoneToDos(){
        return new ResponseEntity<>(toDoService.getNumberOfDoneToDos(),HttpStatus.OK);
    }

    @GetMapping("/numberOpen")
    public ResponseEntity<Integer> getNumberOfOpenToDos(){
        return new ResponseEntity<>(toDoService.getNumberOfOpenToDos(),HttpStatus.OK);
    }
}
