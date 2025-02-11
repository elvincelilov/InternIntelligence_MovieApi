package com.intern.movieApi.entity;

import com.intern.movieApi.enums.Genre;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private int releaseYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private double imdbRating;
}
