package by.ares.observ_ex.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import by.ares.observ_ex.dto.ToDoDto;
import by.ares.observ_ex.dto.ToDoRequest;
import by.ares.observ_ex.mapper.ToDoMapper;
import by.ares.observ_ex.model.ToDo;
import by.ares.observ_ex.repository.ToDoRepository;
import by.ares.observ_ex.service.ToDoService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

  @Mock private ToDoMapper toDoMapper;

  @Mock private ToDoRepository toDoRepository;

  @InjectMocks private ToDoService toDoService;

  @Test
  void findAll_ShouldReturnListOfToDoDto() {
    ToDo entity = new ToDo();
    ToDoDto dto = new ToDoDto();
    when(toDoRepository.findAll()).thenReturn(List.of(entity));
    when(toDoMapper.map(entity)).thenReturn(dto);
    List<ToDoDto> result = toDoService.findAll();
    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isEqualTo(dto);
    verify(toDoRepository, times(1)).findAll();
    verify(toDoMapper, times(1)).map(entity);
  }

  @Test
  void save_ShouldReturnSavedToDoDto() {
    ToDoRequest request = new ToDoRequest("test");
    ToDo entity = new ToDo();
    ToDo savedEntity = new ToDo();
    ToDoDto dto = new ToDoDto();
    when(toDoMapper.remap(request)).thenReturn(entity);
    when(toDoRepository.save(entity)).thenReturn(savedEntity);
    when(toDoMapper.map(savedEntity)).thenReturn(dto);
    ToDoDto result = toDoService.save(request);
    assertThat(result).isNotNull().isEqualTo(dto);
    verify(toDoMapper, times(1)).remap(request);
    verify(toDoRepository, times(1)).save(entity);
    verify(toDoMapper, times(1)).map(savedEntity);
  }
}
