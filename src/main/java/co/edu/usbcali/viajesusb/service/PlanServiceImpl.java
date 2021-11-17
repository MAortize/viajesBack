package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.PlanDTO;
import co.edu.usbcali.viajesusb.repository.PlanRepository;
import co.edu.usbcali.viajesusb.utils.Utilities;

public class PlanServiceImpl implements PlanService {

	
	@Autowired
	private PlanRepository planRepository;
	
	
	@Autowired
	private ClienteService clienteService;
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	
	
	@Override
	public void guardarPlan(PlanDTO planDTO) throws SQLException {
		Plan plan = new Plan();
		Cliente cliente = null;
		Usuario usuario = null;
		
		if (Utilities.isNull(planDTO.getCodigo())) {
			throw new RuntimeException("El codigo del plan no puede ser nulo");
		}
		if (planDTO.getCodigo().length()>5) {
			throw new RuntimeException("La cantidad de caracteres no puede ser mayor 5");
		}
		plan.setCodigo(planDTO.getCodigo());
		
		if (Utilities.isNull(planDTO.getNombre())) {
			throw new RuntimeException("El nombre del plan no puede ser nulo");
		}
		if (planDTO.getNombre().length()>100) {
			throw new RuntimeException("La cantidad de caracteres no puede ser mayor 100");
		}
		plan.setNombre(planDTO.getNombre());
		
		if (Utilities.isNull(planDTO.getDescripcionSolicitud())) {
			throw new RuntimeException("La descrripcion de la solicitud no puede ser nula");	
		}
		if (Utilities.isNumeric(planDTO.getDescripcionSolicitud())) {
			throw new RuntimeException("La descripcion de la solicitud no puede contener solo numeros");	
		}
		if (planDTO.getDescripcionSolicitud().length()>300) {
			throw new RuntimeException("La cantidad de caracteres no puede ser mayor 300");
		}
		plan.setDescripcionSolicitud(planDTO.getDescripcionSolicitud());
		
		if (Utilities.isNull(planDTO.getCantidadPersonas())) {
			throw new RuntimeException("El campo cantidad de personas no puede ser nulo");
		}
		plan.setCantidadPersonas(planDTO.getCantidadPersonas());
		
		if (Utilities.isNull(planDTO.getFechaSolicitud())) {
			throw new RuntimeException("La fecha de la solicitud no puede ser nula");
		}
		plan.setFechaSolicitud(planDTO.getFechaSolicitud());
		
		if (!Utilities.isNull(planDTO.getFechaInicioViaje())) {
			if (planDTO.getFechaInicioViaje().compareTo(planDTO.getFechaSolicitud())<0) {
				throw new RuntimeException("La fecha de inicio del viaje no puede ser antes que la fecha de solicitud");
			}
		}
		plan.setFechaInicioViaje(planDTO.getFechaInicioViaje());
		
		if (!Utilities.isNull(planDTO.getFechaFinViaje())) {
			if (planDTO.getFechaFinViaje().compareTo(planDTO.getFechaInicioViaje())<0) {
				throw new RuntimeException("La fecha de fin de viaje no puede ser antes de que inicie el viaje");
			}
		}
		plan.setFechaFinViaje(planDTO.getFechaFinViaje());
		
		if (Utilities.isNull(planDTO.getValorTotal())) {
			throw new RuntimeException("El valor total del viaje no puede ser nulo");
		}
		plan.setValorTotal(planDTO.getValorTotal());
		
		if (Utilities.isNull(planDTO.getFechaCreacion())) {
			throw new RuntimeException("La fecha de creacion del plan no puede ser nula");
		}
		plan.setFechaCreacion(planDTO.getFechaCreacion());
		
		if (Utilities.isNull(planDTO.getUsuCreador())) {
			throw new RuntimeException("El usuario que creo el plan no puede quedar nulo");
		}
		if (planDTO.getUsuCreador().length()>10) {
			throw new RuntimeException("La cantidad de caracteres no puede ser mayor 10");
		}
		plan.setUsuCreador(planDTO.getUsuCreador());
		
		if (Utilities.isNull(planDTO.getEstado())) {
			throw new RuntimeException("El estado del plan no puede quedar nulo");
		}
		if (Utilities.isNumeric(planDTO.getEstado())) {
			throw new RuntimeException("El estado no puede contener numeros");
		}
		if (planDTO.getEstado().length()>1) {
			throw new RuntimeException("La cantidad de caracteres no puede ser mayor 1");
		}
		
		
		cliente = clienteService.findByNumeroIdentificacionLike(planDTO.getNoIdentificacionCliente());
		
		if (cliente==null) {
			throw new RuntimeException("No existe cliente con ese numero de identificacion");
		}
		
		usuario = usuarioService.findByIdentificacionLike(planDTO.getIdentificacionUsuario());
		
		if (usuario==null) {
			throw new RuntimeException("No existe usuario con esa identificacion");
		}
		
		planRepository.save(plan);
		
	}

	@Override
	public void actualizarPlan(PlanDTO planDTO) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarPlan(PlanDTO planDTO) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
