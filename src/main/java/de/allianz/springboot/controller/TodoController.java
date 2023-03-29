package de.allianz.springboot.controller;

import de.allianz.springboot.dto.ToDoCreate;
import de.allianz.springboot.dto.ToDoUpdate;
import de.allianz.springboot.entity.Role;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/todo")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class TodoController {
    private final ToDoService toDoService;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
        @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<ToDo> getToDoById(@Valid @PathVariable("id") Long id){
        return new ResponseEntity<>(toDoService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public ResponseEntity<List<ToDo>> getAllToDos(){
        return new ResponseEntity<>(toDoService.getAllToDos(),HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('TODO_UPDATE')")
    public ResponseEntity<ToDo> updateToDo(@Valid @RequestBody ToDoUpdate toDoUpdateDto) throws Exception {
        ToDo toDo = this.toDoService.findById(toDoUpdateDto.getId());
        mapper.map(toDoUpdateDto, toDo);
        return new ResponseEntity<>(toDoService.updateToDo(toDo),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TODO_DELETE')")
    public ResponseEntity<?> removeToDo(ToDo toDo){
        toDoService.removeToDo(toDo);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('TODO_CREATE')")
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreate toDoCreateDto){
       return new ResponseEntity<>(this.toDoService.createToDo(mapper.map(toDoCreateDto, ToDo.class)),HttpStatus.CREATED) ;
    }

    @GetMapping("/done")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<List<ToDo>> getDoneToDos(){
        return new ResponseEntity<>(toDoService.getDoneTodos(),HttpStatus.OK);
    }

    @GetMapping("/open")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<List<ToDo>> getOpenToDos(){
        return new ResponseEntity<>(toDoService.getOpenTodos(),HttpStatus.OK);
    }

    @GetMapping("/numberDone")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<Integer> getNumberOfDoneToDos(){
        return new ResponseEntity<>(toDoService.getNumberOfDoneToDos(),HttpStatus.OK);
    }

    @GetMapping("/numberOpen")
    @PreAuthorize("hasRole('TODO_READ')")
    public ResponseEntity<Integer> getNumberOfOpenToDos(){
        return new ResponseEntity<>(toDoService.getNumberOfOpenToDos(),HttpStatus.OK);
    }
}
