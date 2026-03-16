package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioVueloExtViewEntity;

public interface JpaEnvioVueloRepository extends JpaRepository<EnvioVueloExtViewEntity, String> {

    @Query("SELECT v FROM EnvioVueloExtViewEntity v WHERE v.estadoVuelo IN ('R','P')")
    Page<EnvioVueloExtViewEntity> findVuelosDisponibles(PageRequest pageRequest);

    @Query("""
       SELECT COUNT(v) > 0
       FROM EnvioVueloExtViewEntity v
       WHERE v.id = :id
       AND (v.estadoVuelo = 'R' OR v.estadoVuelo = 'P')
       """)
    boolean existsVueloDisponibleById(@Param("id") String id);
    

}
