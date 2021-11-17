/**  
 * @Title:  ClienteRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   4/09/2021 12:45:03 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;


/**   
 * @ClassName:  ClienteRepository   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   4/09/2021 12:45:03 p. m.      
 * @Copyright:  USB
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
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
	
	
	
	
	

}
