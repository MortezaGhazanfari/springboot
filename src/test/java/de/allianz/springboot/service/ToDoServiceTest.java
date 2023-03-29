package de.allianz.springboot.service;


import de.allianz.springboot.entity.ToDo;
import de.allianz.springboot.repository.ToDoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {
    @InjectMocks
    private ToDoService toDoService;
    @Mock
    private ToDoRepository toDoRepository;

    @Test
    public void shouldThrowEntityNotFoundException(){
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()->toDoService.findById(1L));
    }


}
