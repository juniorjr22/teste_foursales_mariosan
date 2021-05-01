package br.com.foursales.model.candidato;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.foursales.model.cartao.CartaoDto;
import br.com.foursales.model.cartao.SalvarCartaoDto2;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class SalvarCandidatoDto extends CandidatoBaseDto {

	public SalvarCandidatoDto(String nome, String cpf, List<CartaoDto> cartoes, String telefone, String endereco,
			String bairro, String cidade, String estado, Integer id) {
		super(telefone, endereco, bairro, cidade, estado);
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cartoes = cartoes;
	}

	private Integer id;

	@NotNull(message = "Nome é obrigatório")
	@NotEmpty(message = "Nome não pode ser vazio")
	private String nome;

	@NotNull(message = "Cpf é obrigatório")
	@NotEmpty(message = "Cpf não pode ser vazio")
	@Length(min = 11, max = 11, message = "O cpf precisa ter 11 dígitos")
	private String cpf;

	private List<CartaoDto> cartoes;

}
