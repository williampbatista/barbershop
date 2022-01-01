package br.com.barbershop.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultResponse {
	
	private Integer code;
	private String message;
	private Object payload;

}
