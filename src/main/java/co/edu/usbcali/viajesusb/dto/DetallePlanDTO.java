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
public class DetallePlanDTO {
	private Long idDepl;
	private String alimentacion;
	private String hospedaje;
	private String transporte;
	private String traslados;
	private Double valor;
	private Integer cantidadNoches;
	private Integer cantidadDias;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	
	private Long idDest;
	private Long idPlan;
	
	
	private String codigoPlan;
	private String estadoPlan;
	
	private String codigoDestino;
	private String estadoDestino;
}
