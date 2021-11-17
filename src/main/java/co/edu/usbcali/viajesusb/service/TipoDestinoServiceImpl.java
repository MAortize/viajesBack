/**  
 * @Title:  TipoDestinoImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 11:53:27 a. m.   
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

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRespository;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**
 * @ClassName: TipoDestinoImpl
 * @Description: TODO
 * @author: Miguel Ortiz
 * @date: 7/09/2021 11:53:27 a. m.
 * @Copyright: USB
 */

@Scope("singleton")
@Service
public class TipoDestinoServiceImpl implements TipoDestinoService {

	@Autowired
	private TipoDestinoRespository tipoDestinoRepository;
	
	

	/**
	 * <p>
	 * Title: findByCodigo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigo
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigo(java.lang.String)
	 */

	@Override
	public TipoDestino findByCodigo(String codigo) throws SQLException {

		TipoDestino tipoDestino = null;
		if (Utilities.isNull(codigo)) {
			throw new RuntimeException("El codigo de tipo destino es obligatorio");
		}
		
		if (!Utilities.isOnlyLetters(codigo)) {
			throw new RuntimeException("El codigo de tipo destino no puede contener numeros");
		}
		
		if (codigo.length()>5) {
			throw new RuntimeException("La cantidad de caracteres no puede superar el total de 5");
		}
	

		tipoDestino = tipoDestinoRepository.findByCodigo(codigo);
		
		if (tipoDestino==null) {
			throw new SQLException("El codigo ingresado no corresponde a ningun tipo destino en la base de datos");
		}

		return tipoDestino;
	}

	/**
	 * <p>
	 * Title: findByCodigoAndEstado
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigo
	 * @param estado
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigoAndEstado(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException {
		
		TipoDestino tipoDestino = null;
		if (Utilities.isNull(codigo)) {
			throw new RuntimeException("El codigo de tipo destino es obligatorio");
		}
		
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El estado de tipo destino es obligatorio");
		}
		
		if (!Utilities.isOnlyLetters(codigo)) {
			throw new RuntimeException("El codigo de tipo destino no puede contener numeros");
		}
		
		if (Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado no puede contener numeros");
		}
		
		if (codigo.length()>5) {
			throw new RuntimeException("La cantidad de caracteres no puede superar el total de 5");
		}
		
		if (estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres no puede superar el total de 1");
		}
		
		

		tipoDestino = tipoDestinoRepository.findByCodigoAndEstado(codigo, estado);
		
		if (tipoDestino==null) {
			throw new RuntimeException("No se encuentran registros con los parametros ingresados");
		}

		return tipoDestino;
	}

	/**
	 * <p>
	 * Title: findByEstadoOrderByNombreDesc
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByEstadoOrderByNombreDesc(java.lang.String)
	 */

	@Override
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException {
		List<TipoDestino> lstTipoDestino = null;
		
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El estado no puede ser nulo");
		}
		
		if (Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado no puede contener numeros");
		}
		
		if (estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres no puede superar el total de 1");
		}
		

		lstTipoDestino = tipoDestinoRepository.findByEstadoOrderByNombreDesc(estado);
		
		if (lstTipoDestino.isEmpty()) {
			throw new RuntimeException("No se encuentra registros con el parametro ingresado");
		}


