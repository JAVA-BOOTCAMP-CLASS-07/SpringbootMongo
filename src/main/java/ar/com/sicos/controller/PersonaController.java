package ar.com.sicos.controller;

import java.util.List;

import ar.com.sicos.model.Persona;
import ar.com.sicos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {

		return findPaginated(1, "dni", "asc", model);
	}
	
	@GetMapping("/mostrarInfoInsert")
	public String mostrarInfoInsert(Model model) {
		Persona persona = new Persona();
		model.addAttribute("persona", persona);
		return "nuevaPersona";
	}
	
	@PostMapping("/savePersona")
	public String savePersona(@ModelAttribute("persona") Persona persona) {
		personaService.save(persona);
		return "redirect:/";
	}
	
	@GetMapping("/mostrarInfoUpdate/{id}")
	public String mostrarInfoUpdate(@PathVariable ( value = "id") String id, Model model) {
		
		Persona persona = personaService.getById(id);
		
		model.addAttribute("persona", persona);
		return "updatePersona";
	}
	
	@GetMapping("/deletePersona/{id}")
	public String deletePersona(@PathVariable (value = "id") String id) {
		this.personaService.delete(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Persona> page = personaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Persona> listPersonas = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPersonas", listPersonas);
		return "index";
	}
}
