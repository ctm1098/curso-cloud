package es.um.atica.umufly.paquetes.application.usecase.listarvuelos;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.paquetes.domain.model.Vuelo;

public class ListarVuelosQuery extends Query<Page<Vuelo>> {

    private final Integer pagina;
	private final Integer tamanioPagina;

	private ListarVuelosQuery( int pagina, int tamanioPagina ) {
		this.pagina = pagina;
		this.tamanioPagina = tamanioPagina;
	}

	public static ListarVuelosQuery of( int pagina, int tamanioPagina ) {
		return new ListarVuelosQuery(  pagina, tamanioPagina );
	}

	public Integer getPagina() {
		return pagina;
	}

	public Integer getTamanioPagina() {
		return tamanioPagina;
	}

}
