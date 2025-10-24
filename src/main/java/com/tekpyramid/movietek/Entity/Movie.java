package com.tekpyramid.movietek.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private int moviesId;
    @Column(name="movie_Title")
    private String movieTitle;
    @Column(name = "industry")
    private String industry;
    @Column (name = "release_Year")
    private int releaseYear;
    @Column (name = "imdb_Rating")
    private double imdbRating;
    @Column (name = "studio")
    private String studio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language lang;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "movies")
    private Financial financials;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

}
