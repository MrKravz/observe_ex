package by.ares.observ_ex.service;

import by.ares.observ_ex.dto.ToDoDto;
import by.ares.observ_ex.dto.ToDoRequest;
import by.ares.observ_ex.mapper.ToDoMapper;
import by.ares.observ_ex.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoMapper toDoMapper;
    private final ToDoRepository toDoRepository;

    public List<ToDoDto> findAll() {
        return toDoRepository.findAll()
                .stream()
                .map(toDoMapper::map)
                .toList();
    }

    @Transactional
    public ToDoDto save(ToDoRequest toDoRequest) {
        return toDoMapper.map(
                toDoRepository.save(toDoMapper.remap(toDoRequest))
        );
    }

}
