package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity;

public enum TipoParticipanteEnumEntity {
    DESTINATARIO("destinatario"), REMITENTE("remitente");

    private final String valor;

    TipoParticipanteEnumEntity(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoParticipanteEnumEntity fromValor(String valor) {
        for (TipoParticipanteEnumEntity t : values()) {
            if (t.valor.equalsIgnoreCase(valor)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Valor desconocido para TipoParticipanteEnumEntity: " + valor);
    }
}
