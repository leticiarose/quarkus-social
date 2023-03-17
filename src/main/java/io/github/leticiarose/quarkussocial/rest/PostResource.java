package io.github.leticiarose.quarkussocial.rest;

import io.github.leticiarose.quarkussocial.domain.model.Post;
import io.github.leticiarose.quarkussocial.domain.model.User;
import io.github.leticiarose.quarkussocial.domain.repository.PostRepository;
import io.github.leticiarose.quarkussocial.domain.repository.UserRepository;
import io.github.leticiarose.quarkussocial.rest.dto.CreatePostRequest;
import io.github.leticiarose.quarkussocial.rest.dto.PostResponse;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

//subRecurso
@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private UserRepository userRepository;
    private PostRepository repository;

    @Inject
    public PostResource(
            UserRepository userRepository,
            PostRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response savePost(
            @PathParam("userId") Long userId, CreatePostRequest request){

        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());

        repository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts( @PathParam("userId") Long userId ){
        User user = userRepository.findById(userId);

        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        //repository.find("user",user) -> ("propriedadeDoRepository", objeto)
        var query = repository.find("user",user);
        var list = query.list();

        var postResponseList = list.stream()
             .map(post -> PostResponse.responsePost(post))
                .collect(Collectors.toList());

        return Response.ok(postResponseList).build();
    }

}