		return lstTipoDestino;

	}

	/**   
	 * <p>Title: guardarTipoDestino</p>   
	 * <p>Description: </p>   
	 * @param tipoDestinoDTO
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#guardarTipoDestino(co.edu.usbcali.viajesusb.dto.TipoDestinoDTO)   
	 */
	
	@Override
	public void guardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException {
		TipoDestino tipoDestino = new TipoDestino();
		
		if (Utilities.isNull(tipoDestinoDTO.getCodigo())) {
			throw new SQLException("El codigo de un tipo destino no puede ser nulo");
		}else if(!Utilities.isOnlyLetters(tipoDestinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede contener numeros");
		}else if(tipoDestinoDTO.getCodigo().length()>5) {
			throw new SQLException("La cantidad de caracteres no puede superar el total de 5");
		}else {
			tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
		}
		
		if (Utilities.isNull(tipoDestinoDTO.getNombre())) {
			throw new SQLException("El nombre del tipo destino no puede ser nulo");
		}else if(tipoDestinoDTO.getNombre().length()>100) {
			throw new SQLException("La cantidad de caracteres del nombre del tipo destino no pueden exceder el total de 100");
		}else {
			tipoDestino.setNombre(tipoDestinoDTO.getNombre());
		}
		
		
		if (Utilities.isNull(tipoDestinoDTO.getDescripcion())) {
			throw new SQLException("La descripcion del tipo destino no puede ser nulo");
		}else if(tipoDestinoDTO.getDescripcion().length()>300) {
			throw new SQLException("La cantidad de caracteres de la descripcion del tipo destino no pueden exceder el total de 300");
		}else {
			tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());
		}
		
		if (tipoDestinoDTO.getFechaCreacion()==null) {
			throw new SQLException("La fecha de creacion de un destino no puede ser nula");
		}else {
			tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
		}
		
		if (Utilities.isNull(tipoDestinoDTO.getUsuCreador())) {
			throw new SQLException("El nombre del creador de un destino no puede ser nulo");
		}else if(tipoDestinoDTO.getUsuCreador().length()>10) {
			throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		}else {
			tipoDestino.setUsuCreador(tipoDestinoDTO.getUsuCreador());
		}
		
		if (Utilities.isNull(tipoDestinoDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(tipoDestinoDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}else if(tipoDestinoDTO.getEstado().length()>1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}else {
			tipoDestino.setEstado(tipoDestinoDTO.getEstado());
		}
		
		tipoDestinoRepository.save(tipoDestino);
		
		
		
	}

	/**   
	 * <p>Title: actualizarTipoDestino</p>   
	 * <p>Description: </p>   
	 * @param tipoDestinoDTO
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#actualizarTipoDestino(co.edu.usbcali.viajesusb.dto.TipoDestinoDTO)   
	 */
	
	@Override
	public void actualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException {
		Optional<TipoDestino> tipoDestino = findById(tipoDestinoDTO.getIdTide());
		
		TipoDestino tipDestino = tipoDestino.get();
		
		if (tipoDestino.isPresent()) {
			if (Utilities.isNull(tipoDestinoDTO.getCodigo())) {
				throw new SQLException("El codigo de un tipo destino no puede ser nulo");
			}else if(!Utilities.isOnlyLetters(tipoDestinoDTO.getCodigo())) {
				throw new SQLException("El codigo no puede contener numeros");
			}else if(tipoDestinoDTO.getCodigo().length()>5) {
				throw new SQLException("La cantidad de caracteres no puede superar el total de 5");
			}else {
				tipDestino.setCodigo(tipoDestinoDTO.getCodigo());
			}
			
			if (Utilities.isNull(tipoDestinoDTO.getIdTide())) {
				throw new SQLException("El id del tipo destino no puede ser nulo");
			}else {
				tipDestino.setIdTide(tipoDestinoDTO.getIdTide());
			}
			
			if (Utilities.isNull(tipoDestinoDTO.getNombre())) {
				throw new SQLException("El nombre del tipo destino no puede ser nulo");
			}else if(tipoDestinoDTO.getNombre().length()>100) {
				throw new SQLException("La cantidad de caracteres del nombre del tipo destino no pueden exceder el total de 100");
			}else {
				tipDestino.setNombre(tipoDestinoDTO.getNombre());
			}
			
			
			if (Utilities.isNull(tipoDestinoDTO.getDescripcion())) {
				throw new SQLException("La descripcion del tipo destino no puede ser nulo");
			}else if(tipoDestinoDTO.getDescripcion().length()>300) {
				throw new SQLException("La cantidad de caracteres de la descripcion del tipo destino no pueden exceder el total de 300");
			}else {
				tipDestino.setDescripcion(tipoDestinoDTO.getDescripcion());
			}
			
			if (tipoDestinoDTO.getFechaCreacion()==null) {
				throw new SQLException("La fecha de creacion de un destino no puede ser nula");
			}else {
				tipDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
			}
			
			if (Utilities.isNull(tipoDestinoDTO.getUsuCreador())) {
				throw new SQLException("El nombre del creador de un destino no puede ser nulo");
			}else if(tipoDestinoDTO.getUsuCreador().length()>10) {
				throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
			}else {
				tipDestino.setUsuCreador(tipoDestinoDTO.getUsuCreador());
			}
			
			if (Utilities.isNull(tipoDestinoDTO.getEstado())) {
				throw new SQLException("El estado del cliente no puede ser nulo");
			}else if(Utilities.isNumeric(tipoDestinoDTO.getEstado())) {
				throw new SQLException("El estado no debe contener numeros");
			}else if(tipoDestinoDTO.getEstado().length()>1) {
				throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
			}else {
				tipDestino.setEstado(tipoDestinoDTO.getEstado());
			}
			
		}else {
			throw new SQLException("El tipo destino con id "+ tipoDestinoDTO.getIdTide()+" No existe");
		}
		
		
		tipoDestinoRepository.save(tipDestino);
		
		
		
	}

	
	
	
	public Optional<TipoDestino> findById(Long idTide) throws SQLException{
		
		if (idTide==null) {
			throw new SQLException("Debe ingresar un id tipo destino");
		}
		
		return tipoDestinoRepository.findById(idTide);
		 
		
	}
	
//	public Optional<Destino> findByIdDestino(Long idDest) throws SQLException{
//		
//		if (idDest==null) {
//			throw new SQLException("Debe ingresar un id destino");
//		}
//		
//		return destinoRepository.findById(idDest);
//		 
//		
//	}
	
	
	
	
	
	
	
	
	public void eliminarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws SQLException {
		
		
		if (Utilities.isNull(tipoDestinoDTO.getIdTide())) {
			throw new RuntimeException("El id de tipo destino es obligatorio");
		}
		//Optional<TipoDestino> tipoDestino = findById(tipoDestinoDTO.getIdTide());
		
		if (tipoDestinoRepository.existsById(tipoDestinoDTO.getIdTide())==false) {
			throw new RuntimeException("El tipo destino no se encontro");
			
		}
		
		tipoDestinoRepository.findById(tipoDestinoDTO.getIdTide()).ifPresent(destino->{
			if (destino.getDestino().isEmpty()==false) {
				throw new RuntimeException("El tipo destino esta asignado a un destino");
			}
		});
			
		tipoDestinoRepository.deleteById(tipoDestinoDTO.getIdTide());
		
		}


	
		
		
		

}
