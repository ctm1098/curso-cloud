package es.um.atica.umufly.paquetes.application.usecase.crearenvio;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.Paquete;
import es.um.atica.umufly.paquetes.domain.model.ParticipanteEnvio;

public class CrearEnvioCommand extends SyncCommand<EnvioPaquete> {

    private final ParticipanteEnvio rem;
    private final ParticipanteEnvio dest;
    private final Paquete paquete;
    private final UUID idVuelo;
    public CrearEnvioCommand(ParticipanteEnvio rem, ParticipanteEnvio dest, Paquete paquete, UUID idVuelo) {
        this.rem = rem;
        this.dest = dest;
        this.paquete = paquete;
        this.idVuelo = idVuelo;
    }

    public static CrearEnvioCommand of(ParticipanteEnvio rem, ParticipanteEnvio dest, Paquete paquete, UUID idVuelo) {
        //TODO checks...
        return new CrearEnvioCommand(rem, dest, paquete, idVuelo);
    }

    public ParticipanteEnvio getRem() {
        return rem;
    }
    public ParticipanteEnvio getDest() {
        return dest;
    }
    public Paquete getPaquete() {
        return paquete;
    }
    public UUID getIdVuelo() {
        return idVuelo;
    }

    
}
