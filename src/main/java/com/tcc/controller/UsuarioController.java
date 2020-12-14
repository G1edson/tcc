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

import com.tcc.model.Usuario;
import com.tcc.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("{id}")
	public Usuario buscarPorId(@PathVariable Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping
	public List<Usuario> buscarTodas() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	public Usuario nova(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
		
	@PutMapping("{id}")
	public Usuario atualzar(@PathVariable Long id, Usuario usuario) {
		return usuarioRepository.findById(id).map( novo -> {		
			novo.setUsername(usuario.getUsername());
			novo.setPassword(usuario.getPassword());
			return usuarioRepository.save(novo);
		
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("{id}")
	public Usuario apagar(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(usuario -> {
					usuarioRepository.delete(usuario);
					return usuario;
				})
				.orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
}
