package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EstadoEnvioEnumEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EstadoVueloEnum;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.PaqueteEnvioEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.ParticipanteEnvioEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoDocumentoEnumEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoParticipanteEnumEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoVueloEnum;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioVueloExtViewEntity;
import es.um.atica.umufly.paquetes.domain.model.Avion;
import es.um.atica.umufly.paquetes.domain.model.CorreoElectronico;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.EstadoEnvio;
import es.um.atica.umufly.paquetes.domain.model.EstadoVuelo;
import es.um.atica.umufly.paquetes.domain.model.Importe;
import es.um.atica.umufly.paquetes.domain.model.Itinerario;
import es.um.atica.umufly.paquetes.domain.model.NombreCompleto;
import es.um.atica.umufly.paquetes.domain.model.Paquete;
import es.um.atica.umufly.paquetes.domain.model.ParticipanteEnvio;
import es.um.atica.umufly.paquetes.domain.model.TelefonoContacto;
import es.um.atica.umufly.paquetes.domain.model.TipoDocumento;
import es.um.atica.umufly.paquetes.domain.model.TipoParticipante;
import es.um.atica.umufly.paquetes.domain.model.TipoVuelo;
import es.um.atica.umufly.paquetes.domain.model.Vuelo;

public class JpaPersistenceMapper {

    private JpaPersistenceMapper() {
        throw new IllegalStateException("Clase JPA mapper");
    }

    public static EnvioPaquete envioPaqueteToModel(EnvioEntity entity) {
        ParticipanteEnvio remitente = participanteEnvioToModel(entity.getRemitente());
        ParticipanteEnvio destinatario = participanteEnvioToModel(entity.getDestinatario());
        Paquete paquete = paqueteMoModel(entity.getPaquete());
        Importe importe = new Importe(entity.getImporteEnvio() == null ? 0.0 : entity.getImporteEnvio().doubleValue());
        EstadoEnvio estado = estadoEnvioToModel(entity.getEstado());

        return EnvioPaquete.of(UUID.fromString(entity.getIdEnvio()), UUID.fromString(entity.getIdSeguimiento()), remitente, destinatario, paquete, importe, estado, UUID.fromString(entity.getVuelo()));
    }

    public static EstadoEnvio estadoEnvioToModel(EstadoEnvioEnumEntity estado) {
        return switch (estado) {
            case ENTREGADO -> EstadoEnvio.ENTREGADO;
            case EN_TRANSITO -> EstadoEnvio.EN_TRANSITO;
            case FACTURADO -> EstadoEnvio.FACTURADO;
            default -> throw new IllegalArgumentException( "Valor inesperado para EstadoEnvio: " + estado );
        };
    }

    public static Paquete paqueteMoModel(PaqueteEnvioEntity entity) {
        return Paquete.of(UUID.fromString(entity.getIdPaquete()), entity.getDescripcion(), entity.getPesoKg() == null ? 0 : entity.getPesoKg().doubleValue(), "S".equalsIgnoreCase(entity.getFragil()));
    }   

    public static ParticipanteEnvio participanteEnvioToModel(ParticipanteEnvioEntity entity) {
        String[] apellidos = entity.getApellidos().split(" ");
        NombreCompleto nombreCompleto = new NombreCompleto(entity.getNombre(), apellidos[0], apellidos.length == 1 ? "" : String.join(" ", Arrays.copyOfRange(apellidos, 1, apellidos.length)));
        DocumentoIdentidad documento = new DocumentoIdentidad(tipoDocumentoToModel(entity.getTipoDocumento()), entity.getNumeroDocumento());
        CorreoElectronico correoElectronico = new CorreoElectronico(entity.getEmail());
        TelefonoContacto tlf = new TelefonoContacto(entity.getTelefono());
        TipoParticipante tipo = TipoParticipante.valueOf(entity.getTipoParticipante().toString());

        return ParticipanteEnvio.of(UUID.fromString(entity.getIdParticipante()), nombreCompleto, documento, correoElectronico, tlf, tipo);
        
    }

    public static TipoDocumento tipoDocumentoToModel(TipoDocumentoEnumEntity tipoDocumento) {
        return switch ( tipoDocumento ) {
			case N -> TipoDocumento.NIF;
			case E -> TipoDocumento.NIE;
			case P -> TipoDocumento.PASAPORTE;
			default -> throw new IllegalArgumentException( "Unexpected value: " + tipoDocumento );
		};
    }

