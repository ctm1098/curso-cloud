package es.um.atica.umufly.paquetes.application.usecase.listarenvios;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.paquetes.application.port.EnvioPaqueteReadRepository;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

@Component
public class ListarEnviosQueryHandler implements QueryHandler<Page<EnvioPaquete>, ListarEnviosQuery>{

    private final EnvioPaqueteReadRepository envioPaqueteReadRepository;

    public ListarEnviosQueryHandler(EnvioPaqueteReadRepository envioPaqueteReadRepository) {
        this.envioPaqueteReadRepository = envioPaqueteReadRepository;
    }

    @Override
    public Page<EnvioPaquete> handle(ListarEnviosQuery query) throws Exception {
        return this.envioPaqueteReadRepository.findEnvios(query.getDocumentoIdentidad(), query.getPage(), query.getSize());
    }

}
