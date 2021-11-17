package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;

import co.edu.usbcali.viajesusb.dto.PlanDTO;

public interface PlanService {
	
	public void guardarPlan(PlanDTO planDTO) throws  SQLException;
	
	public void actualizarPlan(PlanDTO planDTO) throws SQLException;
	
	public void eliminarPlan(PlanDTO planDTO) throws SQLException;
	
	
}
