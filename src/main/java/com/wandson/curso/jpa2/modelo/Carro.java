package com.wandson.curso.jpa2.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String placa;

	private String cor;

	private String chassi;

	private BigDecimal valorDiaria;

	@ManyToOne
	@JoinColumn(name = "codigo_modelo")
	private ModeloCarro modelo;

	@ManyToMany
	@JoinTable(name = "carro_acessorio", joinColumns = @JoinColumn(name = "codigo_carro"), inverseJoinColumns = @JoinColumn(name = "codigo_acessorio"))
	private List<Acessorio> acessorios;

	@OneToMany(mappedBy = "carro")
	private List<Aluguel> alugueis;

}