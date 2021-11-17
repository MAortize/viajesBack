package co.edu.usbcali.viajesusb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;

@Mapper(componentModel = "spring")
public interface DestinoMapper {
	
    @Mapping(source = "tipoDestino.nombre", target = "nombreTipoDestino")
    @Mapping(source = "tipoDestino.codigo", target = "codigoTipoDestino")
    @Mapping(source = "tipoDestino.idTide", target = "idTide")
    public DestinoDTO destinoToDestinoDTO(Destino destino);
	
}
