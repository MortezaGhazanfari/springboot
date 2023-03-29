package de.allianz.springboot.controller;

import de.allianz.springboot.config.TestPasswordEncoderConfig;
import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import de.allianz.springboot.service.ToDoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@Import(TestPasswordEncoderConfig.class)
@WebMvcTest(TodoController.class)
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ToDoService toDoService;
    @MockBean
    private ModelMapper modelMapper;

    private ToDo toDo1;
    private ToDo toDo2;
    private ToDo toDo3;
    private List<ToDo> toDoArrayList = new ArrayList<>();

    @BeforeEach
    public void init(){
        toDo1 = new ToDo(1L,"Schulung","Springboot","März",true);
        toDo2 = new ToDo(2L,"Termin","Hausarzt","Morgen",false);
        toDo3 = new ToDo(3L,"Termin","Hausarzt","Morgen",false);
        this.toDoArrayList.addAll(Arrays.asList(toDo1,toDo2,toDo3));
    }


    @Test
    public void getAllToDosAPI() throws Exception
    {
        when(this.toDoService.getAllToDos()).thenReturn(this.toDoArrayList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                    {
                                        "id": 1,
                                        "title": "Schulung",
                                        "description": "Springboot",
                                        "date": "März",
                                        "status": true
                                    },
                                    {
                                        "id": 2,
                                        "title": "Termin",
                                        "description": "Hausarzt",
                                        "date": "Morgen",
                                        "status": false
                                    },
                                    {
                                        "id": 3,
                                        "title": "Termin",
                                        "description": "Hausarzt",
                                        "date": "Morgen",
                                        "status": false
                                    }
                                ]
                                """
                ));

    }
   /* @Test
    public void getToDoByIdAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }*/
}
