package br.com.foursales.model.cartao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDto {
	
	private String numero;

	private String validade;

	private Integer id;

}
