package es.um.atica.umufly.paquetes.domain.model;

import java.util.UUID;

public class EnvioPaquete {

    private UUID id;
    private UUID numeroSeguimiento;
    private ParticipanteEnvio remitente;
    private ParticipanteEnvio destinatario;
    private Paquete paquete;
    private Importe importe;
    private EstadoEnvio estado;
    private UUID idVuelo;

    private EnvioPaquete(UUID id, UUID numeroSeguimiento, ParticipanteEnvio remitente, ParticipanteEnvio destinatario, Paquete paquete, Importe importe, EstadoEnvio estado, UUID idVuelo) {
        this.id = id;
        this.numeroSeguimiento = numeroSeguimiento;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.paquete = paquete;
        this.importe = importe;
        this.estado = estado;
        this.idVuelo = idVuelo;
    }

    public static EnvioPaquete of(UUID id, UUID numeroSeguimiento, ParticipanteEnvio remitente, ParticipanteEnvio destinatario, Paquete paquete, Importe importe, EstadoEnvio estado, UUID idVuelo) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del envío no puede ser nulo");
        }
        if (numeroSeguimiento == null) {
            throw new IllegalArgumentException("El número de seguimiento del envío no puede ser nulo");
        }
        if (remitente == null) {
            throw new IllegalArgumentException("El remitente del envío no puede ser nulo");
        }
        if (destinatario == null) {
            throw new IllegalArgumentException("El destinatario del envío no puede ser nulo");
        }
        if (!TipoParticipante.REMITENTE.equals(remitente.getTipoParticipante())) {
            throw new IllegalArgumentException("El remitente tiene que ser un participante de tipo REMITENTE");
        }
        if (!TipoParticipante.DESTINATARIO.equals(destinatario.getTipoParticipante())) {
            throw new IllegalArgumentException("El destinatario tiene que ser un participante de tipo DESTINATARIO");
        }
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete del envío no puede ser nulo");
        }
        if (importe == null) {
            throw new IllegalArgumentException("El importe del envío no puede ser nulo");
        }
        if (estado == null) {
            throw new IllegalArgumentException("El estado del envío no puede ser nulo");
        }
        if (idVuelo == null) {
            throw new IllegalArgumentException("El ID del vuelo no puede ser nulo");
        }

        return new EnvioPaquete(id, numeroSeguimiento, remitente, destinatario, paquete, importe, estado, idVuelo);
    }

    public UUID getId() {
        return id;
    }

    public UUID getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public ParticipanteEnvio getRemitente() {
        return remitente;
    }

    public ParticipanteEnvio getDestinatario() {
        return destinatario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public Importe getImporte() {
        return importe;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public UUID getIdVuelo() {
        return idVuelo;
    }

    public static EnvioPaquete crear(ParticipanteEnvio remitente, ParticipanteEnvio destinatario, UUID idVuelo, Paquete paquete) {
        //2. Calcular importe
        double multiplicador = 2.5;
        if (paquete.isFragil()) {
            multiplicador = 4.0;
        }
        Importe importe = new Importe(paquete.getPeso()*multiplicador);
        //3. Generar id del envio
        UUID idEnvio = UUID.randomUUID();
        //4. Crear estado
        EstadoEnvio estado = EstadoEnvio.FACTURADO;
        //5. Crear num seguimiento
        UUID numSeguimiento = UUID.randomUUID();
        //5. Crear envio
        return of(idEnvio, numSeguimiento, remitente, destinatario, paquete, importe, estado, idVuelo);
    }

    

    


}
