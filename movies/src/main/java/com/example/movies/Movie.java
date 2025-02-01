package com.example.movies;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter  // ✅ Add this to ensure proper serialization
public class Movie {
    @Id
    @JsonProperty("id")
    private ObjectId id;
    @JsonProperty("imdbId")
    private String imdbId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("trailerLink")
    private String trailerLink;
    @JsonProperty("poster")
    private String poster;
    @JsonProperty("backdrops")
    private List<String> backdrops;
    @JsonProperty("genres")
    private List<String> genres;



    @DocumentReference
    @JsonProperty("reviews")
    private List<Review> reviews;
}
