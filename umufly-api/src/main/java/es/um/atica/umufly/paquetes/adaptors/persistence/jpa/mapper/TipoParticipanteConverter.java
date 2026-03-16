package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoParticipanteEnumEntity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class TipoParticipanteConverter 
    implements AttributeConverter<TipoParticipanteEnumEntity, String> {

    @Override
    public String convertToDatabaseColumn(TipoParticipanteEnumEntity attribute) {
        return attribute == null ? null : attribute.getValor();
    }

    @Override
    public TipoParticipanteEnumEntity convertToEntityAttribute(String valor) {
        return valor == null ? null : 
               TipoParticipanteEnumEntity.fromValor(valor);
    }
}
