package no.hvl.dat250.rest.todos;

import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import java.util.HashMap;


@RestController
@RequestMapping
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";
  private static long counter;
  private static HashMap<Long, Todo> toDoList = new HashMap<>();


  @PostMapping("/todos")
  public Todo createTodo(@RequestBody Todo requestedTodo) {
    Todo properTodo = new Todo(counter++, requestedTodo.getSummary(), requestedTodo.getDescription());
    toDoList.put(counter, properTodo);
    return properTodo;
  }

  @GetMapping("/todos/{id}")
  public Todo readTodo(@PathVariable Long id) {
    //Todo retrievedTodo;
    Todo retrievedTodo = toDoList.get(id);
    if (retrievedTodo == null) {
      throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
    }
    return retrievedTodo;
  }


  @PutMapping("/todos/{id}")
  public Todo updateTodo(@PathVariable Long id, @RequestBody Todo requestedTodo) {
    //Todo retrievedTodo;
    Todo retrievedTodo = toDoList.get(id);
    if (retrievedTodo == null) {
      throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
    }

    retrievedTodo.setDescription(requestedTodo.getDescription());
    retrievedTodo.setSummary(requestedTodo.getSummary());
    return requestedTodo;
  }


  @DeleteMapping("/todos/{id}")
  public void deleteTodo(@PathVariable Long id) {
    //Todo retrievedTodo;
    Todo retrievedTodo = toDoList.get(id);
    if (retrievedTodo == null) {
      throw new NoSuchElementException(String.format(TODO_WITH_THE_ID_X_NOT_FOUND, id));
    }
    toDoList.remove(id);
  }

}
