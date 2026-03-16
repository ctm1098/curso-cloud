package es.um.atica.umufly.paquetes.adaptors.api.rest;

import java.util.UUID;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.EnvioPaqueteDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.PaqueteDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.ParticipanteEnvioDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.mapper.ApiRestMapper;
import es.um.atica.umufly.paquetes.application.usecase.crearenvio.CrearEnvioCommand;
import es.um.atica.umufly.paquetes.application.usecase.crearenvio.CrearEnvioCommandHandler;
import es.um.atica.umufly.paquetes.application.usecase.listarenvios.ListarEnviosQuery;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@Validated
public class EnvioCommandEndpoint {

    private final CrearEnvioCommandHandler crearEnvioCommandHandler;
	private final EnvioPaqueteModelAssembler envioPaqueteModelAssembler;

    public EnvioCommandEndpoint(CrearEnvioCommandHandler crearEnvioCommandHandler,
            PagedResourcesAssembler<EnvioPaquete> pagedResourcesAssembler,
            EnvioPaqueteModelAssembler envioPaqueteModelAssembler) {
        this.crearEnvioCommandHandler = crearEnvioCommandHandler;
        this.envioPaqueteModelAssembler = envioPaqueteModelAssembler;
    }

    @Operation( summary = "Crea un envío de paquete", description = "Registra un envío de paquete en un vuelo" )
	@ApiResponse( responseCode = "200", description = "OK" )
	@PostMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_2 + Constants.RESOURCE_ENVIOS )
	public EnvioPaqueteDTO getEnvios( @AuthenticationPrincipal UsuarioEntity usuario, @RequestHeader( name = "UMU-Usuario", required = true ) String userHeader, @RequestBody @Valid ParticipanteEnvioDTO remitente, @RequestBody @Valid ParticipanteEnvioDTO destinatario,
    @RequestBody @Valid PaqueteDTO paquete, @RequestParam(name = "idVuelo", required = true) String idVuelo )
			throws Exception {
        EnvioPaquete envio = crearEnvioCommandHandler.handle(CrearEnvioCommand.of(ApiRestMapper.partipanteToModel(remitente) , ApiRestMapper.partipanteToModel(destinatario), ApiRestMapper.paqueteToModel(paquete), UUID.fromString(idVuelo)));
        return envioPaqueteModelAssembler.toModel(envio);
    }

}
