package com.cloud;

import com.cloud.error.BookNotFoundException;
import com.cloud.error.BookUnSupportedFieldPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookRepository repository;

    @GetMapping
    List<Book> findAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(
            @Valid
            @RequestBody
                    Book newBook
    ) {
        return repository.save(newBook);
    }

    @GetMapping("/{id}")
    Book findOne(
            @PathVariable
            @Min(1)
                    Long id
    ) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new BookNotFoundException(id));
    }

    @PutMapping("/{id}")
    Book saveOrUpdate(
            @RequestBody
                    Book newBook,
            @PathVariable
                    Long id
    ) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthor(newBook.getAuthor());
                    x.setPrice(newBook.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });

    }

    @PatchMapping("/{id}")
    Book patch(
            @RequestBody
                    Map<String, String> update,
            @PathVariable
                    Long id
    ) {

        return repository.findById(id)
                .map(x -> {

                    String author = update.get("author");
                    if (!StringUtils.isEmpty(author)) {
                        x.setAuthor(author);
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });

    }

    @DeleteMapping("/{id}")
    void deleteBook(
            @PathVariable
                    Long id
    ) {
        repository.deleteById(id);
    }

}
