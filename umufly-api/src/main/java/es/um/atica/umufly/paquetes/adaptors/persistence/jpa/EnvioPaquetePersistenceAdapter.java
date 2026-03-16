package es.um.atica.umufly.paquetes.adaptors.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.paquetes.application.port.EnvioPaqueteReadRepository;
import es.um.atica.umufly.paquetes.application.port.EnvioPaqueteWriteRepository;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoDocumentoEnumEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.mapper.JpaPersistenceMapper;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository.JpaEnvioPaqueteRepository;
import es.um.atica.umufly.paquetes.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.paquetes.domain.model.EnvioPaquete;

@Component
public class EnvioPaquetePersistenceAdapter implements EnvioPaqueteReadRepository, EnvioPaqueteWriteRepository {

    private final JpaEnvioPaqueteRepository jpaEnvioPaqueteRepository;

    public EnvioPaquetePersistenceAdapter(JpaEnvioPaqueteRepository jpaEnvioPaqueteRepository) {
        this.jpaEnvioPaqueteRepository = jpaEnvioPaqueteRepository;
    }

    @Override
    public Page<EnvioPaquete> findEnvios(DocumentoIdentidad documentoIdentidad, int page, int size) {
        TipoDocumentoEnumEntity tipoDocumentoEntity = JpaPersistenceMapper.tipoDocumentoToEntity(documentoIdentidad.tipo());
        return this.jpaEnvioPaqueteRepository
            .findByRemitenteTipoDocumentoAndRemitenteNumeroDocumento(tipoDocumentoEntity, documentoIdentidad.identificador(), PageRequest.of(page, size))
            .map(JpaPersistenceMapper::envioPaqueteToModel);
    }

    @Override
    public void persistirEnvio(EnvioPaquete envio) {
        EnvioEntity entity = JpaPersistenceMapper.envioToEntity(envio);
        this.jpaEnvioPaqueteRepository.save(entity);
    }

}
