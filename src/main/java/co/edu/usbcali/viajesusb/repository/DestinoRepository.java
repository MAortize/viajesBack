package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.Destino;


/**
 * 
 * @ClassName:  DestinoRepository   
 * @Description: Repository para destino   
 * @author: Miguel Ortiz     
 * @date:   31/08/2021 11:04:46 a. m.      
 * @Copyright:  USB
 */

public interface DestinoRepository extends JpaRepository <Destino, Long> {
	
	public List<Destino> findByTipoDestino_Codigo(String codigoTipoDestino) throws SQLException;
	
	
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws SQLException;
	
	
	

}
