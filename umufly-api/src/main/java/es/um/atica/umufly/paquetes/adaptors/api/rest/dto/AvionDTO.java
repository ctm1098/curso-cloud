package es.um.atica.umufly.paquetes.adaptors.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude( Include.NON_NULL )
public class AvionDTO {

	@JsonProperty( access = JsonProperty.Access.READ_ONLY )
	private Integer capacidad;

	public AvionDTO( Integer capacidad ) {
		this.capacidad = capacidad;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad( Integer capacidad ) {
		this.capacidad = capacidad;
	}

}
