package co.edu.usbcali.viajesusb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.mapper.TipoDestinoMapper;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;

@RestController
@RequestMapping("/api/tipoDestino")
public class TipoDestinoRestController {
	
	@Autowired
	private TipoDestinoService tipoDestinoService;
	
	@Autowired 
	private TipoDestinoMapper tipoDestinoMapper;
	
	
	@GetMapping("/getTipoDestinoPorCodigo")
	public ResponseEntity<TipoDestinoDTO> buscarTipoDestinoPorCodigo(@RequestParam("codigo") String codigo){
		try {
			TipoDestino tipoDestino = tipoDestinoService.findByCodigo(codigo);
//			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO(tipoDestino.getIdTide(), tipoDestino.getCodigo(), tipoDestino.getNombre()
//					, tipoDestino.getDescripcion(), tipoDestino.getFechaCreacion(), tipoDestino.getFechaModificacion(), tipoDestino.getUsuCreador(), tipoDestino.getUsuModificador(), tipoDestino.getEstado());
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	@GetMapping("/getTiposDestino")
	public ResponseEntity<List<TipoDestinoDTO>> consultarTipoDestino(@RequestParam("estado") String estado){
		try {
			List<TipoDestino> lstTipoDestino = tipoDestinoService.findByEstadoOrderByNombreDesc(estado);
//			List<TipoDestinoDTO> lstTipoDestinoDTO = new ArrayList<>();
			
//			for (TipoDestino tipoDestino : lstTipoDestino) {
//				TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO(tipoDestino.getIdTide(), tipoDestino.getCodigo(), tipoDestino.getNombre()
//						, tipoDestino.getDescripcion(), tipoDestino.getFechaCreacion(), tipoDestino.getFechaModificacion(), tipoDestino.getUsuCreador(), tipoDestino.getUsuModificador(), tipoDestino.getEstado());
//				
//				lstTipoDestinoDTO.add(tipoDestinoDTO);
//			}
			return ResponseEntity.ok().body(tipoDestinoMapper.listTipoDestinoToTipoDestinoDTO(lstTipoDestino));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		
		
		
	}
	
	
}
