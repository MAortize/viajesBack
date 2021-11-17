package co.edu.usbcali.viajesusb.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {
	private Long idPlan;
	private String codigo;
	private String nombre;
	private String descripcionSolicitud;
	private Integer cantidadPersonas;
	private Date fechaSolicitud;
	private Date fechaInicioViaje;
	private Date fechaFinViaje;
	private Double valorTotal;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	private Long idCliente;
	private Long idUsua;
	
	private String noIdentificacionCliente;
	private String estadoCliente;
	
	private String identificacionUsuario;
	private String estadoUsuario;
	
}
