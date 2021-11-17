package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;




public interface ClienteService {
	
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable) throws SQLException;
	
	public Cliente findByCorreoIgnoreCase(String correo) throws SQLException;
	
	public Cliente findByNumeroIdentificacionLike(String noIdentificacion) throws SQLException;
	
	public Cliente findByNombreLikeIgnoreCase(String nombre) throws SQLException;
	
	public List<Cliente> findByFechaNacimientoBetween(Date fecha1, Date fecha2) throws SQLException;
	
	public Long countByEstado(String estado) throws SQLException;
	
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion,  Pageable pageable) throws SQLException;
	
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido) throws SQLException;

//	@Query(nativeQuery = true)
//	public List<ClienteDTO> consultarClientesPorEstadoNoIdentificacionTipoIdentificacion(@Param("pEstado") String estado, @Param("pNumeroIdentificacion") 
//																							String noIdentificacion, 
//																							@Param("pNombre")String nombre) throws Exception;

	@Query(nativeQuery = true)
	public List<ClienteDTO> ultimaConsulta(@Param("pNombre") String nombre) throws SQLException;
	
	public Cliente guardarCliente(ClienteDTO clienteDTO) throws SQLException;
	
	public Cliente actualizarCliente(ClienteDTO clienteDTO) throws SQLException;
	
	public void eliminarCliente(Long clienteDTO) throws SQLException;

}
