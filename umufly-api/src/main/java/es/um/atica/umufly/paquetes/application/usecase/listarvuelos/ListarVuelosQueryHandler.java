package es.um.atica.umufly.paquetes.application.usecase.listarvuelos;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.paquetes.application.port.VueloReadRepository;
import es.um.atica.umufly.paquetes.domain.model.Vuelo;

@Component
public class ListarVuelosQueryHandler implements QueryHandler<Page<Vuelo>, ListarVuelosQuery> {

    private final VueloReadRepository vueloReadRepository;

    public ListarVuelosQueryHandler( VueloReadRepository reservasVueloRepository ) {
		this.vueloReadRepository = reservasVueloRepository;
	}

	@Override
	public Page<Vuelo> handle( ListarVuelosQuery query ) throws Exception {
		return vueloReadRepository.findVuelosDisponibles(query.getPagina(), query.getTamanioPagina());
	}

}
