package br.com.barbershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.barbershop.converter.ProdutoConverter;
import br.com.barbershop.dto.ProdutoDto;
import br.com.barbershop.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	public ProdutoDto save(ProdutoDto dto) {
		return ProdutoConverter.toDto(repository.save(ProdutoConverter.toEntity(dto)));
	}

	public Page<ProdutoDto> findByFilter(Pageable pageable) {
		return ProdutoConverter.toDto(repository.findAll(pageable));
	}

}
