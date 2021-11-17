package co.edu.usbcali.viajesusb.domain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import co.edu.usbcali.viajesusb.dto.ClienteDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@NamedNativeQueries({
		@NamedNativeQuery(name="Cliente.consultarClientesPorEstadoNoIdentificacionTipoIdentificacion", query="",resultSetMapping = 
		"consultarClientesPorEstadoNoIdentificacionTipoIdentificacion"),
		
		@NamedNativeQuery(name="Cliente.ultimaConsulta", query="", resultSetMapping = "ultimaConsulta")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="consultarClientesPorEstadoNoIdentificacionTipoIdentificacion",
			classes= { @ConstructorResult(targetClass = ClienteDTO.class,
				columns= {
						@ColumnResult(name="nombre", type = String.class)
				})}),
	@SqlResultSetMapping(name="ultimaConsulta",
			classes = { @ConstructorResult(targetClass = ClienteDTO.class,
				columns = {
						@ColumnResult(name="noIdent", type = String.class),
						@ColumnResult(name="nombreCl", type= String.class),
						@ColumnResult(name="estadoCl", type= String.class)
			})})
})


@ToString
@Getter
@Setter
@Entity
@Table(name="cliente")
public class Cliente {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="id_clie")
	public Long idCliente;
	
	@Column(name="numero_identificacion", unique=true, nullable=false, length=15)
	public String numeroIdentificacion;
	
	@Column(name="primer_apellido", nullable=false, length=100)
	public String primerApellido;
	
	@Column(name="segundo_apellido",length=100)
	public String segundoApellido;
	
	@Column(name="nombre", nullable=false, length=100)
	public String nombre;
	
	@Column(name="telefono1",length=15)
	public String telefonoUno;
	
	@Column(name="telefono2",length=15)
	public String telefonoDos;
	
	@Column(name="correo",length=100)
	public String correo;
	
	@Column(name="sexo", nullable=false, length=1)
	public String sexo;
	
	@Column(name="fecha_nacimiento", nullable=false)
	public Date fechaNacimiento;
	
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
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy="cliente")
	public List<Plan> plan = new ArrayList<>();
	
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tiid", nullable=false)
	public TipoIdentificacion tipoIdentificacion;

}
