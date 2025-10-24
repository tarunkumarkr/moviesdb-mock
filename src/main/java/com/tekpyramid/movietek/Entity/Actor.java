package com.tekpyramid.movietek.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "actor_id")
    private int actorId;
    @Column (name = "actor_name")
    private String actorName;
    @Column(name = "birth_year")
    private LocalDate birthYear;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "actors")
    @JsonIgnore
    private List<Movie> moviesList;
}
