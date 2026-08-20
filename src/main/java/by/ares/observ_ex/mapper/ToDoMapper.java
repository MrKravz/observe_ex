package by.ares.observ_ex.mapper;

import by.ares.observ_ex.dto.ToDoDto;
import by.ares.observ_ex.dto.ToDoRequest;
import by.ares.observ_ex.model.ToDo;
import org.springframework.stereotype.Component;

@Component
public class ToDoMapper {
  public ToDoDto map(ToDo toDo) {
    return ToDoDto.builder().id(toDo.getId()).desc(toDo.getDesc()).build();
  }

  public ToDo remap(ToDoRequest toDoRequest) {
    return new ToDo().setDesc(toDoRequest.getDesc());
  }
}
