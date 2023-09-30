package no.hvl.dat250.rest.todos;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Rest-Endpoint for todos.
 */
public class TodoController {

  public static final String TODO_WITH_THE_ID_X_NOT_FOUND = "Todo with the id %s not found!";


  private static long counter;
  private static HashMap<Todo, Long> toDoList = new HashMap<>();


  @PostMapping("/todos")
  public Todo createTodo(@RequestBody Todo requestedTodo) {
    Todo properTodo = new Todo(counter++, requestedTodo.getSummary(), requestedTodo.getDescription());
    toDoList.put(properTodo, counter);
    return properTodo;
  }


}
