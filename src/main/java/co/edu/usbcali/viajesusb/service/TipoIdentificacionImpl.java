/**  
 * @Title:  TipoIdentificacionImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   8/09/2021 9:46:35 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  TipoIdentificacionImpl   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   8/09/2021 9:46:35 p. m.      
 * @Copyright:  USB
 */


@Scope("singleton")
@Service
public class TipoIdentificacionImpl implements TipoIdentificacionService {
	
	@Autowired
	private TipoIdentificacionRepository tipoIdentificacionRepository;
	private TipoIdentificacion tipoIdentificacion;

	/**   
	 * <p>Title: findByEstadoOrderByNombreAsc</p>   
	 * <p>Description: </p>   
	 * @param estado
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#findByEstadoOrderByNombreAsc(java.lang.String)   
	 */
	
	@Override
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws SQLException {
		List<TipoIdentificacion> lstTipoIdentificacion = null;
		
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El estado del tipo identificacion es obligatorio insertarlo");
		}
		
		if (Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado del tipo identificacion no puede contener numeros");
		}
		
		if (estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres no pueden superar el total de 1");
		}
		
		lstTipoIdentificacion = tipoIdentificacionRepository.findByEstadoOrderByNombreAsc(estado);
		
		if (lstTipoIdentificacion.isEmpty()) {
			throw new RuntimeException("No se encontraron tipos de identificacion con el parametro ingresado");
		}
		
