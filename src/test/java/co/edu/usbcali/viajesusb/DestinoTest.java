/**  
 * @Title:  DestinoTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   31/08/2021 11:41:12 a. m.   
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.service.DestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**
 * @ClassName: DestinoTest
 * @Description: TODO
 * @author: Miguel Ortiz
 * @date: 31/08/2021 11:41:12 a. m.
 * @Copyright: USB
 */

@SpringBootTest
@Rollback(false)
class DestinoTest {

	@Autowired
	private DestinoService destinoService;
	
	

	private static final Logger logger = LoggerFactory.getLogger(DestinoTest.class);

	@Test
	@Transactional
	void debeConsultarDestinosPorTipoDestino() {

		List<Destino> lstDestino = null;

		try {
			lstDestino = destinoService.findByTipoDestino_Codigo("PLAYA");

			for (Destino destino : lstDestino) {
				System.out.println(destino.getCodigo() + " - " + destino.getNombre());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	void debeConsultarDestinosPorEstadoPaginado() {

		Page<Destino> pageDestino = null;

		try {

			// Primer numero: Es el numero de pagina actual, empezando desde cero
			// Segundo numero: Es la cantidad de items por pagina
			Pageable pageable = PageRequest.of(0, 2);
			pageDestino = destinoService.findByEstado("A", pageable);

			for (Destino destino : pageDestino.getContent()) {
				System.out.println(destino.getCodigo() + " - " + destino.getNombre());
//				System.out.println(destino.toString());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	void debeGuardarElDestinoSanAndres() {
		try {

			DestinoDTO destinoDTO = new DestinoDTO();


			destinoDTO.setAire(Constantes.YES);
			destinoDTO.setTierra(Constantes.NO);
			destinoDTO.setMar(Constantes.YES);

			destinoDTO.setNombre("SAN ANDRES");
			destinoDTO.setCodigo("BOSQU");
			destinoDTO.setDescripcion("ISLA BELLA");
			destinoDTO.setEstado(Constantes.ACTIVO);
			destinoDTO.setFechaCreacion(new Date());
			destinoDTO.setUsuCreador("MORTIZ");

			destinoDTO.setCodigoTipoDestino("PLAYA");
			destinoDTO.setNombreTipoDestino("PLAYA Y MAR");

			destinoService.guardarDestino(destinoDTO);
			logger.info("Se inserto correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}

	}

	@Test
	@Transactional
	void debeActualizarElDestinoSanAndres() {
		try {

			DestinoDTO destinoDTO = new DestinoDTO();

			destinoDTO.setIdDest(8l);
			destinoDTO.setAire(Constantes.YES);
			destinoDTO.setTierra(Constantes.NO);
			destinoDTO.setMar(Constantes.YES);

			destinoDTO.setNombre("SAN ANDREAS");
			destinoDTO.setCodigo("SAND");
			destinoDTO.setDescripcion("ISLA BELLA");
			destinoDTO.setEstado(Constantes.ACTIVO);
			destinoDTO.setFechaCreacion(new Date());
			destinoDTO.setUsuCreador("MORTIZ");

			destinoDTO.setCodigoTipoDestino("PLAYA");
			destinoDTO.setNombreTipoDestino("PLAYA Y MAR");

			destinoService.actualizarDestino(destinoDTO);
			logger.info("Se actualizo correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}

	}
	
	@Test
	@Transactional
	void debeEliminarElDestinoSanAndres() {
		try {

			DestinoDTO destinoDTO = new DestinoDTO();

			destinoDTO.setIdDest(8l);

			destinoService.eliminarDestino(destinoDTO.getIdDest());
			logger.info("Se actualizo correctamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
		}

	}
	
	

}
