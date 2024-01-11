package pt.moviestats.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import pt.moviestats.presentation.MovieDTO.CreateMovieDTO;
import pt.moviestats.presentation.MovieDTO.UpdateMovieDTO;

@RequestMapping("/api/movies")
public interface MovieStatsAPI {

    @Operation(summary = "Get one movie.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the movie",
            content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
            }
        ),
        @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Invalid movie ID", content = {@Content})
    })
    @GetMapping("{movieId}")
    MovieDTO getOneMovie(@PathVariable long movieId);

    @Operation(summary = "Create one Movie")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "The movie was created",
            content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))
            }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content})
    })
    @PostMapping("")
    long addOneMovie(@RequestBody CreateMovieDTO movie);

    @Operation(summary = "Update one movie.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Updated the movie",
            content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
            }),
        @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Invalid movie ID", content = {@Content})
    })
    @PutMapping("{movieId}")
    MovieDTO updateOneMovie(@PathVariable long movieId, @RequestBody UpdateMovieDTO value);

    @Operation(summary = "Delete one movie.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Deleted the movie", content = {@Content}),
        @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Invalid movie ID", content = {@Content})
    })
    @DeleteMapping("{movieId}")
    void deleteOneMovie(@PathVariable long movieId);


    @Operation(summary = "Get one movie.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the movie",
            content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO[].class))
            }
        ),
        @ApiResponse(responseCode = "400", description = "Bad request", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Invalid movie ID", content = {@Content})
    })
    @GetMapping(value = "/filter/date", params = {"from", "to"})
    List<MovieDTO> getAllMoviesFilteredByLaunchDate(@RequestParam long from, @RequestParam long to);
}
