package es.um.atica.umufly.paquetes.domain.model;

import java.util.regex.Pattern;

public record CorreoElectronico( String valor ) {

	private static final String PATRON_EMAIL_REGEX = "^[a-zA-Z0-9._\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
	private static final Pattern PATTERN = Pattern.compile(PATRON_EMAIL_REGEX);

	public CorreoElectronico {
		if ( valor == null || valor.isBlank() ) {
			throw new IllegalArgumentException( "El correo electrónico no puede ser nulo" );
		}
		if ( !PATTERN.matcher( valor ).matches() ) {
			throw new IllegalArgumentException( "El correo electrónico no tiene un formato válido" );
		}
	}
}

