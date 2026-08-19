package by.ares.observ_ex.controller;

import by.ares.observ_ex.dto.ToDoDto;
import by.ares.observ_ex.dto.ToDoRequest;
import by.ares.observ_ex.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/to_do")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public ResponseEntity<List<ToDoDto>> findAll() {
        return ResponseEntity.ok(toDoService.findAll());
    }

    @PostMapping
    public ResponseEntity<ToDoDto> save(@RequestBody ToDoRequest toDoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toDoService.save(toDoRequest));
    }

}
