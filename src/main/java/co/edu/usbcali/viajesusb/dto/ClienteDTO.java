/**  
 * @Title:  ClienteDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   4/09/2021 4:51:44 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**   
 * @ClassName:  ClienteDTO   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   4/09/2021 4:51:44 p. m.      
 * @Copyright:  USB
 */

@Getter
@Setter

public class ClienteDTO implements Serializable {

	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	
	
	private static final long serialVersionUID = 8650084404524240764L;
	
	
	private Long idCliente;
	private String numeroIdentificacion;
	private String primerApellido;
	private String segundoApellido;
	private String nombre;
	private String telefonoUno;
	private String telefonoDos;
	private String correo;
	private String sexo;
	private Date fechaNacimiento;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	private Long idTiid;
	private String codigoTipoIdentificacion;
	private String estadoTipoIdentificacion;
	
	
	
	
	
	/**   
	 * @Title:  ClienteDTO  
	 * @Author: Miguel Ortiz
	 * @Description:TODO 
	 * @param:  @param nombre  
	 * @throws   
	 */
	
	
	
	
	public ClienteDTO(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	

	
	
	public ClienteDTO(String numeroIdentificacion, String nombre, String estado) {
		super();
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombre = nombre;
		this.estado = estado;
	}





	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}




	
	



	
	
	
	

}
