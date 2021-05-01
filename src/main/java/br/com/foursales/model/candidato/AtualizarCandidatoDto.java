package br.com.foursales.model.candidato;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class AtualizarCandidatoDto extends CandidatoBaseDto {

	@NotNull(message = "Id é obrigatório")
	private Integer id;

	private String nome;

	@Length(min = 11, max = 11, message = "O cpf precisa ter 11 dígitos")
	private String cpf;

}
