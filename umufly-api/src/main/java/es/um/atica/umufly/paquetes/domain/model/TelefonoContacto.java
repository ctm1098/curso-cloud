package es.um.atica.umufly.paquetes.domain.model;

public record TelefonoContacto( String numero ) {

    public TelefonoContacto {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("El número de teléfono no puede ser nulo ni vacío");
        }
        String numeros = numero.replaceAll("\\s+", "");
        if (!numeros.matches("\\d{6,30}")) {
            throw new IllegalArgumentException("El número de teléfono debe contener entre 6 y 30 dígitos");
        }
        numero = numeros;
    }
}
