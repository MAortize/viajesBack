package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

@Mapper(componentModel="spring")
public interface TipoIdentificacionMapper {
	
	public TipoIdentificacionDTO tipoIdentificacionToTipoIdentificacionDTO(TipoIdentificacion tipoIdentificacion);
	
	public TipoIdentificacion tipoIdentificacionDTOToTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO);
	
	public List<TipoIdentificacionDTO> listTipoIdentificacionToTipoIdentificacionDTO(List<TipoIdentificacion> lstTipoIdentificacion);
	
	public List<TipoIdentificacion> listTipoIdentificacionDTOToTipoIdentificacion(List<TipoIdentificacionDTO> lstTipoIdentificacionDTO);
	
}
