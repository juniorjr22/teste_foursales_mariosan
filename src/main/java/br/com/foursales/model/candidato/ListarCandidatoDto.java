package br.com.foursales.model.candidato;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.foursales.model.cartao.CartaoDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
public class ListarCandidatoDto extends CandidatoBaseDto {

	public ListarCandidatoDto(Integer id, String nome, String cpf, String telefone, String endereco, String bairro,
			String cidade, String estado, List<CartaoDto> cartoes) {
		super(telefone, endereco, bairro, cidade, estado);
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cartoes = cartoes;
	}

	@NotNull(message = "Id é obrigatório")
	private Integer id;

	private String nome;

	@Length(min = 11, max = 11, message = "O cpf precisa ter 11 dígitos")
	private String cpf;

	private List<CartaoDto> cartoes;

}
