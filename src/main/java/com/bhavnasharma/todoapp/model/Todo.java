package com.bhavnasharma.todoapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a single task in the Todo list.")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "The unique identifier of the Todo item.", example = "123e4567-e89b-12d3-a456-426614174000", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "The title of the Todo item.", example = "Buy groceries")
    private String title;

    @Schema(description = "A detailed description of the Todo item.", example = "Milk, Eggs, Bread, and Butter")
    private String description;

    @Schema(description = "Indicates whether the Todo item is completed or not.", example = "false")
    private boolean completed;
}
