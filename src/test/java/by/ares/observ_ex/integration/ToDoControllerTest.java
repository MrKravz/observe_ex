package by.ares.observ_ex.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.ares.observ_ex.dto.ToDoRequest;
import by.ares.observ_ex.model.ToDo;
import by.ares.observ_ex.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

class ToDoControllerTest extends AbstractIntegrationTest {

  @Autowired private ToDoRepository toDoRepository;

  private ToDo saveToDo(String title) {
    ToDo toDo = new ToDo();
    toDo.setDesc(title);
    return toDoRepository.save(toDo);
  }

  @Test
  void findAll() throws Exception {
    saveToDo("First Task");
    saveToDo("Second Task");
    mockMvc
        .perform(get("/to_do"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].title").value("First Task"));
  }

  @Test
  void save() throws Exception {
    ToDoRequest request = new ToDoRequest("New Task");

    // Act & Assert
    mockMvc
        .perform(
            post("/to_do")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.title").value("New Task"));
  }
}
