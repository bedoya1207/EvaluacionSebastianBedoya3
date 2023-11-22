package com.service.evaluacionSebastianBedoya3.repositorio;

import com.service.evaluacionSebastianBedoya3.model.ExternalPost;
import com.service.evaluacionSebastianBedoya3.model.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    private final RestTemplate restTemplate;
    private final PostRepository postRepository;

    @Autowired
    public PostService(RestTemplate restTemplate, PostRepository postRepository) {
        this.restTemplate = restTemplate;
        this.postRepository = postRepository;
    }

    public void fetchDataFromExternalServiceAndSave() {
        // Consumir el servicio externo
        ExternalPost[] externalPosts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", ExternalPost[].class);

        if (externalPosts != null) {
            for (ExternalPost externalPost : externalPosts) {
                // Validaciones de null y campos vacíos
                if (externalPost.getTitle() != null && !externalPost.getTitle().isEmpty()) {
                    PostEntity postEntity = new PostEntity();
                    postEntity.setTitle(externalPost.getTitle());
                    postEntity.setBody(externalPost.getBody());
                    postRepository.save(postEntity);
                }
            }
        }
    }

    public boolean postExistsByTitle(String title) {
        return postRepository.existsByTitle(title);
    }

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }
}

