package es.um.atica.umufly.paquetes.domain.model;

public record NombreCompleto( String nombre, String primerApellido, String segundoApellido ) {

	public NombreCompleto {
		if ( nombre == null || nombre.isBlank() ) {
			throw new IllegalArgumentException( "Es obligatorio indicar el nombre" );
		}
		if ( primerApellido == null || primerApellido.isBlank() ) {
			throw new IllegalArgumentException( "Es obligatorio indicar el primer apellido" );
		}
        if (segundoApellido == null || segundoApellido.isBlank()) {
            segundoApellido = "";
        }
	}

    public String getApellidos() {
        StringBuilder sb = new StringBuilder(primerApellido);
        if (!segundoApellido.isBlank()) {
            sb.append(" ").append(segundoApellido);
        }
        return sb.toString();
    }
}

