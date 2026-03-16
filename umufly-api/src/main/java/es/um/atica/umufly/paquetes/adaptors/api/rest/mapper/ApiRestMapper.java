package es.um.atica.umufly.paquetes.adaptors.api.rest.mapper;

import java.util.Arrays;
import java.util.UUID;

import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.AvionDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.DocumentoIdentidadDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.EnvioPaqueteDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.EstadoEnvioDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.EstadoVuelo;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.ItinerarioDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.PaqueteDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.ParticipanteEnvioDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.TipoDocumentoDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.TipoParticipanteDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.TipoVuelo;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.VueloDTO;
import es.um.atica.umufly.paquetes.domain.model.CorreoElectronico;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.paquetes.domain.model.NombreCompleto;
import es.um.atica.umufly.paquetes.domain.model.Paquete;
import es.um.atica.umufly.paquetes.domain.model.ParticipanteEnvio;
import es.um.atica.umufly.paquetes.domain.model.TelefonoContacto;
import es.um.atica.umufly.paquetes.domain.model.TipoDocumento;
import es.um.atica.umufly.paquetes.domain.model.TipoParticipante;
import es.um.atica.umufly.paquetes.domain.model.Vuelo;
import jakarta.validation.Valid;

public class ApiRestMapper {

    private ApiRestMapper() {
        throw new IllegalStateException("Clase mapper");
    }

    public static VueloDTO vueloToDTO( Vuelo vuelo ) {
		return new VueloDTO( vuelo.getId(), new ItinerarioDTO( vuelo.getItinerario().salida(), vuelo.getItinerario().llegada(), vuelo.getItinerario().origen(), vuelo.getItinerario().destino() ), TipoVuelo.valueOf( vuelo.getTipo().toString() ), EstadoVuelo.valueOf( vuelo.getEstado().toString() ),
				new AvionDTO( vuelo.getAvion().capacidad() ) );
	}

    public static EnvioPaqueteDTO envioPaqueteToDTO(EnvioPaquete model) {
        ParticipanteEnvioDTO rem = participanteEnvioToDTO(model.getRemitente());
        ParticipanteEnvioDTO dest = participanteEnvioToDTO(model.getDestinatario());
        PaqueteDTO paquete = paqueteToDTO(model.getPaquete());
        EstadoEnvioDTO estado = EstadoEnvioDTO.valueOf(model.getEstado().toString());

        return new EnvioPaqueteDTO(model.getId(), model.getNumeroSeguimiento(), rem, dest, paquete, model.getImporte().valor(), estado);
    }

    public static PaqueteDTO paqueteToDTO(Paquete model) {
        return new PaqueteDTO(model.getId(), model.getDescripcionContenido(), model.getPeso(), model.isFragil());
    }

    public static DocumentoIdentidadDTO documentoToDTO(DocumentoIdentidad model) {
        DocumentoIdentidadDTO dto = new DocumentoIdentidadDTO();
        dto.setNumero(model.identificador());
        dto.setTipo(TipoDocumentoDTO.valueOf(model.tipo().toString()));
        return dto;
    }

    public static DocumentoIdentidad documentoToModel(DocumentoIdentidadDTO dto) {
        return new DocumentoIdentidad(TipoDocumento.valueOf(dto.getTipo().toString()), dto.getNumero());
    }

    public static ParticipanteEnvioDTO participanteEnvioToDTO(ParticipanteEnvio model) {
        return new ParticipanteEnvioDTO(model.getId(), model.getNombreCompleto().nombre(), model.getNombreCompleto().getApellidos(), model.getCorreoElectronico().valor(), model.getTelefonoContacto().numero(), documentoToDTO(model.getDocumentoIdentidad()),TipoParticipanteDTO.valueOf(model.getTipoParticipante().toString()));
    }

    public static ParticipanteEnvio partipanteToModel(ParticipanteEnvioDTO dto) {
        String[] apellidos = dto.getApellidos().split(" ");
        return ParticipanteEnvio.of(dto.getId(), new NombreCompleto(dto.getNombre(), apellidos[0],  String.join(" ", Arrays.copyOfRange(apellidos, 1, apellidos.length))), documentoToModel(dto.getDocumentoIdentidad()), new CorreoElectronico(dto.getCorreo()), new TelefonoContacto(dto.getTelefono()), TipoParticipante.valueOf(dto.getTipoParticipante().toString()));
    }

    public static Paquete paqueteToModel(PaqueteDTO paquete) {
        return Paquete.of(paquete.getId(), paquete.getDescripcionContenido(), paquete.getPesoKg(), paquete.isFragil());
    }

}
