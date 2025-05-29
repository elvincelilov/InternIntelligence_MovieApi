package com.intern.movieApi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Genre {
    ACTION,
    DRAMA,
    COMEDY,
    HORROR,
    SCI_FI,
    ROMANCE,
    THRILLER;

    @JsonCreator
    public static Genre fromString(String value) {
        return switch (value.toUpperCase()) {
            case "ACTION", "action" -> ACTION;
            case "DRAMA", "drama" -> DRAMA;
            case "COMEDY", "comedy" -> COMEDY;
            case "HORROR", "horror" -> HORROR;
            case "SCI_FI", "sciFi", "SCIFI", "scifi" -> SCI_FI;
            case "ROMANCE", "romance" -> ROMANCE;
            case "THRILLER", "thriller" -> THRILLER;
            default -> throw new IllegalArgumentException("Unknown genre: " + value);
        };
    }
}
