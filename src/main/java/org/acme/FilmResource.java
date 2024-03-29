package org.acme;

import io.smallrye.common.annotation.NonBlocking;
import org.acme.domain.Ally;
import org.acme.domain.Film;
import org.acme.domain.Hero;
import org.acme.services.GalaxyService;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
public class FilmResource {

    private final GalaxyService galaxyService;

    public FilmResource(GalaxyService galaxyService) {
        this.galaxyService = galaxyService;
    }

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return galaxyService.getAllFilms();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    @NonBlocking
    public Film getFilm(@Name("filmID") int id) {
        return galaxyService.getFilm(id);
    }

    @Query
    @Description("Get heroes by film")
    public List<Hero> heroes(@Source Film film) {
        return galaxyService.getHeroesByFilm(film);
    }

    @Query
    @Description("Get all allies")
    public List<Ally> allies() {
        return galaxyService.getAllAllies();
    }
}
