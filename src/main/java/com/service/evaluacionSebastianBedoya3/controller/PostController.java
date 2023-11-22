package com.service.evaluacionSebastianBedoya3.controller;

import com.service.evaluacionSebastianBedoya3.model.PostEntity;
import com.service.evaluacionSebastianBedoya3.repositorio.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/fetch-and-save")
    public ResponseEntity<String> fetchAndSaveData() {
        postService.fetchDataFromExternalServiceAndSave();
        return ResponseEntity.ok("Datos recuperados y guardados en la base de datos.");
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchPost(@RequestParam(required = false) String title) {
        // Validaciones
        if (title == null || title.isEmpty()) {
            return ResponseEntity.badRequest().body("El título no puede estar vacío");
        }

        if (postService.postExistsByTitle(title)) {
            return ResponseEntity.ok("Post encontrado");
        } else {
            return ResponseEntity.ok("Post no encontrado");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PostEntity>> getAllPosts() {
        List<PostEntity> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
