/**  
 * @Title:  TipoIdentificacionService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   8/09/2021 9:47:05 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

/**   
 * @ClassName:  TipoIdentificacionService   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   8/09/2021 9:47:05 p. m.      
 * @Copyright:  USB
 */

public interface TipoIdentificacionService {
	
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws SQLException;
	 
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws SQLException;
	
	public TipoIdentificacion guardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException;
	
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException;
	
	public void eliminarTipoIdentificacion(Long tipoIdentificacionDTO) throws SQLException;

}
