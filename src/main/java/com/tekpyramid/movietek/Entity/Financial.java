package com.tekpyramid.movietek.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity

public class Financial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "financial_id")
    private int financialId;
    @Column(name = "budget")
    private double budget;
    @Column (name = "revenue")
    private double  revenue;
    @Column (name = "unit")
    private String unit;
    @Column (name = "currency")
    private String currency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movies;
}
