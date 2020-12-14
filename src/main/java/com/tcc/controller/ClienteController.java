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

import com.tcc.model.Cliente;
import com.tcc.repository.ClienteRepository;

@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@PostMapping
	public Cliente salvar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
	
	@PutMapping("{id}")
	public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		return clienteRepository.findById(id)
				.map(novo -> {
					//cliente.setId(novo.getId());
					//return clienteRepository.save(cliente);
					novo.setCpf(cliente.getCpf());
					novo.setEmail(cliente.getEmail());
					novo.setNome(cliente.getNome());
					return clienteRepository.save(novo);
				})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	
	@GetMapping
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
	
	@DeleteMapping("{id}")
	public Cliente apagarPorId(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(cliente -> {
					clienteRepository.delete(cliente);
					return cliente;
				})
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
	

}
