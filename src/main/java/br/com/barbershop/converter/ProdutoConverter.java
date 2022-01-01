package br.com.barbershop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import br.com.barbershop.dto.ProdutoDto;
import br.com.barbershop.entity.Produto;

public class ProdutoConverter {

	private static ModelMapper mp = new ModelMapper();

	public static ProdutoDto toDto(Produto entity) {
		ProdutoDto dto = new ProdutoDto();
		mp.map(entity, dto);
		return dto;
	}

	public static Page<ProdutoDto> toDto(Page<Produto> entityList) {
		return entityList.map(entity -> toDto(entity));
	}
	
	
	
	public static Produto toEntity(ProdutoDto dto) {
		Produto entity = new Produto();
		mp.map(dto, entity);
		return entity;
	}

	public static Page<Produto> toEntity(Page<ProdutoDto> dtoList) {
		return dtoList.map(dto -> toEntity(dto));
	}

}
