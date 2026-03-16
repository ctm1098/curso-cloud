package es.um.atica.umufly.paquetes.application.port;

import org.springframework.data.domain.Page;

import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

public interface EnvioPaqueteReadRepository {

    Page<EnvioPaquete> findEnvios(DocumentoIdentidad documentoIdentidad, int page, int size);

}
