package co.edu.usbcali.viajesusb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	

}
