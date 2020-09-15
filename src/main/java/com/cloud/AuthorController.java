package com.cloud;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    List<String> authors = Arrays.asList("Richard Bejtlich", "Antonio Salas", "Mónica Valle", "Nassim Nicholas Taleb");

    @GetMapping
    List<String> findAll() {

        return authors;

    }

}
