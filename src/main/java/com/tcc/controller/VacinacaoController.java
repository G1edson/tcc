package com.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.tcc.model.Vacina;
import com.tcc.model.Vacinacao;
import com.tcc.repository.VacinacaoRepository;

@RestController
@RequestMapping(value = "/vacinacao")
public class VacinacaoController {
	
	
	@Autowired
	VacinacaoRepository vacinacaoRepository;
	
	public VacinacaoController(VacinacaoRepository vacinacaoRepository) {
		this.vacinacaoRepository = vacinacaoRepository;
	}
	
	@GetMapping("{id}")
	public Vacinacao buscarPorId(@PathVariable Long id) {
		return vacinacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Vacinacao> buscarTodas() {
		return vacinacaoRepository.findAll();
	}
	
	@PostMapping
	public Vacinacao nova(@RequestBody Vacinacao vacinacao) {
		return vacinacaoRepository.save(vacinacao);
	}
		
	@PutMapping("{id}")
	public Vacinacao atualzar(@PathVariable Long id, Vacinacao vacinacao) {
		return vacinacaoRepository.findById(id).map( novo -> {		
			novo.setObjetivo(novo.getObjetivo());;
			return vacinacaoRepository.save(novo);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("{id}")
	public Vacinacao apagar(@PathVariable Long id) {
		return vacinacaoRepository.findById(id)
				.map(vacinacao -> {
					vacinacaoRepository.delete(vacinacao);
					return vacinacao;
				})
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
	
	@PutMapping("{id}/addanimal")
	public void addAnimal(@PathVariable Long id,  @RequestBody Vacinacao vacinacao, @RequestBody Animal animal) {
		vacinacao.getAnimais().add(animal);
		vacinacaoRepository.findById(id).map( novo -> {		
			novo.setAnimais(vacinacao.getAnimais());
			return vacinacaoRepository.save(novo);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("{id}/addavacina")
	public void addAnimal(@PathVariable Long id,  @RequestBody Vacinacao vacinacao, @RequestBody Vacina vacina) {
		vacinacao.getVacinas().add(vacina);
		vacinacaoRepository.findById(id).map( novo -> {		
			novo.setVacinas(vacinacao.getVacinas());
			return vacinacaoRepository.save(novo);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("{id}/addcliente")
	public void addAnimal(@PathVariable Long id,  @RequestBody Vacinacao vacinacao, @RequestBody Cliente cliente) {
		vacinacaoRepository.findById(id).map( novo -> {		
			novo.setUsuario(cliente);
			return vacinacaoRepository.save(novo);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
