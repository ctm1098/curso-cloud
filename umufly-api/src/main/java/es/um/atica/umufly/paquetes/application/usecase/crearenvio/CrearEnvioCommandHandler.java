package es.um.atica.umufly.paquetes.application.usecase.crearenvio;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.paquetes.application.port.EnvioPaqueteWriteRepository;
import es.um.atica.umufly.paquetes.application.port.VueloReadRepository;
import es.um.atica.umufly.paquetes.domain.exception.VueloNoDisponibleException;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

@Component
public class CrearEnvioCommandHandler implements SyncCommandHandler<EnvioPaquete, CrearEnvioCommand> {

    private final VueloReadRepository vueloReadRepository;
    private final EnvioPaqueteWriteRepository envioPaqueteWriteRepository;

    

    public CrearEnvioCommandHandler(VueloReadRepository vueloReadRepository, EnvioPaqueteWriteRepository envioPaqueteWriteRepository) {
        this.vueloReadRepository = vueloReadRepository;
        this.envioPaqueteWriteRepository = envioPaqueteWriteRepository;
    }



    @Override
    public EnvioPaquete handle(CrearEnvioCommand command) throws VueloNoDisponibleException {
        boolean vueloDisponible = this.vueloReadRepository.existeVueloDisponible(command.getIdVuelo().toString());
        if (vueloDisponible) {
            EnvioPaquete envio = EnvioPaquete.crear(command.getRem(), command.getDest(), command.getIdVuelo(), command.getPaquete());
            this.envioPaqueteWriteRepository.persistirEnvio(envio);
            return envio;
        }
        throw new VueloNoDisponibleException("El vuelo " + command.getIdVuelo() + " no está disponible");
    }

}
