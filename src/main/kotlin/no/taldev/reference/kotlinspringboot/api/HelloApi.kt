package no.taldev.reference.kotlinspringboot.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hello")
class HelloApi {

    @GetMapping
    fun getHello(): ResponseEntity<HelloDto> {
        return ResponseEntity.ok(HelloDto("Hello world"))
    }
}