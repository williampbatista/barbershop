package br.com.barbershop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import br.com.barbershop.dto.AgendaDto;
import br.com.barbershop.entity.Agenda;

public class AgendaConverter {

	private static ModelMapper mp = new ModelMapper();

	public static AgendaDto toDto(Agenda entity) {
		AgendaDto dto = new AgendaDto();
		mp.map(entity, dto);
		return dto;
	}

	public static Page<AgendaDto> toDto(Page<Agenda> entityList) {
		return entityList.map(entity -> toDto(entity));
	}
	
	
	
	public static Agenda toEntity(AgendaDto dto) {
		Agenda entity = new Agenda();
		mp.map(dto, entity);
		return entity;
	}

	public static Page<Agenda> toEntity(Page<AgendaDto> dtoList) {
		return dtoList.map(dto -> toEntity(dto));
	}

}
