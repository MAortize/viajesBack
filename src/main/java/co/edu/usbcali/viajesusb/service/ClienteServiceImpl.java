/**  
 * @Title:  ClienteServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   7/09/2021 9:25:39 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.repository.ClienteRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**
 * @ClassName: ClienteServiceImpl
 * @Description: TODO
 * @author: Miguel Ortiz
 * @date: 7/09/2021 9:25:39 p. m.
 * @Copyright: USB
 */

@Scope("singleton")
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	

	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	
	
	
	private Cliente cliente;
	

	@Override
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable)
			throws SQLException {
		Page<Cliente> pageCliente = null;

	
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El estado del cliente no puede ser nulo");
		}
		if(Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado no debe contener numeros");
		}
		if(estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}
		if (pageable == null  ) {
			throw new RuntimeException("El estado del cliente es obligatorio insertarlo");
		}

		pageCliente = clienteRepository.findByEstadoOrderByNumeroIdentificacionAsc(estado, pageable);

		return pageCliente;
	}


	@Override
	public Cliente findByCorreoIgnoreCase(String correo) throws SQLException {
		
		if (Utilities.isNull(correo)) {
			throw new RuntimeException("Para buscar un cliente debe ingresar el correo electronico");
		}
		if (!Utilities.isValidEmail(correo)) {
			throw new RuntimeException("El correo ingresado es invalido");
		}
		
		if (correo.length()>100) {
			throw new RuntimeException("La cantidad de caracteres ingresados supera el establecido que es 100");
		}

		cliente = clienteRepository.findByCorreoIgnoreCase(correo);
		
		if (cliente==null) {
			throw new RuntimeException("No existe el cliente con el parametro ingresado anteriormente");
		}
		
		
		return cliente;
	}


	@Override
	public Cliente findByNumeroIdentificacionLike(String noIdentificacion) throws SQLException {

		
		if (Utilities.isNull(noIdentificacion)) {
			throw new RuntimeException("El numero de identificacion del cliente no puede ser nulo");
		}
		
		if (!Utilities.isNumeric(noIdentificacion)) {
			throw new RuntimeException("El no de identificacion no puede contener letras");
		}
		
		if (noIdentificacion.length()>15) {
			throw new RuntimeException("La cantidad de caracteres del numero de identificacion no puede exceder el total de 15");
		}
		
		
		cliente = clienteRepository.findByNumeroIdentificacionLike(noIdentificacion);
		
		if (cliente == null) {
			throw new RuntimeException("No existe el cliente con el parametro ingresado anteriormente");
		}

		

		return cliente;
	}

	

	@Override
	public Cliente findByNombreLikeIgnoreCase(String nombre) throws SQLException {

		if (Utilities.isNull(nombre)) {
			throw new RuntimeException("Debe ingresar el nombre del usuario que desea buscar, no existe usuario sin nombre");
		}
		
		if (nombre.length()>100) {
			throw new RuntimeException("La cantidad de caracteres ingresados supera el establecido que es 100");
		}

		cliente = clienteRepository.findByNombreLikeIgnoreCase(nombre);

		if (cliente == null) {
			throw new RuntimeException("No existe el cliente con el parametro ingresado anteriormente");
		}

		return cliente;
	}


	@Override
	public List<Cliente> findByFechaNacimientoBetween(Date fecha1, Date fecha2) throws SQLException {
		List<Cliente> lstCliente = null;
		if (Utilities.isNull(fecha1)||Utilities.isNull(fecha2)) {
			throw new RuntimeException("Las fechas no pueden ser nulas");
		}
		
		if(fecha1.compareTo(Constantes.FECHA_ACTUAL)>0) {
			throw new RuntimeException("La fecha que ingreso indica que la persona no a nacido");
		}

		lstCliente = clienteRepository.findByFechaNacimientoBetween(fecha1, fecha2);

		if (lstCliente.isEmpty()) {
			throw new RuntimeException("No existen clientes entre las fechas ingresadas");
		}
		// TODO Auto-generated method stub
		return lstCliente;
	}

	

	@Override
	public Long countByEstado(String estado) throws SQLException {
		Long clientes;
		if (Utilities.isNull(estado)) {
			throw new RuntimeException("El campo estado no puede ser nulo");
		}

		if (Utilities.isNumeric(estado)) {
			throw new RuntimeException("El estado no puede contener numeros");
		}
		if(estado.length()>1) {
			throw new RuntimeException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}
		clientes = clienteRepository.countByEstado(estado);

		if (clientes == null) {
			throw new RuntimeException("No existe el cliente con el parametro ingresado anteriormente");
		}
		return clientes;
	}

	/**
	 * <p>
	 * Title: findByTipoIdentificacion_Codigo
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param codigoTipoIdentificacion
	 * @param pageable
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByTipoIdentificacion_Codigo(java.lang.String,
	 *      org.springframework.data.domain.Pageable)
	 */

	@Override
	public Page<Cliente> findByTipoIdentificacion_Codigo(String codigoTipoIdentificacion, Pageable pageable)
			throws SQLException {
		Page<Cliente> pageCliente = null;

		if (Utilities.isNull(codigoTipoIdentificacion)) {
			throw new RuntimeException("El codigo del tipo identificacion no puede ser nulo");
		}
		
		if (Utilities.isNumeric(codigoTipoIdentificacion)) {
			throw new RuntimeException("El codigo de tipo identificacion no puede contener numeros");
		}
		
		if (!Utilities.isOnlyLetters(codigoTipoIdentificacion)) {
			throw new RuntimeException("El codigo de tipo identificacion no puede contener numeros");
		}
		
		if (codigoTipoIdentificacion.length()>5) {
			throw new RuntimeException("La cantidad de caracteres del codigo tipo identificacion no puede exceder el total de 5");
		}
		
		if (pageable.isUnpaged()) {
			throw new RuntimeException("La paginacion no puede ser nula");
		}

		pageCliente = clienteRepository.findByTipoIdentificacion_Codigo(codigoTipoIdentificacion, pageable);
		
		if (pageCliente.isEmpty()) {
			throw new RuntimeException("No se encuentra ningun cliente con el parametro ingresado anteriormente");
		}

		return pageCliente;
	}

	/**
	 * <p>
	 * Title: findByPrimerApellidoOrSegundoApellido
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param primerApellido
	 * @param segundoApellido
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByPrimerApellidoOrSegundoApellido(java.lang.String,
	 *      java.lang.String)
	 */

	@Override
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido)
			throws SQLException {
		List<Cliente> lstCliente = null;
		if (Utilities.isNull(primerApellido)) {
			throw new RuntimeException("El primer apellido no puede ser nulo");
		}
		if (primerApellido.length()>100||segundoApellido.length()>100) {
			throw new RuntimeException("La cantidad de caracteres de los apellidos no puede exceder el total de 100");
		}
		lstCliente = clienteRepository.findByPrimerApellidoOrSegundoApellido(primerApellido, segundoApellido);
		if (lstCliente.isEmpty()) {
			throw new RuntimeException("No existen clientes con los parametros ingresados");
		}
		return lstCliente;
	}

	

	/**
	 * <p>
	 * Title: ultimaConsulta
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param nombre
	 * @return
	 * @throws SQLException
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#ultimaConsulta(java.lang.String)
	 */

	@Override
	public List<ClienteDTO> ultimaConsulta(String nombre) throws SQLException {
		List<ClienteDTO> lstCliente = null;
		if (Utilities.isNull(nombre)) {
			throw new RuntimeException("El nombre no puede ser nulo");
		}
		
		if (nombre.length()>100) {
			throw new RuntimeException("La cantidad de caracteres de los nombres no puede exceder el total de 100");
		}
		
		lstCliente = clienteRepository.ultimaConsulta(nombre);
		if (lstCliente.isEmpty()) {
			throw new RuntimeException("No existen clientes con el nombre " + nombre);
		}
		return lstCliente;
	}

	@Override
	public Cliente guardarCliente(ClienteDTO clienteDTO) throws SQLException {
		Cliente cliente = null;
		TipoIdentificacion tipoIdentificacion = null;

		cliente = new Cliente();
		
		//Validaciones de numero identificacion
		//...VALIDADO....
		if (clienteDTO.getNumeroIdentificacion()==null || clienteDTO.getNumeroIdentificacion().trim().equals("")) {
			throw new SQLException("El numero de identificacion no puede ser nulo");
		}else if(!Utilities.isNumeric(clienteDTO.getNumeroIdentificacion())) {
			throw new SQLException("El numero de identificacion no puede contener letras "+ clienteDTO.getNumeroIdentificacion());
		}else if(clienteDTO.getNumeroIdentificacion().length()>15) {
			throw new SQLException("La cantidad de digitos del numero identificacion no pueden exceder el total de 15");
		}else {
			cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
		}
		
		//Validaciones para primer apellido
		//...VALIDADO....
		if (clienteDTO.getPrimerApellido()==null || clienteDTO.getPrimerApellido().trim().equals("")) {
			throw new SQLException("El primer apellido del cliente no puede ser nulo");
		}else if(clienteDTO.getPrimerApellido().length()>100) {
			throw new SQLException("La cantidad de caracteres del primer apellido del cliente no pueden exceder el total de 100");
		}else {
			cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
		}
		
		//Validaciones para segundo apellido
		//...VALIDADO....\\
		if (clienteDTO.getSegundoApellido()!=null) {
			if (clienteDTO.getSegundoApellido().length()>100) {
				throw new SQLException("La cantidad de caracteres del segundo apellido del cliente no pueden exceder el total de 100");
			}
		}else {
			cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
		}
		
		//Validaciones para el nombre
		//...VALIDADO....
		if (clienteDTO.getNombre()==null || clienteDTO.getNombre().trim().equals("")) {
			throw new SQLException("El nombre del cliente no puede ser nulo");
		}else if(clienteDTO.getNombre().length()>100) {
			throw new SQLException("La cantidad de caracteres del nombre del cliente no pueden exceder el total de 100");
		}else {
			cliente.setNombre(clienteDTO.getNombre());
		}
		
		
		//Validaciones para el numero de telefono 1
		//...VALIDADO....
		if (clienteDTO.getTelefonoUno()==null || clienteDTO.getTelefonoUno().trim().equals("")) {
			throw new SQLException("El telefono 1 del cliente no puede ser nulo");
		}else if(!Utilities.isNumeric(clienteDTO.getTelefonoUno())){
			throw new SQLException("El numero telefonico no debe contener letras");
		}else if(clienteDTO.getTelefonoUno().length()>15) {
			throw new SQLException("La cantidad de digitos del numero telefonico 1 no pueden exceder el total de 15");
		}else {
			cliente.setTelefonoUno(clienteDTO.getTelefonoUno());
		}
		
		//Validaciones para el numero de telefono 2
		//...VALIDADO....\\
		if (clienteDTO.getTelefonoDos()!=null) {
			if(clienteDTO.getTelefonoDos().length()>15) {
				throw new SQLException("La cantidad de digitos del numero telefonico 2 no pueden exceder el total de 15");
			}
		}else {
			cliente.setTelefonoDos(clienteDTO.getTelefonoDos());
		}
		
		
		//Validaciones para el correo electronico del cliente
		//...Validado....\\
		if (Utilities.isNull(clienteDTO.getCorreo())) {
			throw new SQLException("El correo no puede ser nulo");
		
		}else if (!Utilities.isValidEmail(clienteDTO.getCorreo())) {
			throw new SQLException("El correo no es valido");
			
		}else if(clienteDTO.getCorreo().length()>100) {
			throw new SQLException("La cantidad de caracteres del correo no puede exceder el total de 100");
		}else {
			cliente.setCorreo(clienteDTO.getCorreo());
		}
		
		//Validaciones para el genero del cliente
		//...Validado....\\
		if (clienteDTO.getSexo()==null || clienteDTO.getSexo().trim().equals("")) {
			throw new SQLException("El genero del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(clienteDTO.getSexo())) {
			throw new SQLException("El genero no debe contener numeros");
		}else if(clienteDTO.getSexo().length()>1) {
			throw new SQLException("La cantidad maxima de caracteres para el genero es de 1");
		}else {
			cliente.setSexo(clienteDTO.getSexo());
		}
		
		
		//Validaciones para la fecha de nacimiento del cliente
		//...Validado....\\
		if(clienteDTO.getFechaNacimiento()==null) {
			throw new SQLException("La fecha de nacimiento del cliente no puede ser nula");
		}else if(clienteDTO.getFechaNacimiento().compareTo(Constantes.FECHA_ACTUAL)>0) {
			throw new SQLException("La fecha de nacimiento de la persona que ingreso, indica que aun no ha nacido");
		}else {
			cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		}
		
		
		//Validaciones para la fecha de creacion de un cliente
		//...Validado....\\
		if (clienteDTO.getFechaCreacion()==null) {
			throw new SQLException("La fecha de creacion de un cliente no puede ser nula");
		}else {
			cliente.setFechaCreacion(clienteDTO.getFechaCreacion());
		}
		
		
		//Validaciones para el nombre del usuario creador de un cliente 
		//...Validado....\\
		if (clienteDTO.getUsuCreador()==null || clienteDTO.getUsuCreador().trim().equals("")) {
			throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
		}else if(clienteDTO.getUsuCreador().length()>10) {
			throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		}else {
			cliente.setUsuCreador(clienteDTO.getUsuCreador());
		}
		
		
		//Validacioones para el estado del cliente
		//...Validado...\\
		if (clienteDTO.getEstado()==null || clienteDTO.getEstado().trim().equals("")) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(clienteDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}else if(clienteDTO.getEstado().length()>1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}else {
			cliente.setEstado(clienteDTO.getEstado());
		}
		
		
		tipoIdentificacion = tipoIdentificacionService
				.findByCodigoAndEstado(clienteDTO.getCodigoTipoIdentificacion(), clienteDTO.getEstadoTipoIdentificacion());

		if (tipoIdentificacion == null) {
			throw new SQLException("El tipo identificacion" + clienteDTO.getCodigoTipoIdentificacion() + "No existe");
		}

		cliente.setTipoIdentificacion(tipoIdentificacion);
		
		
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@Override
	public Cliente actualizarCliente(ClienteDTO clienteDTO) throws SQLException {
		Optional<Cliente> cliente = null;
		TipoIdentificacion tipoIdentificacion = null;

		cliente = findById(clienteDTO.getIdCliente());
		
		if (!cliente.isPresent()) {
			throw new SQLException("El cliente con id "+ clienteDTO.getIdCliente()+" No existe");
			
		}
		
		if (Utilities.isNull(clienteDTO.getIdCliente())) {
			throw new SQLException("El id del cliente no puede ser nulo");
		}else {
			cliente.get().setIdCliente(clienteDTO.getIdCliente());
		}
		
		if (Utilities.isNull(clienteDTO.getNumeroIdentificacion())) {
			throw new SQLException("El numero de identificacion no puede ser nulo");
		}else if(!Utilities.isNumeric(clienteDTO.getNumeroIdentificacion())) {
			throw new SQLException("El numero de identificacion no puede tener letras");
		}else if(clienteDTO.getNumeroIdentificacion().length()>15) {
			throw new SQLException("La cantidad de digitos del numero identificacion no pueden exceder el total de 15");
		}else {
			cliente.get().setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
		}
		
		if (Utilities.isNull(clienteDTO.getPrimerApellido())) {
			throw new SQLException("El primer apellido del cliente no puede ser nulo");
		}else if(clienteDTO.getPrimerApellido().length()>100) {
			throw new SQLException("La cantidad de caracteres del primer apellido del cliente no pueden exceder el total de 100");
		}else if(cliente.get().getPrimerApellido().equals(clienteDTO.getPrimerApellido())){
			
		}else {
			cliente.get().setPrimerApellido(clienteDTO.getPrimerApellido());
		}
		
		if (!Utilities.isNull(clienteDTO.getSegundoApellido())) {
			if (clienteDTO.getSegundoApellido().length()>100) {
				throw new SQLException("La cantidad de caracteres del segundo apellido del cliente no pueden exceder el total de 100");
			}
		}else {
			cliente.get().setSegundoApellido(clienteDTO.getSegundoApellido());
		}
		
		if (Utilities.isNull(clienteDTO.getNombre())) {
			throw new SQLException("El nombre del cliente no puede ser nulo");
		}else if(clienteDTO.getNombre().length()>100) {
			throw new SQLException("La cantidad de caracteres del nombre del cliente no pueden exceder el total de 100");
		}else if(cliente.get().getNombre().equals(clienteDTO.getNombre())) {
			throw new SQLException("El nombre que ingresaste es igual al que ya existe");
		}else {
			cliente.get().setNombre(clienteDTO.getNombre());
		}
		
		if (Utilities.isNull(clienteDTO.getTelefonoUno())) {
			throw new SQLException("El telefono 1 del cliente no puede ser nulo");
		}else if(!Utilities.isNumeric(clienteDTO.getTelefonoUno())){
			throw new SQLException("El numero telefonico no debe contener letras");
		}else if(clienteDTO.getTelefonoUno().length()>15) {
			throw new SQLException("La cantidad de digitos del numero telefonico 1 no pueden exceder el total de 15");
		}else {
			cliente.get().setTelefonoUno(clienteDTO.getTelefonoUno());
		}
		
		
		if (!Utilities.isNull(clienteDTO.getTelefonoDos())) {
			if(clienteDTO.getTelefonoDos().length()>15) {
				throw new SQLException("La cantidad de digitos del numero telefonico 2 no pueden exceder el total de 15");
			}
		}else {
			cliente.get().setTelefonoDos(clienteDTO.getTelefonoDos());
		}
		
		
		if (!Utilities.isValidEmail(clienteDTO.getCorreo())) {
			throw new SQLException("El correo no es valido");
			
		}else if(clienteDTO.getCorreo().length()>100) {
			throw new SQLException("La cantidad de caracteres del correo no puede exceder el total de 100");
		}else {
			cliente.get().setCorreo(clienteDTO.getCorreo());
		}
		
		if (Utilities.isNull(clienteDTO.getSexo())) {
			throw new SQLException("El genero del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(clienteDTO.getSexo())) {
			throw new SQLException("El genero no debe contener numeros");
		}else if(clienteDTO.getSexo().length()>1) {
			throw new SQLException("La cantidad maxima de caracteres para el genero es de 1");
		}else {
			cliente.get().setSexo(clienteDTO.getSexo());
		}
		
		if (Utilities.isNull(clienteDTO.getFechaNacimiento())) {
			throw new SQLException("La fecha de nacimiento del cliente no puede ser nula");
		}else if(clienteDTO.getFechaNacimiento().compareTo(Constantes.FECHA_ACTUAL)>0) {
			throw new SQLException("La fecha de nacimiento de la persona que ingreso, indica que aun no ha nacido");
		}else {
			cliente.get().setFechaNacimiento(clienteDTO.getFechaNacimiento());
		}
		
		if (Utilities.isNull(clienteDTO.getFechaCreacion())) {
			throw new SQLException("La fecha de creacion del cliente no puede ser nula");
		}else {
			cliente.get().setFechaCreacion(clienteDTO.getFechaCreacion());
		}
		
		if (Utilities.isNull(clienteDTO.getUsuCreador())) {
			throw new SQLException("El nombre del creador de un cliente no puede ser nulo");
		}else if(clienteDTO.getUsuCreador().length()>10) {
			throw new SQLException("La cantidad de caracteres del nombre del usuario creador no puede exceder el total de 10");
		}else {
			cliente.get().setUsuCreador(clienteDTO.getUsuCreador());
		}
		
		if (Utilities.isNull(clienteDTO.getEstado())) {
			throw new SQLException("El estado del cliente no puede ser nulo");
		}else if(Utilities.isNumeric(clienteDTO.getEstado())) {
			throw new SQLException("El estado no debe contener numeros");
		}else if(clienteDTO.getEstado().length()>1) {
			throw new SQLException("La cantidad de caracteres del estado no puede exceder el total de 1");
		}else {
			cliente.get().setEstado(clienteDTO.getEstado());
		}
		
		

		tipoIdentificacion = tipoIdentificacionService
				.findByCodigoAndEstado(clienteDTO.getCodigoTipoIdentificacion(), Constantes.ACTIVO);

		if (tipoIdentificacion == null) {
			throw new SQLException("El tipo identificacion" + clienteDTO.getCodigoTipoIdentificacion() + "No existe");
		}

		cliente.get().setTipoIdentificacion(tipoIdentificacion);

		clienteRepository.save(cliente.get());
		return cliente.get();
	}
	
	
	

	public Optional<Cliente> findById(Long idCliente) throws SQLException {

		if (idCliente == null) {
			throw new RuntimeException("Debe ingresar un id destino");
		}

		return clienteRepository.findById(idCliente);

	}
	
	@Override
	public void eliminarCliente(Long clienteDTO) throws SQLException {
		
		
		if (Utilities.isNull(clienteDTO)) {
			throw new RuntimeException("El id del cliente es obligatorio");
		}
		
		if (clienteRepository.existsById(clienteDTO)==false) {
			throw new RuntimeException("El cliente no se encontro");
			
		}
		
		clienteRepository.findById(clienteDTO).ifPresent(plan->{
			if (plan.getPlan().isEmpty()==false) {
				throw new RuntimeException("El cliente esta asignado a un plan");
			}
		});
		
		clienteRepository.deleteById(clienteDTO);
		
		
		
		
		
		

		

	}

}
