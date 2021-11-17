package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

	@Mapping(source ="tipoIdentificacion.codigo", target="codigoTipoIdentificacion")
	@Mapping(source ="tipoIdentificacion.estado", target="estadoTipoIdentificacion")
	@Mapping(source ="tipoIdentificacion.idTiid", target="idTiid")
    
	public ClienteDTO clienteToClienteDTO(Cliente cliente);
	
	
    public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes);
    public List<ClienteDTO> listClienteDTOToListCliente(List<ClienteDTO> clientes);

	
}
