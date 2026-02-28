package es.um.atica.umufly.parking.adaptors.persistence.jpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table( name = "RESERVA_PARKING", schema = "FORMACION_TICARUM" )
public class ReservaParkingEntity {

	@Id
	@NotNull
	@Column( name = "ID", nullable = false, length = 36 )
	private String id;

	@NotNull
	@Column( name = "TIPO_DOCUMENTO_CLIENTE", length = 2, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private TipoDocumentoEnum tipoDocumentoCliente;

	@NotNull
	@Column( name = "NUMERO_DOCUMENTO_CLIENTE", length = 15, nullable = false )
	private String numeroDocumentoCliente;

	@NotNull
	@Column( name = "FECHA_RESERVA", nullable = false )
	private LocalDateTime fechaReserva;

	@NotNull
	@Column( name = "FECHA_MODIFICACION", nullable = false )
	private LocalDateTime fechaModificacion;

	@NotNull
	@Column( name = "FECHA_INICIO", nullable = false )
	private LocalDateTime fechaInicio;

	@NotNull
	@Column( name = "FECHA_FIN", nullable = false )
	private LocalDateTime fechaFin;
	
	@NotNull
	@Column( name = "ESTADO_RESERVA", length = 2, nullable = false )
	@Enumerated( value = EnumType.STRING )
	private EstadoParkingEnum estadoReserva;
	
	@NotNull
	@Column( name = "TIPO", nullable = false, length = 2 )
	@Enumerated( value = EnumType.STRING )
	private TipoEstacionamientoEnum tipo;

	@Column( name = "ID_RESERVA_FORMALIZADA", length = 36, nullable = true )
	private String idParkingFormalizada;
	
	@NotNull
	@Column( name = "IMPORTE", nullable = false )
	private double importe;

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public TipoDocumentoEnum getTipoDocumentoCliente() {
		return tipoDocumentoCliente;
	}

	public void setTipoDocumentoCliente( TipoDocumentoEnum tipoDocumentoCliente ) {
		this.tipoDocumentoCliente = tipoDocumentoCliente;
	}

	public String getNumeroDocumentoCliente() {
		return numeroDocumentoCliente;
	}

	public void setNumeroDocumentoCliente( String numeroDocumentoCliente ) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva( LocalDateTime fechaReserva ) {
		this.fechaReserva = fechaReserva;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion( LocalDateTime fechaModificacion ) {
		this.fechaModificacion = fechaModificacion;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio( LocalDateTime fechaInicio ) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin( LocalDateTime fechaFin ) {
		this.fechaFin = fechaFin;
	}
	
	public EstadoParkingEnum getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva( EstadoParkingEnum estadoReserva ) {
		this.estadoReserva = estadoReserva;
	}

	public TipoEstacionamientoEnum getTipo() {
		return tipo;
	}

	public void setTipo( TipoEstacionamientoEnum tipo ) {
		this.tipo = tipo;
	}
	
	public String getIdParkingFormalizada() {
		return idParkingFormalizada;
	}

	public void setIdParkingFormalizada( String idParkingFormalizada ) {
		this.idParkingFormalizada = idParkingFormalizada;
	}
	
	public double getImporte() {
		return importe;
	}

	public void setImporte( double importe ) {
		this.importe = importe;
	}

}