    public static EnvioEntity envioToEntity(EnvioPaquete model) {
        EnvioEntity entity = new EnvioEntity();
        entity.setRemitente( participanteToEntity(model.getRemitente()));
        entity.setDestinatario(participanteToEntity(model.getDestinatario()));
        entity.setPaquete(paqueteToEntity(model.getPaquete()));
        entity.setEstado(estadoEnvioToEntity(model.getEstado()));
        entity.setIdEnvio(model.getId().toString());
        entity.setIdSeguimiento(model.getNumeroSeguimiento().toString());
        entity.setImporteEnvio(BigDecimal.valueOf(model.getImporte().valor()));
        entity.setVuelo(model.getIdVuelo().toString());

        return entity;
    }

    public static EstadoEnvioEnumEntity estadoEnvioToEntity(EstadoEnvio estado) {
        return switch(estado) {
            case ENTREGADO -> EstadoEnvioEnumEntity.ENTREGADO;
            case FACTURADO -> EstadoEnvioEnumEntity.FACTURADO;
            case EN_TRANSITO -> EstadoEnvioEnumEntity.EN_TRANSITO;
            default -> throw new IllegalArgumentException("Valor desconocido para EstadoEnvioEnumEntity: " + estado);
        };
    }

    public static PaqueteEnvioEntity paqueteToEntity(Paquete model) {
        PaqueteEnvioEntity entity = new PaqueteEnvioEntity();
        entity.setDescripcion(model.getDescripcionContenido());
        entity.setFragil(model.isFragil() ? "S" : "N");
        entity.setIdPaquete(model.getId().toString());
        entity.setPesoKg(BigDecimal.valueOf(model.getPeso()));

        return entity;
    }

    public static ParticipanteEnvioEntity participanteToEntity(ParticipanteEnvio model) {
        ParticipanteEnvioEntity entity = new ParticipanteEnvioEntity();
        entity.setApellidos(model.getNombreCompleto().getApellidos());
        entity.setEmail(model.getCorreoElectronico().valor());
        entity.setIdParticipante(model.getId().toString());
        entity.setNombre(model.getNombreCompleto().nombre());
        entity.setNumeroDocumento(model.getDocumentoIdentidad().identificador());
        entity.setTelefono(model.getTelefonoContacto().numero());
        entity.setTipoDocumento(tipoDocumentoToEntity(model.getDocumentoIdentidad().tipo()));
        entity.setTipoParticipante(tipoParticipanteToEntity(model.getTipoParticipante()));

        return entity;
    }

    public static TipoParticipanteEnumEntity tipoParticipanteToEntity(TipoParticipante tipoParticipante) {
        return switch(tipoParticipante) {
            case DESTINATARIO -> TipoParticipanteEnumEntity.DESTINATARIO;
            case REMITENTE -> TipoParticipanteEnumEntity.REMITENTE;
            default -> throw new IllegalArgumentException("Valor inesperado para TipoParticipanteEnumEntity: " + tipoParticipante);
        };
    }

    public static TipoDocumentoEnumEntity tipoDocumentoToEntity(TipoDocumento tipo) {
        return switch ( tipo ) {
			case NIF -> TipoDocumentoEnumEntity.N;
			case NIE -> TipoDocumentoEnumEntity.E;
			case PASAPORTE -> TipoDocumentoEnumEntity.P;
			default -> throw new IllegalArgumentException( "Valor inesperado para TipoDocumentoEnumEntity: " + tipo );
		};
    }

    public static Vuelo vueloToModel( EnvioVueloExtViewEntity v ) {
		return Vuelo.of( UUID.fromString( v.getId() ), new Itinerario( v.getFechaSalida(), v.getFechaLlegada(), v.getOrigen(), v.getDestino() ), tipoVueloToModel( v.getTipoVuelo() ), estadoVueloToModel( v.getEstadoVuelo() ),
				new Avion( v.getCapacidadAvion() ) );
	}

    private static TipoVuelo tipoVueloToModel( TipoVueloEnum t ) {
		switch ( t ) {
			case I:
				return TipoVuelo.INTERNACIONAL;
			case N:
				return TipoVuelo.NACIONAL;
			default:
				throw new IllegalArgumentException( "Tipo de vuelo no soportado" );
		}
	}

	private static EstadoVuelo estadoVueloToModel( EstadoVueloEnum e ) {
		switch ( e ) {
			case P:
				return EstadoVuelo.PENDIENTE;
			case R:
				return EstadoVuelo.RETRASADO;
			case C:
				return EstadoVuelo.COMPLETADO;
			case X:
				return EstadoVuelo.CANCELADO;
			default:
				throw new IllegalArgumentException( "Estado de vuelo no soportado" );
		}
	}

}
