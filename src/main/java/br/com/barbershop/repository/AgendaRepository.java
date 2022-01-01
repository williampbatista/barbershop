package br.com.barbershop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import br.com.barbershop.entity.Agenda;

public interface AgendaRepository extends JpaRepositoryImplementation<Agenda, Long> {

	List<Agenda> findByHoraInicioLessThan(LocalDateTime time);
	
	List<Agenda> findByHoraInicioBetweenAndHoraFimBetween(LocalDateTime timeIni1, LocalDateTime timeFin1, LocalDateTime timeIni2, LocalDateTime timeFin2);
	
	
	@Query("SELECT a FROM Agenda a WHERE :horaIni between horaInicio and horaFim OR :horaFin between horaInicio and horaFim")
	List<Agenda> findByHoraIniAndHoraFin(@Param("horaIni") LocalDateTime horaIni, @Param("horaFin")LocalDateTime horaFin);

}
