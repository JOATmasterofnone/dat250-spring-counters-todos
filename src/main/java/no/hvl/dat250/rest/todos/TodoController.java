package no.hvl.dat250.rest.todos;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;


@RestController
@RequestMapping
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  private static long counter;
  private static final ArrayList<Todo> toDoList = new ArrayList<>();


  @PostMapping("/todos")
  public Todo createTodo(@RequestBody Todo requestedTodo) {
    Todo properTodo = new Todo(counter++, requestedTodo.getSummary(), requestedTodo.getDescription());
    toDoList.add(properTodo);
    return properTodo;
  }

  @GetMapping("/todos/{id}")
  public Todo readTodo(@PathVariable Long id) {
    for (Todo existingTodo : toDoList) {
      if (existingTodo.getId().equals(id)) {
        return existingTodo;
      }
    }

    throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
  }


  @PutMapping("/todos/{id}")
  public void updateTodo(@PathVariable Long id, @RequestBody Todo requestedTodo) {
    for (Todo existingTodo : toDoList) {
      if (existingTodo.getId().equals(id)) {
        existingTodo.setSummary(requestedTodo.getSummary());
        existingTodo.setDescription(requestedTodo.getDescription());
        return;
      }
    }
    throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
  }


  @DeleteMapping("/todos/{id}")
  public void deleteTodo(@PathVariable Long id) {

    for (Todo existingTodo : toDoList) {
      if (existingTodo.getId().equals(id)) {
        toDoList.remove(existingTodo);
        return;
      }
    }
    throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
  }


  @GetMapping("/todos")
  public ArrayList<Todo> getAll(){
    return toDoList;
  }
}
