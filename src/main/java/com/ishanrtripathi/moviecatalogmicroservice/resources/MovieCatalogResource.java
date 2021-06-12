package com.ishanrtripathi.moviecatalogmicroservice.resources;

import com.ishanrtripathi.moviecatalogmicroservice.models.CatalogItem;
import com.ishanrtripathi.moviecatalogmicroservice.models.Movie;
import com.ishanrtripathi.moviecatalogmicroservice.models.Rating;
import com.ishanrtripathi.moviecatalogmicroservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        // get all movieid
        // for each movieid, call movie-info-microservice
        // put everything together

        //RestTemplate restTemplate= new RestTemplate();

        UserRating ratings= restTemplate.getForObject("http://localhost:8083/ratings/users/"+userId, UserRating.class);

        return ratings
                .getUserRatings()
                .stream()
                .map(
                        rating -> {
                            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                            return new CatalogItem(movie.getMovieName(), "Random desc", rating.getRating());
                        }
                )
                .collect(Collectors.toList());
        /*
        if(userId.equals("ishanr"))
        return new ArrayList<>(){{
            add(new CatalogItem("Transformer", "Robot movie", 8));
            add(new CatalogItem("Titanic", "Drowning movie", 6));
        }};
        else
            return new ArrayList<>(){{
                add(new CatalogItem("Pokemon", "Fictional movie", 5));
                add(new CatalogItem("Frozen", "Barbie movie", 7));
            }};

         */
    }
}
