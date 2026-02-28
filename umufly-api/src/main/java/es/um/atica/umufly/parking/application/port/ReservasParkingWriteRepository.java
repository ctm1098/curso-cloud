package es.um.atica.umufly.parking.application.port;

import java.util.UUID;

import es.um.atica.umufly.parking.domain.model.ReservaParking;

public interface ReservasParkingWriteRepository {

	void persistirParking( ReservaParking reservaParking );

	void persistirFormalizacionParking( UUID idParking, UUID idParkingFormalizada );

	void cancelParking( UUID idParking );

}
