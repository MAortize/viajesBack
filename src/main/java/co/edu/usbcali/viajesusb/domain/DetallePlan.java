package co.edu.usbcali.viajesusb.domain;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="detalle_plan")
public class DetallePlan {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_depl")
	private Long idDepl;
	
	
	@Column(name="alimentacion", nullable=false, length=1)
	private String alimentacion;
	
	@Column(name="hospedaje", nullable=false, length=1)
	private String hospedaje;
	
	@Column(name="transporte", nullable=false, length=1)
	private String transporte;
	
	@Column(name="traslados", nullable=false, length=1)
	private String traslados;
	
	@Column(name="valor", nullable=false, precision=19, scale=2)
	private Double valor;
	
	@Column(name="cantidad_noches", nullable=false)
	private Integer cantidadNoches;
	
	@Column(name="cantidad_dias", nullable=false)
	private Integer cantidadDias;
	
	@Column(name="fecha_creacion", nullable=false)
	private Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	@Column(name="usu_creador", nullable=false, length=10)
	private String usuCreador;
	
	@Column(name="usu_modificador", length=10)
	private String usuModificador;
	
	@Column(name="estado", nullable=false, length=1)
	private String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_plan", nullable=false)
	private Plan plan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_dest", nullable=false)
	private Destino destino;

	

}
