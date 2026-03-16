package es.um.atica.umufly.paquetes.adaptors.api.rest.dto;

import java.util.UUID;

public class ParticipanteEnvioDTO {

    private UUID id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private DocumentoIdentidadDTO documentoIdentidad;
    private TipoParticipanteDTO tipoParticipante;

    public ParticipanteEnvioDTO(UUID id, String nombre, String apellidos, String correo, String telefono, DocumentoIdentidadDTO documentoIdentidad,
            TipoParticipanteDTO tipoParticipante) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.documentoIdentidad = documentoIdentidad;
        this.tipoParticipante = tipoParticipante;
    }
    public UUID getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public TipoParticipanteDTO getTipoParticipante() {
        return tipoParticipante;
    }
    public DocumentoIdentidadDTO getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    
}
