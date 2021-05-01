package br.com.foursales.model.candidato;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class CandidatoBaseDto {

	private String telefone;

	private String endereco;

	private String bairro;

	private String cidade;

	private String estado;

}
