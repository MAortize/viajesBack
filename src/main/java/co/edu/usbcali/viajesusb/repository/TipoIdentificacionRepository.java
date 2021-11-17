/**  
 * @Title:  TipoIdentificacionRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   3/09/2021 9:21:42 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;

/**   
 * @ClassName:  TipoIdentificacionRepository   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   3/09/2021 9:21:42 p. m.      
 * @Copyright:  USB
 */

public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long> {
	
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws SQLException;
	 
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws SQLException;

}
