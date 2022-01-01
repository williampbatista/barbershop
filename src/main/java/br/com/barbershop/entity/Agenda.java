package br.com.barbershop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Agenda {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private LocalDateTime horaInicio;
	@Column
	private LocalDateTime horaFim;
	@Column
	private String cliente;
	@Column
	private String servico;
	@Column
	private String barbeiro;

}
