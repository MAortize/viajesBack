/**  
 * @Title:  ClienteTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   4/09/2021 12:47:57 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.service.ClienteService;

import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**
 * @ClassName: ClienteTest
 * @Description: TODO
 * @author: Miguel Ortiz
 * @date: 4/09/2021 12:47:57 p. m.
 * @Copyright: USB
 */

@SpringBootTest
@Rollback(false)
class ClienteTest {

	@Autowired
	private ClienteService clienteService;
	
	
	private Cliente cliente = null;
	private static final Logger logger = LoggerFactory.getLogger(ClienteTest.class);
	
	
	
	
	
	@Test
	@Transactional
	void debeConsultarClientesPorEstadoPaginadoOrdenadoAscendetementePorNumeroIdentifiacion() {
		

		
			Pageable pageable = PageRequest.of(0, 3);
			Page<Cliente> pageCliente;
			try {
				pageCliente = clienteService.findByEstadoOrderByNumeroIdentificacionAsc("A", pageable);
				for (Cliente cliente : pageCliente.getContent()) {
					System.out.println(cliente.getCorreo());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
			
		
		}

	

	@Test
	@Transactional
	void debeConsultarClientePorCorreoElectronicoIgnorandoMayusculasOMinusculas() {

		try {
			cliente = clienteService.findByCorreoIgnoreCase("ortiz.eche@gmail.com");
			System.out.println(cliente.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	void debeConsultarClientePorNumeroIdentifiacionUsandoLike() {

		try {
			cliente = clienteService.findByNumeroIdentificacionLike("1193405162");
			System.out.println("El numero de documento pertenece al cliente llamado " + cliente.getNombre() + " "
					+ cliente.getPrimerApellido() + " " + cliente.getSegundoApellido());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	void debeConsultarClientePorNombreUsandoLikeDebeIgnorarMayusculasOMinusculas() {

		try {
			cliente = clienteService.findByNombreLikeIgnoreCase("MIGUEL");
			System.out.println(cliente.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	void debeConsultarClientePorFechaNacimientoUsandoBetween() {

		List<Cliente> lstCliente = null;
		Calendar fechaInicio = new GregorianCalendar(1999, 10, 20);
		Calendar fechaFin = new GregorianCalendar(2022, 12, 01);

		try {
			lstCliente = clienteService.findByFechaNacimientoBetween(fechaInicio.getTime(), fechaFin.getTime());

			for (Cliente cliente : lstCliente) {
				logger.info(cliente.getNombre() + " " + cliente.getNumeroIdentificacion() + " "
						+ cliente.getFechaNacimiento());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	@Transactional
	void debeContarTotalDeClientesPorEstado() {

		Long clientes;
		try {

			clientes = clienteService.countByEstado("A");
			System.out.println("Hay un total de " + clientes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}

	}

	@Test
	@Transactional
	void debeConsultarClientePorTipoIdentificacion() {

		Page<Cliente> pageCliente = null;

		try {
			Pageable pageable = PageRequest.of(0, 5);
			pageCliente = clienteService.findByTipoIdentificacion_Codigo("CC", pageable);

			for (Cliente cliente : pageCliente.getContent()) {
				System.out.println(cliente.getNombre() + " " + cliente.getNumeroIdentificacion() + " "
						+ cliente.getTipoIdentificacion().getNombre());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sapaa");
		}

	}

	@Test
	@Transactional
	void debeConsultarClientesPorApellidos() {

		List<Cliente> lstCliente = null;

		try {
			lstCliente = clienteService.findByPrimerApellidoOrSegundoApellido("Ortiz", "");
			for (Cliente cliente : lstCliente) {
				System.out.println(cliente.getNombre());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Test
	@Transactional
	void ultimaConsulta() {
		List<ClienteDTO> lstCliente = null;

		try {
			lstCliente = clienteService.ultimaConsulta("Alejandro");
			for (ClienteDTO cliente : lstCliente) {
				System.out.println(
						cliente.getNumeroIdentificacion() + " " + cliente.getNombre() + " " + cliente.getEstado());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	@Transactional
	void debeGuardarCliente() {
		try {
			Calendar fechaNacimiento = new GregorianCalendar(1964, 06, 16);
			ClienteDTO clienteDTO = new ClienteDTO();

			clienteDTO.setNumeroIdentificacion("12345");
			clienteDTO.setPrimerApellido("de pruebaA");
			clienteDTO.setSegundoApellido(null);
			clienteDTO.setNombre("Sujeto");
			clienteDTO.setTelefonoUno("123456");
			clienteDTO.setTelefonoDos(null);
			clienteDTO.setCorreo("siejtoprueba@gmail.com");
			clienteDTO.setSexo(Constantes.MASCULINO);
			clienteDTO.setFechaNacimiento(fechaNacimiento.getTime());
			clienteDTO.setFechaCreacion(new Date());
			clienteDTO.setUsuCreador("MORTIZ");
			clienteDTO.setEstado(Constantes.ACTIVO);
			
			clienteDTO.setCodigoTipoIdentificacion("CC");
			clienteDTO.setEstadoTipoIdentificacion(Constantes.ACTIVO);

			clienteService.guardarCliente(clienteDTO);
			logger.info("Se inserto correctamente");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	@Transactional
	void debeActualizarCliente() {
		try {
			Calendar fechaNacimiento = new GregorianCalendar(1964, 06, 16);
			ClienteDTO clienteDTO = new ClienteDTO();

			clienteDTO.setIdCliente(8l);
			
			clienteDTO.setNumeroIdentificacion("31929913");
			clienteDTO.setPrimerApellido("Echeverriii");
			clienteDTO.setSegundoApellido(null);
			clienteDTO.setNombre("Elizabethh");
			clienteDTO.setTelefonoUno("3104464069");
			clienteDTO.setTelefonoDos(null);
			clienteDTO.setCorreo("thebazile646@gmail.com");
			clienteDTO.setSexo(Constantes.FEMENINO);
			clienteDTO.setFechaNacimiento(fechaNacimiento.getTime());
			clienteDTO.setFechaCreacion(new Date());
			clienteDTO.setUsuCreador("MORTIZ");
			clienteDTO.setEstado(Constantes.ACTIVO);
			
			clienteDTO.setCodigoTipoIdentificacion("CC");
			clienteDTO.setEstadoTipoIdentificacion(Constantes.ACTIVO);

			clienteService.actualizarCliente(clienteDTO);
			logger.info("Se actualizo correctamente");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	@Transactional
	void debeEliminarCliente() {
		try {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdCliente(31l);
			clienteService.eliminarCliente(clienteDTO.getIdCliente());
			logger.info("Se elimino correctamente");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			
		}
	}

}
