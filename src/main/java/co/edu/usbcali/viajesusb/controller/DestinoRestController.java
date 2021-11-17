package co.edu.usbcali.viajesusb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.mapper.DestinoMapper;
import co.edu.usbcali.viajesusb.service.DestinoService;

@RestController
@RequestMapping("/api/destino")
public class DestinoRestController {
	
	 	@Autowired
	    private DestinoService destinoService;

	    @Autowired
	    private DestinoMapper destinoMapper;

	    @PostMapping("/guardarDestino")
	    public ResponseEntity<DestinoDTO> guardarDestino(@RequestBody DestinoDTO destinoDTO) {

	        try {

	            Destino destino = destinoService.guardarDestino(destinoDTO);
	            return ResponseEntity.ok(destinoMapper.destinoToDestinoDTO(destino));


	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @DeleteMapping("/eliminarDestino")
	    public ResponseEntity<?> eliminarDestino(@RequestParam("id") Long id) {

	        try {

	            destinoService.eliminarDestino(id);
	            return ResponseEntity.ok("Se eliminó satisfactoriamente");
	            
	            

	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	        }
	    }
	    
	    
	   @GetMapping("/getDestinoPorCodigoTipoDestino")
	   public ResponseEntity<DestinoDTO> findByTipoDestino_codigo(){
		   
		return null;
		   
	   }

	}
	
	
	

