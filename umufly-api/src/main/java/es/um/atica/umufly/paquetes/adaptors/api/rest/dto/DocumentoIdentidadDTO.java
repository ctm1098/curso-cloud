package es.um.atica.umufly.paquetes.adaptors.api.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DocumentoIdentidadDTO {

	@NotNull
	private TipoDocumentoDTO tipo;

	@NotBlank
	@Size( max = 15 )
	private String numero;

	public TipoDocumentoDTO getTipo() {
		return tipo;
	}

	public void setTipo( TipoDocumentoDTO tipo ) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

}
