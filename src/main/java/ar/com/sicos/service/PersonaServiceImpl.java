package ar.com.sicos.service;

import ar.com.sicos.model.Domicilio;
import ar.com.sicos.model.Persona;
import ar.com.sicos.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> getAll() {
		return personaRepository.findAll();
	}

	@Override
	public void save(Persona persona) {
		this.personaRepository.save(persona);
	}

	@Override
	public Persona getById(String id) {
		return Optional.of(id)
				.flatMap(personaRepository::findById)
				.orElseThrow(() -> new RuntimeException("Persona con id " + id + " no existe"));
	}

	@Override
	public void delete(String id) {
		this.personaRepository.deleteById(id);
	}

	@Override
	public Page<Persona> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.personaRepository.findAll(pageable);
	}
}
