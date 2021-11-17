package co.edu.usbcali.viajesusb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.mapper.ClienteMapper;
import co.edu.usbcali.viajesusb.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteMapper clienteMapper;
	
	@PostMapping("/guardarCliente")
	public ResponseEntity<ClienteDTO> guardarCliente(@RequestBody ClienteDTO clienteDTO){
		try {
			Cliente cliente = clienteService.guardarCliente(clienteDTO);
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PutMapping("/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(
			@RequestBody ClienteDTO clienteDTO
			){
		try {
			
			Cliente cliente = clienteService.actualizarCliente(clienteDTO);
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/eliminarCliente")
	public ResponseEntity<?> eliminarCliente(@RequestParam("id") Long id){
		try {
			clienteService.eliminarCliente(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@GetMapping("/findByCorreo")
	public ResponseEntity<ClienteDTO> findClienteByCorreo(@RequestParam("correo") String correo ){
		try {
			Cliente cliente=  clienteService.findByCorreoIgnoreCase(correo);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@GetMapping("/findByNumeroIdentificacionLike")
	public ResponseEntity<ClienteDTO> findClienteByNumeroIdentificacionLike(@RequestParam("numeroIdentificacion") String numeroIdentificacion ){
		try {
			Cliente cliente=  clienteService.findByNumeroIdentificacionLike(numeroIdentificacion);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	
	
	@GetMapping("/findByNombreLike")
	public ResponseEntity<ClienteDTO> buscarClientePorNombreLike(@RequestParam("nombre") String nombre ){
		try {
			Cliente cliente=  clienteService.findByNombreLikeIgnoreCase(nombre);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}

	@GetMapping("/findByFechasNacimiento")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorFechaN(@RequestParam("fechaInicio") 
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicial, 
	@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFinal ){
	

		try {
			List<Cliente> listacliente=  clienteService.findByFechaNacimientoBetween(fechaInicial, fechaFinal);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			//retorna un error 500
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}

	@GetMapping("/countEstado")
	public ResponseEntity<?> clientesPorEstado(@RequestParam("estado") String estado ){
		try {
			Long cliente=  clienteService.countByEstado(estado);
			return ResponseEntity.ok().body(cliente);
		} catch (Exception e) {
			//retorna un error 500
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@GetMapping("/findByApellidos")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorApellidos(@RequestParam("primerApellido") String primerApellido, @RequestParam("segundoApellido") String segundoApellido ){
		try {
			List<Cliente> listacliente=  clienteService.findByPrimerApellidoOrSegundoApellido(primerApellido, segundoApellido);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	

	
}
