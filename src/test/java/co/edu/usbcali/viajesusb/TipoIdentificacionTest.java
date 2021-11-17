/**  
 * @Title:  TipoIdentificacionTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   3/09/2021 9:29:53 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;





import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;

/**   
 * @ClassName:  TipoIdentificacionTest   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   3/09/2021 9:29:53 p. m.      
 * @Copyright:  USB
 */

@SpringBootTest
class TipoIdentificacionTest {
	
	
	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	private TipoIdentificacion tipoIdentificacion;
	

	@Test
	@Transactional
	void debeConsultarTiposDeIdentificacionPorEstadoOrdenadoAlfabeticamente() {
		
		List<TipoIdentificacion> lstTipoIdentificacion = null;
		
		try {
			
			lstTipoIdentificacion = tipoIdentificacionService.findByEstadoOrderByNombreAsc("A");
			
			for (TipoIdentificacion tipoIdentificacion : lstTipoIdentificacion) {
				System.out.println(tipoIdentificacion.getNombre());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	void debeConsultarUnTipoIdentificacionPorCodigoEstado(){
		
		
		try {
			tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado("CC","A");	
			System.out.println(tipoIdentificacion.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	void debeGuardarTipoIdentificacion() {
		
		try {
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			tipoIdentificacionDTO.setCodigo(null);
			tipoIdentificacionDTO.setNombre(null);
			tipoIdentificacionDTO.setFechaCreacion(null);
			tipoIdentificacionDTO.setUsuCreador(null);
			tipoIdentificacionDTO.setEstado(null);
			tipoIdentificacionService.guardarTipoIdentificacion(tipoIdentificacionDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeActualizarTipoIdentificacion() {
		
		try {
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			tipoIdentificacionDTO.setIdTiid(null);
			tipoIdentificacionDTO.setCodigo(null);
			tipoIdentificacionDTO.setNombre(null);
			tipoIdentificacionDTO.setFechaCreacion(null);
			tipoIdentificacionDTO.setUsuCreador(null);
			tipoIdentificacionDTO.setEstado(null);
			tipoIdentificacionService.actualizarTipoIdentificacion(tipoIdentificacionDTO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeEliminarTipoIdentificacion() {
		
		try {
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			tipoIdentificacionDTO.setIdTiid(2l);
			tipoIdentificacionService.eliminarTipoIdentificacion(tipoIdentificacionDTO.getIdTiid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
