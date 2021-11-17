/**  
 * @Title:  TipoDestinoDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   28/09/2021 11:21:07 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**   
 * @ClassName:  TipoDestinoDTO   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   28/09/2021 11:21:07 a. m.      
 * @Copyright:  USB
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoDestinoDTO implements Serializable {
	
	
	
	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	private static final long serialVersionUID = -7522400646942849931L;
	
	private Long idTide;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;

}
