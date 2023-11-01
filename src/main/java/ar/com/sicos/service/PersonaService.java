package ar.com.sicos.service;

import java.util.List;

import ar.com.sicos.model.Persona;
import org.springframework.data.domain.Page;

public interface PersonaService {
	List<Persona> getAll();
	void save(Persona persona);
	Persona getById(String id);
	void delete(String id);
	Page<Persona> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
