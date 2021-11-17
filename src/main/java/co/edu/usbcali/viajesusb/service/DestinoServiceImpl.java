/**  
 * @Title:  DestinoServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 12:22:04 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**
 * @ClassName: DestinoServiceImpl
 * @Description: TODO
 * @author: Miguel Ortiz
 * @date: 7/09/2021 12:22:04 p. m.
 * @Copyright: USB
 */

@Scope("singleton")
@Service
public class DestinoServiceImpl implements DestinoService {

	@Autowired
	private DestinoRepository destinoRepository;
	


	
	@Autowired
	private TipoDestinoService tipoDestinoService;

	/**
	 * <p>
	 * Title: findByTipoDestino_Codigo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigoTipoDestino
	 * @return
	 * @throws SQLException 
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByTipoDestino_Codigo(java.lang.String)
	 */

	@Override
	public List<Destino> findByTipoDestino_Codigo(String codigoTipoDestino) throws SQLException {

		List<Destino> lstDestino = null;
		
		if (Utilities.isNull(codigoTipoDestino)) {
			throw new RuntimeException("El codigo de tipo destino es obligatorio");
		}
		
		if (!Utilities.isOnlyLetters(codigoTipoDestino)) {
			throw new RuntimeException("El codigo de tipo destino no puede contener numeros");
		}
		
		if (codigoTipoDestino.length()>5) {
			throw new RuntimeException("La cantidad de caracteres no puede superar el total de 5");
		}

		lstDestino = destinoRepository.findByTipoDestino_Codigo(codigoTipoDestino);
		
		
		if (lstDestino.isEmpty()) {
			throw new RuntimeException("No se encuentran destinos con el codigo del tipo destino ingresado");
		}

		return lstDestino;
	}

	/**
	 * <p>
	 * Title: findByEstado
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param estado
	 * @param pageable
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByEstado(java.lang.String,
	 *      org.springframework.data.domain.Pageable)
	 */

	@Override
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws SQLException {
		Page<Destino> pageDestino = null;
		
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El estado de tipo destino es obligatorio");
		}
		
		if (Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado no puede contener numeros");
		}
		
		if (estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres no puede superar el total de 1");
		}
		
		if(pageable.isUnpaged()) {
			throw new RuntimeException("Debe ingresar un parametro de tipo pageable");
		}
		
		

		pageDestino = destinoRepository.findByEstado(estado,pageable);
		
		if (pageDestino.isEmpty()) {
			throw new RuntimeException("No se han encontrado destinos con el parametro ingresado");
		}
		
