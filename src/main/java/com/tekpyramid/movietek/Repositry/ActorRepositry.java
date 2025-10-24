package com.tekpyramid.movietek.Repositry;

import com.tekpyramid.movietek.Entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepositry extends JpaRepository<Actor, Integer> {
}
