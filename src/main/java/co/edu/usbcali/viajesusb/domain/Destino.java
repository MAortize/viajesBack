package co.edu.usbcali.viajesusb.domain;

//mappedBy reference an unknown target entity property: 
//co.edu.usbcali.viajesusb.domain.Plan.Cliente in co.edu.usbcali.viajesusb.domain.Cliente.plan

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="destino")
public class Destino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_dest")
	public Long idDest;
	
	@Column(name="codigo", unique=true, length=5)
	public String codigo;
	
	@Column(name="nombre", nullable=false, length=100)
	public String nombre;
	
	@Column(name="descripcion", nullable=false, length=300)
	public String descripcion;
	
	@Column(name="tierra", nullable=false, length=1)
	public String tierra;
	
	@Column(name="aire", nullable=false, length=1)
	public String aire;
	
	@Column(name="mar", nullable=false, length=1)
	public String mar;
	
	@Column(name="fecha_creacion", nullable=false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador", nullable=false, length=10)
	public String usuCreador;
	
	@Column(name="usu_modificador", length=10)
	public String usuModificador;
	
	@Column(name="estado", nullable=false, length=1)
	public String estado;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="destino")
	public List<DetallePlan> detallePlan = new ArrayList<>();
	
	
//	Se coloca esta anotacion debido a que se crea un ciclo infinito, en el metodo toString
//	por ende se excluye el to string para evitar el overflow generado
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tide", nullable=false)
	public TipoDestino tipoDestino;
	
	
	
	
}