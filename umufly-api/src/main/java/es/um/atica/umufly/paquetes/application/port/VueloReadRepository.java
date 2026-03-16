package es.um.atica.umufly.paquetes.application.port;

import org.springframework.data.domain.Page;

import es.um.atica.umufly.paquetes.domain.model.Vuelo;

public interface VueloReadRepository {

    Page<Vuelo> findVuelosDisponibles(int page, int size);

    boolean existeVueloDisponible(String id);

}
