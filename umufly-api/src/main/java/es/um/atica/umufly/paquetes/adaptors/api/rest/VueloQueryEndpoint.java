package es.um.atica.umufly.paquetes.adaptors.api.rest;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.VueloDTO;
import es.um.atica.umufly.paquetes.application.usecase.listarvuelos.ListarVuelosQuery;
import es.um.atica.umufly.paquetes.application.usecase.listarvuelos.ListarVuelosQueryHandler;
import es.um.atica.umufly.paquetes.domain.model.Vuelo;
import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class VueloQueryEndpoint {

    private final ListarVuelosQueryHandler listarVuelosQueryHandler;
    private final PagedResourcesAssembler<Vuelo> pagedResourcesAssembler;
    private final VuelosModelAssembler vuelosModelAssembler;
    private final AuthServiceEnvios authService;

    public VueloQueryEndpoint(ListarVuelosQueryHandler listarVuelosQueryHandler, AuthServiceEnvios authService,
            PagedResourcesAssembler<Vuelo> pagedResourcesAssembler, VuelosModelAssembler vuelosModelAssembler) {
        this.listarVuelosQueryHandler = listarVuelosQueryHandler;
        this.authService = authService;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.vuelosModelAssembler = vuelosModelAssembler;
    }

    @Operation( summary = "Obtener vuelos disponibles", description = "Devuelve la lista lista de vuelos disponibles (pendientes o retrasados)" )
	@ApiResponse( responseCode = "200", description = "OK" )
	@ApiResponse( responseCode = "404", description = "No hay vuelos" )
	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_2 + Constants.RESOURCE_ENVIOS + Constants.RESOURCE_VUELOS )
	public CollectionModel<VueloDTO> getEnvios( @AuthenticationPrincipal UsuarioEntity usuario, @RequestHeader( name = "UMU-Usuario", required = true ) String userHeader, @RequestParam( name = "page", defaultValue = "0" ) int page, @RequestParam( name = "size", defaultValue = "25" ) int size )
			throws Exception {
		return pagedResourcesAssembler.toModel(listarVuelosQueryHandler.handle(ListarVuelosQuery.of( page, size)), vuelosModelAssembler);
	}

}