		return pageDestino;
	}
	
	/**
	 * 
	 * @Title: guardarDestino   
	 * @Description: TODO 
	 * @param: @param destino
	 * @param: @throws SQLException      
	 * @return: void      
	 * @throws
	 */
	@Override
	public Destino guardarDestino(DestinoDTO destinoDTO) throws SQLException{
		Destino destino = null;
		TipoDestino tipoDestino = null;
		
			
		//TODO: Validar que el codigo es unico 
		
		destino = new Destino();
		
		if (Utilities.isNull(destinoDTO.getAire())) {
			throw new SQLException("El parametro aire no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getAire())){
			throw new SQLException("El parametro aire no puede tener numeros");
		}else if(destinoDTO.getAire().length()>1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		}else {
			destino.setAire(destinoDTO.getAire());
		}
		
		if (Utilities.isNull(destinoDTO.getTierra())) {
			throw new SQLException("El parametro tierra no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getTierra())){
			throw new SQLException("El parametro tierra no puede tener numeros");
		}else if(destinoDTO.getTierra().length()>1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		}else {
			destino.setTierra(destinoDTO.getTierra());
		}
		
		if (Utilities.isNull(destinoDTO.getMar())) {
			throw new SQLException("El parametro mar no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getMar())){
			throw new SQLException("El parametro mar no puede tener numeros");
		}else if(destinoDTO.getMar().length()>1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		}else {
			destino.setMar(destinoDTO.getMar());
		}
		
		if (Utilities.isNull(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede ser nulo");
		}else if (!Utilities.isOnlyLetters(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede contener numeros");
		}else if(destinoDTO.getCodigo().length()>5) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 5");
		}else {
			destino.setCodigo(destinoDTO.getCodigo());
		}
		
		if (Utilities.isNull(destinoDTO.getNombre())) {
			throw new SQLException("El nombre del destino no puede ser nulo");
		}else if(destinoDTO.getNombre().length()>100) {
			throw new SQLException("La cantidad de caracteres del nombre del destino no pueden exceder el total de 100");
		}else {
			destino.setNombre(destinoDTO.getNombre());
		}
		
		if (Utilities.isNull(destinoDTO.getDescripcion())) {
			throw new SQLException("La descripcion del destino no puede ser nulo");
		}else if(destinoDTO.getDescripcion().length()>300) {
			throw new SQLException("La cantidad de caracteres de la descripcion del destino no pueden exceder el total de 300");
		}else {
			destino.setDescripcion(destinoDTO.getDescripcion());
		}
		
		if (Utilities.isNull(destinoDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}else if(destinoDTO.getEstado().length()>1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}else {
			destino.setEstado(destinoDTO.getEstado());
		}
		
		if (destinoDTO.getFechaCreacion()==null) {
			throw new SQLException("La fecha de creacion de un destino no puede ser nula");
		}else {
			destino.setFechaCreacion(destinoDTO.getFechaCreacion());
		}
		
		if (Utilities.isNull(destinoDTO.getUsuCreador())) {
			throw new SQLException("El nombre del creador de un destino no puede ser nulo");
		}else if(destinoDTO.getUsuCreador().length()>10) {
			throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		}else {
			destino.setUsuCreador(destinoDTO.getUsuCreador());
		}
		
		
		
		
		
		
		tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);
		
		if (tipoDestino==null) {
			throw new SQLException("El tipo destino"+destinoDTO.getCodigoTipoDestino() + "No existe");
		}
		
		destino.setTipoDestino(tipoDestino);
		
		destinoRepository.save(destino);
		return destino;
		
		
	}
	
	@Override
	public Destino actualizarDestino(DestinoDTO destinoDTO) throws SQLException{
		Optional<Destino> destino = null;
		TipoDestino tipoDestino = null;
		
		destino = findById(destinoDTO.getIdDest());
		
		if (!destino.isPresent()) {
			throw new SQLException("El destino con id "+ destinoDTO.getIdDest()+" No existe");
			
		}
		
		if (Utilities.isNull(destinoDTO.getIdDest())) {
			throw new SQLException("El id del cliente no puede ser nulo");
		}else {
			destino.get().setIdDest(destinoDTO.getIdDest());
		}
		
		if (Utilities.isNull(destinoDTO.getAire())) {
			throw new SQLException("El parametro aire no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getAire())){
			throw new SQLException("El parametro aire no puede tener numeros");
		}else if(destinoDTO.getAire().length()>1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		}else {
			destino.get().setAire(destinoDTO.getAire());
		}
		
		if (Utilities.isNull(destinoDTO.getTierra())) {
			throw new SQLException("El parametro tierra no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getTierra())){
			throw new SQLException("El parametro tierra no puede tener numeros");
		}else if(destinoDTO.getTierra().length()>1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		}else {
			destino.get().setTierra(destinoDTO.getTierra());
			
		}
		
		if (Utilities.isNull(destinoDTO.getMar())) {
			throw new SQLException("El parametro mar no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getMar())){
			throw new SQLException("El parametro mar no puede tener numeros");
		}else if(destinoDTO.getMar().length()>1) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 1");
		}else {
			destino.get().setMar(destinoDTO.getMar());
		}
		
		if (Utilities.isNull(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede ser nulo");
		}else if (!Utilities.isOnlyLetters(destinoDTO.getCodigo())) {
			throw new SQLException("El codigo no puede contener numeros");
		}else if(destinoDTO.getCodigo().length()>5) {
			throw new SQLException("La cantidad de caracteres no puede ser mayor a 5");
		}else {
			destino.get().setCodigo(destinoDTO.getCodigo());
		}
		
		
		if (Utilities.isNull(destinoDTO.getNombre())) {
			throw new SQLException("El nombre del destino no puede ser nulo");
		}else if(destinoDTO.getNombre().length()>100) {
			throw new SQLException("La cantidad de caracteres del nombre del destino no pueden exceder el total de 100");
		}else {
			destino.get().setNombre(destinoDTO.getNombre());
		}
	
		if (Utilities.isNull(destinoDTO.getDescripcion())) {
			throw new SQLException("La descripcion del destino no puede ser nulo");
		}else if(destinoDTO.getDescripcion().length()>300) {
			throw new SQLException("La cantidad de caracteres de la descripcion del destino no pueden exceder el total de 300");
		}else {
			destino.get().setDescripcion(destinoDTO.getDescripcion());
		}
		
		if (Utilities.isNull(destinoDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(destinoDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}else if(destinoDTO.getEstado().length()>1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}else {
			destino.get().setEstado(destinoDTO.getEstado());
		}
		
		if (destinoDTO.getFechaCreacion()==null) {
			throw new SQLException("La fecha de creacion de un destino no puede ser nula");
		}else {
			destino.get().setFechaCreacion(destinoDTO.getFechaCreacion());
		}
		
		if (Utilities.isNull(destinoDTO.getUsuCreador())) {
			throw new SQLException("El nombre del creador de un destino no puede ser nulo");
		}else if(destinoDTO.getUsuCreador().length()>10) {
			throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		}else {
			destino.get().setUsuCreador(destinoDTO.getUsuCreador());
		}
		
		
		
		
		tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);
		
		if (tipoDestino==null) {
			throw new SQLException("El tipo destino"+destinoDTO.getCodigoTipoDestino() + "No existe");
		}
		
		destino.get().setTipoDestino(tipoDestino);
		
		destinoRepository.save(destino.get());
		return destino.get();
		
		
		
	}
	
	public Optional<Destino> findById(Long idDest) throws SQLException{
		
		if (idDest==null) {
			throw new SQLException("Debe ingresar un id destino");
		}
		
		return destinoRepository.findById(idDest);
		 
		
	}
	
	
	@Override
	public void eliminarDestino(Long destinoDTO) throws SQLException{
		
		
		if (Utilities.isNull(destinoDTO)) {
			throw new RuntimeException("El id destino es obligatorio");
		}
		
		if (destinoRepository.existsById(destinoDTO)==false) {
			throw new RuntimeException("El destino no se encontro");
		}
		
		destinoRepository.findById(destinoDTO).ifPresent(detallePlan->{
			if (detallePlan.getDetallePlan().isEmpty()==false) {
				throw new RuntimeException("El destino ya esta asignado a un detalle plan");
			}
		});
		
		destinoRepository.deleteById(destinoDTO);
		
	
	}
}
