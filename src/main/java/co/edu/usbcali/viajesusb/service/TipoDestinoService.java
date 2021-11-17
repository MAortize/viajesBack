/**  
 * @Title:  TipoDestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 11:50:45 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

/**   
 * @ClassName:  TipoDestinoService   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 11:50:45 a. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoService {
	
	public TipoDestino findByCodigo(String codigo) throws SQLException;
	
	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException;
	
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException;
	
	public void guardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException;
	
	public void actualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException;
	
	public void eliminarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException;
	
	
}
