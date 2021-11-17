package co.edu.usbcali.viajesusb.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.TipoDestino;


/**
 * 
 * @ClassName:  TipoDestinoRespository   
 * @Description: Repository para tipo destino   
 * @author: Miguel Ortiz     
 * @date:   31/08/2021 11:05:29 a. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoRespository extends JpaRepository <TipoDestino, Long> {
	
	
	
	public TipoDestino findByCodigo(String codigo) throws SQLException;
	
	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException;
	
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException;

}	
