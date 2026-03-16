package es.um.atica.umufly.paquetes.domain.model;

import java.util.UUID;

public class ParticipanteEnvio {

    private UUID id;
    private NombreCompleto nombreCompleto;
    private DocumentoIdentidad documentoIdentidad;
    private CorreoElectronico correoElectronico;
    private TelefonoContacto telefonoContacto;
    private TipoParticipante tipoParticipante;

    private ParticipanteEnvio(UUID id, NombreCompleto nombreCompleto, DocumentoIdentidad documentoIdentidad,
            CorreoElectronico correoElectronico, TelefonoContacto telefonoContacto, TipoParticipante tipoParticipante) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.documentoIdentidad = documentoIdentidad;
        this.correoElectronico = correoElectronico;
        this.telefonoContacto = telefonoContacto;
        this.tipoParticipante = tipoParticipante;
    }

    public static ParticipanteEnvio of(UUID id, NombreCompleto nombreCompleto, DocumentoIdentidad documentoIdentidad, CorreoElectronico correoElectronico, TelefonoContacto telefonoContacto, TipoParticipante tipoParticipante) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del participante no puede ser nulo");
        }
        if (nombreCompleto == null) {
            throw new IllegalArgumentException("El nombre completo del participante de envío no puede ser nulo");
        }
        if (documentoIdentidad == null) {
            throw new IllegalArgumentException("El documento de identidad del participante de envío no puede ser nulo");
        }
        if (correoElectronico == null) {
            throw new IllegalArgumentException("El correo electrónico del participante de envío no puede ser nulo");
        }
        if (telefonoContacto == null) {
            throw new IllegalArgumentException("El teléfono de contacto del participante de envío no puede ser nulo");
        }
        if (tipoParticipante == null) {
            throw new IllegalArgumentException("El tipo de participante no puede ser nulo");
        }

        return new ParticipanteEnvio(id, nombreCompleto, documentoIdentidad, correoElectronico, telefonoContacto, tipoParticipante);
    }

    public UUID getId() {
        return id;
    }

    public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }

    public DocumentoIdentidad getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public CorreoElectronico getCorreoElectronico() {
        return correoElectronico;
    }

    public TelefonoContacto getTelefonoContacto() {
        return telefonoContacto;
    }

    public TipoParticipante getTipoParticipante() {
        return tipoParticipante;
    }


    
    

}
