package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper.TipoParticipanteConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "muchovuelo_participante_envio")
public class ParticipanteEnvioEntity {

    private String idParticipante;
    private String nombre;
    private String apellidos;
    private String numeroDocumento;
    private TipoDocumentoEnumEntity tipoDocumento;
    private String email;
    private String telefono;
    private TipoParticipanteEnumEntity tipoParticipante;

    public ParticipanteEnvioEntity() {
    }

    public ParticipanteEnvioEntity(String idParticipante, String nombre, String apellidos,
                                   String numeroDocumento, TipoDocumentoEnumEntity tipoDocumento,
                                   String email, String telefono, TipoParticipanteEnumEntity tipoParticipante) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroDocumento = numeroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.email = email;
        this.telefono = telefono;
        this.tipoParticipante = tipoParticipante;
    }

    @Id
    @Column(name = "id_participante", length = 100)
    public String getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(String idParticipante) {
        this.idParticipante = idParticipante;
    }

    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellidos", nullable = false, length = 150)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name = "numero_documento", nullable = false, length = 15)
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Column(name = "tipo_documento", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    public TipoDocumentoEnumEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEnumEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Column(name = "email", nullable = false, length = 250)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "telefono", length = 30)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "tipo_participante", nullable = false, length = 20)
    @Convert(converter = TipoParticipanteConverter.class)
    public TipoParticipanteEnumEntity getTipoParticipante() {
        return tipoParticipante;
    }

    public void setTipoParticipante(TipoParticipanteEnumEntity tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }
}
