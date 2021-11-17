/**  
 * @Title:  DestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 12:21:47 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;

/**   
 * @ClassName:  DestinoService   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 12:21:47 p. m.      
 * @Copyright:  USB
 */

public interface DestinoService {
	
	public List<Destino> findByTipoDestino_Codigo(String codigoTipoDestino) throws SQLException;
	
	
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws SQLException;
	
	public Destino guardarDestino(DestinoDTO destinoDTO) throws SQLException;
	
	public Destino actualizarDestino(DestinoDTO destinoDTO) throws SQLException;

	public void eliminarDestino(Long destinoDTO)throws SQLException;

}
