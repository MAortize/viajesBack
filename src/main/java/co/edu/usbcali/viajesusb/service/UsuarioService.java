package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;

import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.UsuarioDTO;

public interface UsuarioService {
	
	public Usuario findByIdentificacionLike(String identificacion) throws SQLException;
	
	
	public void guardarUsuario(UsuarioDTO usuarioDTO) throws SQLException;
	
	public void actualizarUsuario(UsuarioDTO usuarioDTO) throws SQLException;
	
	public void eliminarUsuario(UsuarioDTO usuarioDTO) throws SQLException;

}
