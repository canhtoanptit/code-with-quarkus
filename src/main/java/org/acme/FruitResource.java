package org.acme;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.acme.domain.Fruit;
import org.acme.dto.FruitDTO;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/fruits")
@ApplicationScoped
public class FruitResource {

    @GET
    public Uni<List<Fruit>> get() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET()
    @Path("/{id}")
    public Uni<Fruit> getById(Long id) {
        return Fruit.findById(id);
    }

    @POST
    public Uni<RestResponse<Fruit>> createFruit(FruitDTO fruitDTO) {
        Fruit f  = new Fruit();
        f.name = fruitDTO.getName();
        return Panache.withTransaction(f::persist)
                .replaceWith(RestResponse.status(RestResponse.Status.CREATED, f));
    }
}
