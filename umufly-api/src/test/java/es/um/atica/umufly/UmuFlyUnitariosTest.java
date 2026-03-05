package es.um.atica.umufly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import es.um.atica.umufly.vuelos.domain.exception.VueloIniciadoException;
import es.um.atica.umufly.vuelos.domain.model.Avion;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.CorreoElectronico;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.EstadoReserva;
import es.um.atica.umufly.vuelos.domain.model.EstadoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Itinerario;
import es.um.atica.umufly.vuelos.domain.model.Nacionalidad;
import es.um.atica.umufly.vuelos.domain.model.NombreCompleto;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.TipoDocumento;
import es.um.atica.umufly.vuelos.domain.model.TipoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@TestClassOrder( ClassOrderer.OrderAnnotation.class )
public class UmuFlyUnitariosTest {

	private DocumentoIdentidad titular;
	private Pasajero pasajero;
	private LocalDateTime SALIDA = LocalDateTime.now().plusDays( 5 );
	private LocalDateTime LLEGADA = LocalDateTime.now().plusDays( 6 );
	private Vuelo vueloPendiente;
	private Vuelo vueloCancelado;
	private Vuelo vueloCompletado;

	@BeforeEach
	void setUp() {
		titular = new DocumentoIdentidad( TipoDocumento.NIF, "12345678Z" );
		pasajero = Pasajero.of( titular, new NombreCompleto( "Juan", "García", "López" ), new CorreoElectronico( "juan@ejemplo.com" ), new Nacionalidad( "Española" ) );
		Itinerario itinerario = new Itinerario( SALIDA, LLEGADA, "MAD", "BCN" );
		Avion avion = new Avion( 180 );
		vueloPendiente = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.PENDIENTE, avion );
		vueloCancelado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.CANCELADO, avion );
		vueloCompletado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.COMPLETADO, avion );
	}

	@Nested
	@DisplayName( "Cuando cancelar una reserva..." )
	@Order( 1 )
	class CuandoCancelar {

		private LocalDateTime ANTES_DE_SALIDA = SALIDA.minusDays( 1 );
		private LocalDateTime DESPUES_DE_SALIDA = SALIDA.plusDays( 1 );

		@Test
		void cancelar_deberia_cancelar_reserva_si_vuelo_no_iniciado() {
			ReservaVuelo reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_DE_SALIDA, 0, 10 );
			reserva.cancelarReserva( ANTES_DE_SALIDA );
			assertEquals( EstadoReserva.CANCELADA, reserva.getEstado() );
		}

		@Test
		void cancelar_deberia_lanzar_excepcion_si_vuelo_iniciado() {
			ReservaVuelo reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_DE_SALIDA, 0, 10 );
			assertThrows( VueloIniciadoException.class, () -> reserva.cancelarReserva( DESPUES_DE_SALIDA ) );
		}

	}

	@Nested
	@DisplayName( "Cuando formalizar reserva..." )
	@Order( 2 )
	class CuandoFormalizar {

		private ReservaVuelo reserva;

		@BeforeEach
		void setUp() {
			reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.BUSINESS, LocalDateTime.now(), 0, 1 );
		}

		@Test
		void formalizar_deberia_pasar_a_activa() {
			reserva.formalizarReserva();
			assertEquals( EstadoReserva.ACTIVA, reserva.getEstado() );
		}

	}
}
