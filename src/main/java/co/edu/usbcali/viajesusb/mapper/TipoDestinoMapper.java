package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

@Mapper(componentModel="spring")
public interface TipoDestinoMapper {
	
	public TipoDestinoDTO tipoDestinoToTipoDestinoDTO(TipoDestino tipoDestino);
	
	public TipoDestino tipoDestinoDTOToTipoDestino(TipoDestinoDTO tipoDestinoDTO);
	
	public List<TipoDestinoDTO> listTipoDestinoToTipoDestinoDTO(List<TipoDestino> lstTipoDestino);
	
	public List<TipoDestino> listTipoDestinoDTOToTipoDestino(List<TipoDestinoDTO> lstTipoDestinoDTO);
	
}
