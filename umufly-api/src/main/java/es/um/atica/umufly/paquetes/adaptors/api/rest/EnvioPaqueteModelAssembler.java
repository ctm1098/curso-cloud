package es.um.atica.umufly.paquetes.adaptors.api.rest;

import java.util.UUID;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.paquetes.adaptors.api.rest.dto.EnvioPaqueteDTO;
import es.um.atica.umufly.paquetes.adaptors.api.rest.mapper.ApiRestMapper;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;
import es.um.atica.umufly.parking.adaptors.api.rest.LinkServiceParking;

@Component
public class EnvioPaqueteModelAssembler implements RepresentationModelAssembler<EnvioPaquete, EnvioPaqueteDTO> {

    private final LinkServiceParking linkService;

	public EnvioPaqueteModelAssembler( LinkServiceParking linkService ) {
		this.linkService = linkService;
	}
    
    @Override
    public EnvioPaqueteDTO toModel(EnvioPaquete entity) {
        EnvioPaqueteDTO dto = ApiRestMapper.envioPaqueteToDTO(entity);
        dto.add(linkSelf(entity.getId()));
        return dto;
    }

    private Link linkSelf(UUID id) {
        return Link.of(linkService.privateApi().path( Constants.RESOURCE_ENVIOS ).pathSegment( id.toString() ).build().toString());
    }

}
