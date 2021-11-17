/**  
 * @Title:  TipoIdentificacionDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Miguel Ortiz     
 * @date:   29/09/2021 10:51:41 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

/**   
 * @ClassName:  TipoIdentificacionDTO   
 * @Description: TODO   
 * @author: Miguel Ortiz     
 * @date:   29/09/2021 10:51:41 a. m.      
 * @Copyright:  USB
 */

@Getter
@Setter
public class TipoIdentificacionDTO {
	
	
	private Long idTiid;
	private String codigo;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;

}
