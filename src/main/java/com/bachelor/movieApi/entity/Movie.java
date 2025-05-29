package com.bachelor.movieApi.entity;

import com.bachelor.movieApi.enums.Genre;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.List;

@Entity
@Table(name ="movie",indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_director", columnList = "director")
})
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

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @BatchSize(size=10)
    private List<Review> reviews;
}
