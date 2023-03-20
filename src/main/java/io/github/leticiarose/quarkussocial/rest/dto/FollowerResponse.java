package io.github.leticiarose.quarkussocial.rest.dto;

import io.github.leticiarose.quarkussocial.domain.model.Follower;
import lombok.Data;

@Data
public class FollowerResponse {
    private Long id;
    private String name;

    public FollowerResponse(){
    }

    public FollowerResponse(Follower follower){
        this (follower.getId(), follower.getFollower().getName());
    }

    public FollowerResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
