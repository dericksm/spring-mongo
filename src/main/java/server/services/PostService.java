package server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.dto.UserDTO;
import server.entities.Post;
import server.entities.User;
import server.exceptions.ObjectNotFoundException;
import server.repository.PostRepository;
import server.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Post post = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found"));
        return post;
    }

    public List<Post> findByTitle(String title) {
        return repository.searchTitle(title);
    }

    public List<Post> fullSearch(String title, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(title, minDate, maxDate);
    }

}
