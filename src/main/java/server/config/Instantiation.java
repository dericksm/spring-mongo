package server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import server.dto.AuthorDTO;
import server.dto.CommentDTO;
import server.entities.Post;
import server.entities.User;
import server.repository.PostRepository;
import server.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        repository.deleteAll();
        repository.saveAll(Arrays.asList(maria, alex, bob));

        CommentDTO commentDTO = new CommentDTO("Oi", sdf.parse("22/04/2021"), new AuthorDTO(maria));

        Post post1 = new Post(null, sdf.parse("21/03/2020"), "Rola", "cu", new AuthorDTO(maria));
        post1.getComments().add(commentDTO);
        postRepository.deleteAll();
        postRepository.save(post1);

        maria.getPosts().addAll(Arrays.asList(post1));
        repository.save(maria);

    }
}
