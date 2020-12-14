package com.tcc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Vacinacao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	@Column
	private String objetivo;
	@ManyToMany
	@JoinColumn(name = "animal_id")
	private List<Animal> animais;
	@ManyToMany
	@JoinColumn(name = "vacina_id")
	private List<Vacina> vacinas;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente usuario;
	
	public Vacinacao() {}
	
	public Vacinacao(Long id, LocalDate data, String objetivo, List<Animal> animais, List<Vacina> vacinas,
			Cliente usuario) {
		this.id = id;
		this.data = data;
		this.objetivo = objetivo;
		this.animais = animais;
		this.vacinas = vacinas;
		this.usuario = usuario;
	}
	
	@PrePersist
	public void prePersit() {
		setData(LocalDate.now());
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public List<Animal> getAnimais() {
		return animais;
	}
	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
	public List<Vacina> getVacinas() {
		return vacinas;
	}
	public void setVacinas(List<Vacina> vacinas) {
		this.vacinas = vacinas;
	}
	public Cliente getUsuario() {
		return usuario;
	}
	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}
	
}
