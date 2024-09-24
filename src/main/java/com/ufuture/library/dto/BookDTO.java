package com.ufuture.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Книга")
public class BookDTO {
    @Size(min = 1, max = 50, message = "length of title must be between 2 and 50 characters")
    @NotEmpty(message = "title can't be empty")
    private String title;
    @NotEmpty(message = "title can't be empty")
    private String author;
    @Size(min = 10, message = "description must be more than 10 characters")
    @NotEmpty(message = "description can't be empty")
    private String description;
    @Min(value = 0, message = "price can't be less than 0")
    @NotNull(message = "price can't be empty")
    private Double price;

    public BookDTO() {
    }

    public BookDTO(String title, String author, String description, Double price) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
