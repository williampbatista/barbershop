package br.com.barbershop.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.barbershop.converter.AgendaConverter;
import br.com.barbershop.dto.AgendaDto;
import br.com.barbershop.entity.Agenda;
import br.com.barbershop.repository.AgendaRepository;

@Service
public class AgendaService {

	@Autowired
	AgendaRepository repository;

	public Object save(AgendaDto dto) {
		if (validarAgenda(dto))
			return AgendaConverter.toDto(repository.save(AgendaConverter.toEntity(dto)));
		throw new IllegalArgumentException("Agenda conflitante");
	}

	public Object findByFilter(Pageable pageable) {
		return AgendaConverter.toDto(repository.findAll(pageable));
	}

	private Boolean validarAgenda(AgendaDto dto) {
		List<Agenda> agendas = repository.findByHoraIniAndHoraFin(dto.getHoraInicio(), dto.getHoraFim());

		if (agendas.isEmpty()) {
			return true;
		}
		return false;
	}

	@Scheduled(cron = "0 */1 * * * *")
	private void verificarAgenda() {
		LocalDateTime ldt = LocalDateTime.now();

		List<Agenda> agendas = repository.findByHoraInicioLessThan(ldt.plus(30, ChronoUnit.MINUTES));

		if (!agendas.isEmpty()) {
			System.out.println("Proximas agendas");
			for (Agenda agenda : agendas) {
				System.out.println("=================");
				System.out.println("Cliente: " + agenda.getCliente());
				System.out.println("Serviço: " + agenda.getServico());
				System.out.println("Barbeiro: " + agenda.getBarbeiro());
				System.out.println("Inicio: " + agenda.getHoraInicio());
				System.out.println("Fim: " + agenda.getHoraFim());
				System.out.println("=================");
			}

		}
	}

	public void popularBancoTeste() {

		LocalDateTime ldt = LocalDateTime.now();
		for (int i = 0; i < 50; i++) {

			Agenda a = new Agenda();
			a.setBarbeiro(geradorNome());
			a.setCliente(geradorNome());
			a.setHoraFim(ldt.plus(i, ChronoUnit.MINUTES));
			a.setHoraInicio(ldt.plus(i, ChronoUnit.MINUTES));
			a.setServico("Generico");
			repository.save(a);

		}

	}

	public String geradorNome() {
		// letras maisculas 65 - 90
		// letras minúsculas 97 - 122

		ThreadLocalRandom gerador = ThreadLocalRandom.current();

		int tamanhoNome = gerador.nextInt(3, 10);
		int tamanhoSobrenome = gerador.nextInt(3, 10);

		char primeiraLetraNome = (char) gerador.nextInt(65, 90);

		StringBuilder nome = new StringBuilder().append(primeiraLetraNome);
		StringBuilder sobreNome = new StringBuilder().append(primeiraLetraNome);

		for (int i = 1; i < tamanhoNome; i++) {
			char letra = (char) gerador.nextInt(97, 122);
			nome.append(letra);
		}

		for (int i = 1; i < tamanhoSobrenome; i++) {
			char letra = (char) gerador.nextInt(97, 122);
			sobreNome.append(letra);
		}

		return nome + " " + sobreNome;
	}

	// inserir cron conforme necessidade
	private void notificarCliente() {
		LocalDateTime ldt = LocalDateTime.now();
		// agendamentos para os proximos 15min
		List<Agenda> agendas = repository.findByHoraInicioLessThan(ldt.plus(15, ChronoUnit.MINUTES));

		// enviar email? sms? notificar no app

	}

}
