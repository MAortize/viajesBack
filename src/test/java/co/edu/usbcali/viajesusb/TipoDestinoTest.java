/**  
 * @Title:  TipoDestinoTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   31/08/2021 11:19:53 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;



import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  TipoDestinoTest   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   31/08/2021 11:19:53 a. m.      
 * @Copyright:  USB
 */


@SpringBootTest
@Rollback(false)
class TipoDestinoTest {
	
	@Autowired
	private TipoDestinoService tipoDestinoService;
	
	
	private TipoDestino tipoDestino = null;
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteTest.class);
	
	
	@Test
	@Transactional
	void debeConsultarUnTipoDeDestinoPorCodigo() {
		
		
		
		
		try {
			tipoDestino = tipoDestinoService.findByCodigo("PLAYA");			
			logger.info(tipoDestino.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	void debeConsultarUnTipoDeDestinoPorCodigoEstado() {
		
		
		
		
		try {
			tipoDestino = tipoDestinoService.findByCodigoAndEstado("PLAYA", "A");	
			System.out.println(tipoDestino.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	
	@Test
	@Transactional
	void debeConsultarUnTipoDeDestinoPorEstadoOrdenadoAlfabeticamente() {
		
		
		List<TipoDestino> lstTipoDestino = null;
		
		try {
			lstTipoDestino = tipoDestinoService.findByEstadoOrderByNombreDesc("A");
			
			for (TipoDestino tipoDestino : lstTipoDestino) {
				System.out.println(tipoDestino.getNombre());
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	void debeGuardarElTipoDestino() {
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			tipoDestinoDTO.setCodigo("XD");
			tipoDestinoDTO.setNombre("hola");
			tipoDestinoDTO.setDescripcion("kiko");
			tipoDestinoDTO.setFechaCreacion(new Date());
			tipoDestinoDTO.setUsuCreador("moritz");
			tipoDestinoDTO.setEstado(Constantes.ACTIVO);
			tipoDestinoService.guardarTipoDestino(tipoDestinoDTO);
			logger.info("Se inserto correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	void debeActualizarElTipoDestino() {
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			
			tipoDestinoDTO.setIdTide(null);
			tipoDestinoDTO.setCodigo(null);
			tipoDestinoDTO.setNombre(null);
			tipoDestinoDTO.setDescripcion(null);
			tipoDestinoDTO.setFechaCreacion(null);
			tipoDestinoDTO.setUsuCreador(null);
			tipoDestinoDTO.setEstado(null);
			tipoDestinoService.actualizarTipoDestino(tipoDestinoDTO);
			logger.info("se actualizo correctamente");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}
		
	}
	
	@Test
	@Transactional
	void debeEliminarElTipoDestino() {
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			tipoDestinoDTO.setIdTide(3L);
			tipoDestinoService.eliminarTipoDestino(tipoDestinoDTO);
			logger.info("Se elimino correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getStackTrace();
			logger.info(e.getMessage());
		}

	}

}

