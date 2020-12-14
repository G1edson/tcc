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

import com.tcc.model.Animal;
import com.tcc.model.Cliente;
import com.tcc.repository.AnimalRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/animais")
public class AnimalController {
	
	@Autowired
	private AnimalRepository animalRepository;

	public AnimalController(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}
	
	public AnimalController() {}
	
	@GetMapping("{id}")
	public Animal buscarPorId(@PathVariable Long id) {
		return animalRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Animal> buscarTodos() {
		return animalRepository.findAll();
	}
	
	@PostMapping
	public Animal novo(@RequestBody Animal animal) {
		return animalRepository.save(animal);
	}
		
	@PutMapping("{id}")
	public Animal atualzar(@PathVariable Long id,  @RequestBody Animal animal) {
		return animalRepository.findById(id).map( novo -> {		
			novo.setTipo(animal.getTipo());
			novo.setIdade(animal.getIdade());
			novo.setSexo(animal.getSexo());
			novo.setRaca(animal.getRaca());
			novo.setDescricao(animal.getDescricao());
			return animalRepository.save(novo);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("{id}")
	public Animal apagar(@PathVariable Long id) {
		return animalRepository.findById(id)
			.map(animal -> {
				animalRepository.delete(animal);
				return animal;
			})
			.orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
			);
	}

}
