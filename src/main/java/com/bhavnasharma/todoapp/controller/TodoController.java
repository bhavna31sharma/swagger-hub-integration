package com.bhavnasharma.todoapp.controller;

import com.bhavnasharma.todoapp.model.Todo;
import com.bhavnasharma.todoapp.repository.TodoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo Controller", description = "Endpoints for managing Todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Operation(summary = "Get all Todos", description = "Retrieves a list of all existing Todo items.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of Todos")
    })
    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Operation(summary = "Get a Todo by ID", description = "Retrieves a specific Todo by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the Todo"),
            @ApiResponse(responseCode = "404", description = "Todo not found for the given ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(
            @Parameter(description = "ID of the Todo to be retrieved", required = true)
            @PathVariable("id") UUID id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Create a new Todo", description = "Creates a new Todo item and returns the created object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the new Todo"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    public ResponseEntity<Todo> createTodo(
            @Parameter(description = "Todo object to be created", required = true)
            @RequestBody Todo todo) {
        Todo savedTodo = todoRepository.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodo);
    }

    @Operation(summary = "Update an existing Todo", description = "Updates a specific Todo item based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the Todo"),
            @ApiResponse(responseCode = "404", description = "Todo not found for the given ID"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(
            @Parameter(description = "ID of the Todo to be updated", required = true)
            @PathVariable("id") UUID id,
            @Parameter(description = "Updated Todo object", required = true)
            @RequestBody Todo currentTodo) {
        
        Optional<Todo> existingTodoOpt = todoRepository.findById(id);

        if (existingTodoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Todo existingTodo = existingTodoOpt.get();
        existingTodo.setTitle(currentTodo.getTitle());
        existingTodo.setDescription(currentTodo.getDescription());
        existingTodo.setCompleted(currentTodo.isCompleted());
        todoRepository.save(existingTodo);

        return ResponseEntity.ok(existingTodo);
    }

    @Operation(summary = "Delete a Todo", description = "Deletes a specific Todo item based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the Todo"),
            @ApiResponse(responseCode = "404", description = "Todo not found for the given ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @Parameter(description = "ID of the Todo to be deleted", required = true)
            @PathVariable("id") UUID id) {
        
        Optional<Todo> existingTodoOpt = todoRepository.findById(id);

        if (existingTodoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
