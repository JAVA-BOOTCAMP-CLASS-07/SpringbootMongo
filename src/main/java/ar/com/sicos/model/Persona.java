package ar.com.sicos.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "personas")
@Data
@ToString
public class Persona {

	@Id
	private String id;

	private String nombre;
	private String apellido;
	private int dni;
}
