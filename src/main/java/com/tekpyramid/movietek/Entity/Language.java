package com.tekpyramid.movietek.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "language_id")
    private int languageId;
    @Column (name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lang")
    @JsonIgnore
    private List<Movie> moviesList;

}
