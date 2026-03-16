package es.um.atica.umufly.paquetes.adaptors.api.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.EnvioPaqueteDTO;
import es.um.atica.umufly.paquetes.application.usecase.listarenvios.ListarEnviosQuery;
import es.um.atica.umufly.paquetes.application.usecase.listarenvios.ListarEnviosQueryHandler;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class EnvioPaqueteQueryEndpoint {

    private final ListarEnviosQueryHandler listarEnviosQueryHandler;
	private final AuthServiceEnvios authService;
	private final PagedResourcesAssembler<EnvioPaquete> pagedResourcesAssembler;
	private final EnvioPaqueteModelAssembler envioPaqueteModelAssembler;


    public EnvioPaqueteQueryEndpoint(EnvioPaqueteModelAssembler envioPaqueteModelAssembler, ListarEnviosQueryHandler listarEnviosQueryHandler, AuthServiceEnvios authService, PagedResourcesAssembler<EnvioPaquete> pagedResourcesAssembler) {
       this.listarEnviosQueryHandler = listarEnviosQueryHandler;
	   this.authService = authService;
	   this.pagedResourcesAssembler = pagedResourcesAssembler;
	   this.envioPaqueteModelAssembler = envioPaqueteModelAssembler;
    }

    @Operation( summary = "Obtener envíos de paqueyes", description = "Devuelve la lista de envíos de paquetes solicitados por un remitente" )
	@ApiResponse( responseCode = "200", description = "OK" )
	@ApiResponse( responseCode = "404", description = "No hay envíos" )
	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_2 + Constants.RESOURCE_ENVIOS )
	public CollectionModel<EnvioPaqueteDTO> getEnvios( @AuthenticationPrincipal UsuarioEntity usuario, @RequestHeader( name = "UMU-Usuario", required = true ) String userHeader, @RequestParam( name = "page", defaultValue = "0" ) int page, @RequestParam( name = "size", defaultValue = "25" ) int size )
			throws Exception {
		//return pagedResourcesAssembler.toModel( listaReservasQueryHandler.handle( ListaReservasQuery.of( authService.parseUserHeader( usuario ), page, size ) ), reservasModelAssembler );
		return pagedResourcesAssembler.toModel(listarEnviosQueryHandler.handle(ListarEnviosQuery.of(authService.parseUserHeader(userHeader), page, size)), envioPaqueteModelAssembler);
	}

}
