package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "MUCHOVUELO_PAQUETE_ENVIO", schema = "FORMACION_TICARUM")
public class PaqueteEnvioEntity {

    private String idPaquete;
    private String descripcion;
    private BigDecimal pesoKg;
    private String fragil;

    public PaqueteEnvioEntity() {
    }

    public PaqueteEnvioEntity(String idPaquete, String descripcion, BigDecimal pesoKg, String fragil) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.fragil = fragil;
    }

    @Id
    @Column(name = "id_paquete", length = 100)
    public String getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }

    @Column(name = "descripcion", nullable = false, length = 500)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "peso_kg", nullable = false, precision = 8, scale = 2)
    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    @Column(name = "fragil", nullable = false, length = 1)
    public String getFragil() {
        return fragil;
    }

    public void setFragil(String fragil) {
        this.fragil = fragil;
    }
}