		return lstTipoIdentificacion;
	}

	/**   
	 * <p>Title: findByCodigoAndEstado</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @param estado
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#findByCodigoAndEstado(java.lang.String, java.lang.String)   
	 */
	
	@Override
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws SQLException {
		
		
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El estado del tipo identificacion es obligatorio insertarlo");
		}
		
		if (Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado del tipo identificacion no puede contener numeros");
		}
		
		if (estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres no pueden superar el total de 1");
		}
		
		if (Utilities.isNull(codigo)) {
			throw new RuntimeException("El codigo del tipo identificacion es obligatorio insertarlo");
		}
		
		if (!Utilities.isOnlyLetters(codigo)) {
			throw new RuntimeException("El codigo del tipo identificacion no puede contener numeros");
		}
		
		if (codigo.length()>5) {
			throw new RuntimeException("La cantidad de caracteres no pueden superar el total de 5");
		}
		
	 
		
		tipoIdentificacion = tipoIdentificacionRepository.findByCodigoAndEstado(codigo, estado);
		
		if (tipoIdentificacion==null) {
			throw new SQLException("No se encuentra ningun registro con los parametros ingresados");
		}
		
		
		
		return tipoIdentificacion;
	}

	/**   
	 * <p>Title: guardarTipoIdentificacion</p>   
	 * <p>Description: </p>   
	 * @param tipoDestinoDTO
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#guardarTipoIdentificacion(co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO)   
	 */
	
	@Override
	public TipoIdentificacion guardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException {
		
		TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
		if (Utilities.isNull(tipoIdentificacionDTO.getCodigo())) {
			throw new SQLException("El codigo de un tipo identificacion no puede ser nulo");
		}else if(!Utilities.isOnlyLetters(tipoIdentificacionDTO.getCodigo())) {
			throw new SQLException("El codigo no puede contener numeros");
		}else if(tipoIdentificacionDTO.getCodigo().length()>5) {
			throw new SQLException("La cantidad de caracteres no puede superar el total de 5");
		}else {
			tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());
		}
		
		if (Utilities.isNull(tipoIdentificacionDTO.getNombre())) {
			throw new SQLException("El nombre del tipo identificacion no puede ser nulo");
		}else if(tipoIdentificacionDTO.getNombre().length()>100) {
			throw new SQLException("La cantidad de caracteres del nombre del tipo identificacion no pueden exceder el total de 100");
		}else {
			tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());
		}
		
		if (tipoIdentificacionDTO.getFechaCreacion()==null) {
			throw new SQLException("La fecha de creacion de un destino no puede ser nula");
		}else {
			tipoIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
		}
		
		if (Utilities.isNull(tipoIdentificacionDTO.getUsuCreador())) {
			throw new SQLException("El nombre del creador de un destino no puede ser nulo");
		}else if(tipoIdentificacionDTO.getUsuCreador().length()>10) {
			throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		}else {
			tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		}
		
		if (Utilities.isNull(tipoIdentificacionDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(tipoIdentificacionDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}else if(tipoIdentificacionDTO.getEstado().length()>1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}else {
			tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
		}
		
		tipoIdentificacionRepository.save(tipoIdentificacion);
		return tipoIdentificacion;
	}

	/**   
	 * <p>Title: actualizarTipoIdentificacion</p>   
	 * <p>Description: </p>   
	 * @param tipoDestinoDTO
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#actualizarTipoIdentificacion(co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO)   
	 */
	
	@Override
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws SQLException {
		
		Optional<TipoIdentificacion> tipoIdentificacion = findById(tipoIdentificacionDTO.getIdTiid());
		
		TipoIdentificacion tipIdentificacion = tipoIdentificacion.get();
		
		if (tipoIdentificacion.isPresent()) {
			if (Utilities.isNull(tipoIdentificacionDTO.getCodigo())) {
				throw new SQLException("El codigo de un tipo identificacion no puede ser nulo");
			}else if(!Utilities.isOnlyLetters(tipoIdentificacionDTO.getCodigo())) {
				throw new SQLException("El codigo no puede contener numeros");
			}else if(tipoIdentificacionDTO.getCodigo().length()>5) {
				throw new SQLException("La cantidad de caracteres no puede superar el total de 5");
			}else {
				tipIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());
			}
			
			if (Utilities.isNull(tipoIdentificacionDTO.getIdTiid())) {
				throw new SQLException("El id del tipo destino no puede ser nulo");
			}else {
				tipIdentificacion.setIdTiid(tipoIdentificacionDTO.getIdTiid());
			}
			
			if (Utilities.isNull(tipoIdentificacionDTO.getNombre())) {
				throw new SQLException("El nombre del tipo identificacion no puede ser nulo");
			}else if(tipoIdentificacionDTO.getNombre().length()>100) {
				throw new SQLException("La cantidad de caracteres del nombre del tipo identificacion no pueden exceder el total de 100");
			}else {
				tipIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());
			}
			
			if (tipoIdentificacionDTO.getFechaCreacion()==null) {
				throw new SQLException("La fecha de creacion de un destino no puede ser nula");
			}else {
				tipIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
			}
			
			if (Utilities.isNull(tipoIdentificacionDTO.getUsuCreador())) {
				throw new SQLException("El nombre del creador de un destino no puede ser nulo");
			}else if(tipoIdentificacionDTO.getUsuCreador().length()>10) {
				throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
			}else {
				tipIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
			}
			
			if (Utilities.isNull(tipoIdentificacionDTO.getEstado())) {
				throw new SQLException("El estado del cliente no puede ser nulo");
			}else if(Utilities.isNumeric(tipoIdentificacionDTO.getEstado())) {
				throw new SQLException("El estado no debe contener numeros");
			}else if(tipoIdentificacionDTO.getEstado().length()>1) {
				throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
			}else {
				tipIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
			}
		}else {
			throw new SQLException("El tipo identificacion con id "+ tipoIdentificacionDTO.getIdTiid()+" No existe");
		}
		
		tipoIdentificacionRepository.save(tipIdentificacion);
		return tipIdentificacion;
	}

	/**   
	 * <p>Title: eliminarTipoIdentificacion</p>   
	 * <p>Description: </p>   
	 * @param tipoDestinoDTO
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#eliminarTipoIdentificacion(co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO)   
	 */
	
	
	public Optional<TipoIdentificacion> findById(Long idTiid) throws SQLException{
		
		if (idTiid==null) {
			throw new SQLException("Debe ingresar un id tipo identificacion");
		}
		
		return tipoIdentificacionRepository.findById(idTiid);
		 
		
	}
	
	
	@Override
	public void eliminarTipoIdentificacion(Long tipoIdentificacionDTO) throws SQLException {
		if (Utilities.isNull(tipoIdentificacionDTO)) {
			throw new SQLException("El id de tipo identificacion es obligatorio");
		}
		//Optional<TipoIdentificacion> tipoIdentificacion = findById(tipoIdentificacionDTO.getIdTiid());
		if (tipoIdentificacionRepository.existsById(tipoIdentificacionDTO)==false) {
			throw new SQLException("El tipo identificacion no se encontro");
			
		}
		
		tipoIdentificacionRepository.findById(tipoIdentificacionDTO).ifPresent(tipoIdentificacion->{
			if (tipoIdentificacion.getCliente().isEmpty()==false) {
				throw new RuntimeException("El tipo identificacion esta asignado a un cliente");
			}
		});
		
		tipoIdentificacionRepository.deleteById(tipoIdentificacionDTO);
	}
	
	

}
