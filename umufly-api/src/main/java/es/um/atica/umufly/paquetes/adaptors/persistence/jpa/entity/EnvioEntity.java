package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "muchovuelo_envio",
       uniqueConstraints = {
           @UniqueConstraint(name = "uq_envio_seguimiento", columnNames = "id_seguimiento")
       })
public class EnvioEntity {

    private String idEnvio;
    private ParticipanteEnvioEntity remitente;
    private ParticipanteEnvioEntity destinatario;
    private PaqueteEnvioEntity paquete;
    private String vuelo;
    private String idSeguimiento;
    private BigDecimal importeEnvio;
    private EstadoEnvioEnumEntity estado;

    public EnvioEntity() {
    }

    @Id
    @Column(name = "id_envio", length = 100)
    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_remitente", nullable = false,
                foreignKey = @ForeignKey(name = "fk_envio_remitente"))
    public ParticipanteEnvioEntity getRemitente() {
        return remitente;
    }

    public void setRemitente(ParticipanteEnvioEntity remitente) {
        this.remitente = remitente;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_destinatario", nullable = false,
                foreignKey = @ForeignKey(name = "fk_envio_destinatario"))
    public ParticipanteEnvioEntity getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(ParticipanteEnvioEntity destinatario) {
        this.destinatario = destinatario;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_paquete", nullable = false,
                foreignKey = @ForeignKey(name = "fk_envio_vuelo"))
    public PaqueteEnvioEntity getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteEnvioEntity paquete) {
        this.paquete = paquete;
    }

    @Column(name = "id_vuelo", nullable = false)
    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    @Column(name = "id_seguimiento", nullable = false, length = 50)
    public String getIdSeguimiento() {
        return idSeguimiento;
    }

    public void setIdSeguimiento(String idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

    @Column(name = "importe_envio", nullable = false, precision = 10, scale = 2)
    public BigDecimal getImporteEnvio() {
        return importeEnvio;
    }

    public void setImporteEnvio(BigDecimal importeEnvio) {
        this.importeEnvio = importeEnvio;
    }

    @Column(name = "estado", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    public EstadoEnvioEnumEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvioEnumEntity estado) {
        this.estado = estado;
    }
}
