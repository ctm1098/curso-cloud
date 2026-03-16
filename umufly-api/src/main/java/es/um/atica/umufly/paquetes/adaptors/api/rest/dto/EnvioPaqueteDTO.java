package es.um.atica.umufly.paquetes.adaptors.api.rest.dto;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation( collectionRelation = "enviosPaquetes", itemRelation = "envioPaquete" )
public class EnvioPaqueteDTO extends RepresentationModel<EnvioPaqueteDTO> {
    private UUID idEnvio;
    private UUID numeroSeguimiento;
    private ParticipanteEnvioDTO remitente;
    private ParticipanteEnvioDTO destinatario;
    private PaqueteDTO paquete;
    private Double importe;
    private EstadoEnvioDTO estado;

    public EnvioPaqueteDTO(UUID idEnvio, UUID numeroSeguimiento, ParticipanteEnvioDTO remitente,
            ParticipanteEnvioDTO destinatario, PaqueteDTO paquete, Double importe, EstadoEnvioDTO estado) {
        this.idEnvio = idEnvio;
        this.numeroSeguimiento = numeroSeguimiento;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.paquete = paquete;
        this.importe = importe;
        this.estado = estado;
    }
    public UUID getIdEnvio() {
        return idEnvio;
    }
    public UUID getNumeroSeguimiento() {
        return numeroSeguimiento;
    }
    public ParticipanteEnvioDTO getRemitente() {
        return remitente;
    }
    public ParticipanteEnvioDTO getDestinatario() {
        return destinatario;
    }
    public PaqueteDTO getPaquete() {
        return paquete;
    }
    public Double getImporte() {
        return importe;
    }
    public EstadoEnvioDTO getEstado() {
        return estado;
    }

    
}
