package es.um.atica.umufly.paquetes.adaptors.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.EnvioEntity;
import es.um.atica.umufly.paquetes.adaptors.persistence.jpa.entity.TipoDocumentoEnumEntity;

public interface JpaEnvioPaqueteRepository extends JpaRepository<EnvioEntity, String> {

    Page<EnvioEntity> findByRemitenteTipoDocumentoAndRemitenteNumeroDocumento( TipoDocumentoEnumEntity tipoDocumento, String numeroDocumento, PageRequest pagina );

}
