package es.um.atica.umufly.paquetes.application.usecase.listarenvios;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

public class ListarEnviosQuery extends Query<Page<EnvioPaquete>> {

    private DocumentoIdentidad documentoIdentidad;
    private Integer page;
    private Integer size;

    private ListarEnviosQuery(DocumentoIdentidad documentoIdentidad, Integer page, Integer size) {
        this.documentoIdentidad = documentoIdentidad;
        this.page = page;
        this.size = size;
    }

    public static ListarEnviosQuery of(DocumentoIdentidad documentoIdentidad, Integer page, Integer size) {
        if (documentoIdentidad == null) {
            throw new IllegalArgumentException("Documento identidad nulo");
        }
        if (page == null || page < 0) {
            throw new IllegalArgumentException("Número de página nulo o negativo");
        }
        if (size == null || size < 0) {
            throw new IllegalArgumentException("Longitud de página nula o negativa");
        }

        return new ListarEnviosQuery(documentoIdentidad, page, size);
    }

    public DocumentoIdentidad getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    

    

}
