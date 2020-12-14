package com.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tcc.model.Vacina;
import com.tcc.repository.VacinaRepository;
@CrossOrigin
@RestController
@RequestMapping(value = "/vacinas")
public class VacinaContoller {

	@Autowired
	private VacinaRepository vacinaRepository;

	public VacinaContoller(VacinaRepository vacinaRepository) {
		this.vacinaRepository = vacinaRepository;
	}
	
	@GetMapping("{id}")
	public Vacina buscarPorId(@PathVariable Long id) {
		return vacinaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Vacina> buscarTodas() {
		return vacinaRepository.findAll();
	}
	
	@PostMapping
	public Vacina nova(@RequestBody Vacina vacina) {
		return vacinaRepository.save(vacina);
	}
	
	@PutMapping("{id}")
	public Vacina atualzar(@PathVariable Long id, Vacina vacina) {
		return vacinaRepository.findById(id).map( novo -> {		
			vacina.setId(novo.getId());
			return vacinaRepository.save(vacina);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("{id}")
	public Vacina apagar(@PathVariable Long id) {
		return vacinaRepository.findById(id)
				.map(vacina -> {
					vacinaRepository.delete(vacina);
					return vacina;
				})
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
	
	
	
}
