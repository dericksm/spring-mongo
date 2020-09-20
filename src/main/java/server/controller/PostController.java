package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import server.controller.utils.URL;
import server.dto.UserDTO;
import server.entities.Post;
import server.entities.User;
import server.services.PostService;
import server.services.UserService;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = postService.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSerach(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date dateMin = URL.convertDate(minDate, new Date(0l));
        Date dateMax = URL.convertDate(maxDate, new Date());
        List<Post> posts = postService.fullSearch(text, dateMin, dateMax);
        return ResponseEntity.ok().body(posts);
    }
}
