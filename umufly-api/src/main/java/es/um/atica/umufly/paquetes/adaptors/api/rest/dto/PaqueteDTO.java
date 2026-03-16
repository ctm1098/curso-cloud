package es.um.atica.umufly.paquetes.adaptors.api.rest.dto;

import java.util.UUID;

public class PaqueteDTO {

    private UUID id;
    private String descripcionContenido;
    private Double pesoKg;
    private boolean fragil;
    public PaqueteDTO(UUID id, String descripcionContenido, Double pesoKg, boolean fragil) {
        this.id = id;
        this.descripcionContenido = descripcionContenido;
        this.pesoKg = pesoKg;
        this.fragil = fragil;
    }
    public UUID getId() {
        return id;
    }
    public String getDescripcionContenido() {
        return descripcionContenido;
    }
    public Double getPesoKg() {
        return pesoKg;
    }
    public boolean isFragil() {
        return fragil;
    }

    

}
