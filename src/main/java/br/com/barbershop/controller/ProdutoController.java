package br.com.barbershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.barbershop.dto.ProdutoDto;
import br.com.barbershop.response.DefaultResponse;
import br.com.barbershop.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;
	
	@PostMapping
	public DefaultResponse save(@RequestBody ProdutoDto request) {
		return new DefaultResponse(HttpStatus.OK.value(), null, service.save(request));
	}
	
	
	@GetMapping
	public DefaultResponse get(Pageable pageable) {
		return new DefaultResponse(HttpStatus.OK.value(), null, service.findByFilter(pageable));
	}

}
