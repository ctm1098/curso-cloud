package es.um.atica.umufly.paquetes.domain.model;

import java.util.UUID;

public class Paquete {

    private UUID id;
    private String descripcionContenido;
    private double peso;
    private boolean fragil;

    private Paquete(UUID id, String descripcionContenido, double peso, boolean fragil) {
        this.id = id;
        this.descripcionContenido = descripcionContenido;
        this.peso = peso;
        this.fragil = fragil;
    }

    public static Paquete of(UUID id, String descripcionContenido, double peso, boolean fragil) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del paquete no puede ser nulo");
        }
        if (descripcionContenido == null || descripcionContenido.isEmpty()) {
            throw new IllegalArgumentException("La descripción del contenido del paquete no puede ser nulo ni vacío");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso del paquete debe ser mayor que 0");
        }
        return new Paquete(id, descripcionContenido, peso, fragil);
    }

    public UUID getId() {
        return id;
    }

    public String getDescripcionContenido() {
        return descripcionContenido;
    }

    public double getPeso() {
        return peso;
    }

    public boolean isFragil() {
        return fragil;
    }
}
