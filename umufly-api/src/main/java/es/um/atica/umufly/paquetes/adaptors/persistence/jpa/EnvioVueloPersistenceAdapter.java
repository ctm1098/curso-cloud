package es.um.atica.umufly.paquetes.adaptors.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper.JpaPersistenceMapper;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository.JpaEnvioVueloRepository;
import es.um.atica.umufly.paquetes.application.port.VueloReadRepository;
import es.um.atica.umufly.paquetes.domain.model.Vuelo;

@Component
public class EnvioVueloPersistenceAdapter implements VueloReadRepository {

    private final JpaEnvioVueloRepository jpaVueloRepository;

    public EnvioVueloPersistenceAdapter(JpaEnvioVueloRepository jpaVueloRepository) {
        this.jpaVueloRepository = jpaVueloRepository;
    }

    @Override
    public Page<Vuelo> findVuelosDisponibles(int page, int size) {
        return jpaVueloRepository.findVuelosDisponibles(PageRequest.of(page, size)).map(JpaPersistenceMapper::vueloToModel);
    }

    @Override
    public boolean existeVueloDisponible(String id) {
        return jpaVueloRepository.existsVueloDisponibleById(id);
    }

}
