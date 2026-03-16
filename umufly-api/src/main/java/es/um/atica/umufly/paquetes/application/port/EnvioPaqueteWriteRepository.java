package es.um.atica.umufly.paquetes.application.port;

import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

public interface EnvioPaqueteWriteRepository {

    void persistirEnvio(EnvioPaquete envio);

}
