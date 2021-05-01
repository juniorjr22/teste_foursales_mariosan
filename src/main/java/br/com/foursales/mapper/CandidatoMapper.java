package br.com.foursales.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.foursales.model.candidato.AtualizarCandidatoDto;
import br.com.foursales.model.candidato.Candidato;
import br.com.foursales.model.candidato.ListarCandidatoDto;
import br.com.foursales.model.candidato.SalvarCandidatoDto;
import br.com.foursales.model.cartao.Cartao;
import br.com.foursales.model.cartao.CartaoDto;
import br.com.foursales.model.cartao.ListarCartaoDto2;
import br.com.foursales.model.cartao.SalvarCartaoDto2;

public class CandidatoMapper {

	public static Candidato converterSalvarCandidatoDtoParaCandidato(SalvarCandidatoDto dto,
			List<Cartao> cartoesSalvos) {
		return new Candidato(dto.getId(), dto.getNome(), dto.getCpf(), dto.getTelefone(), dto.getEndereco(),
				dto.getBairro(), dto.getCidade(), dto.getEstado(), cartoesSalvos);
	}

	public static Candidato converterCandidatoDtoParaCandidato(AtualizarCandidatoDto dto, Candidato candidato) {

		candidato.setNome(dto.getNome() != null && !"".equals(dto.getNome()) ? dto.getNome() : candidato.getNome());
		candidato.setCpf(dto.getCpf() != null ? dto.getCpf() : candidato.getCpf());
		candidato.setTelefone(dto.getTelefone() != null ? dto.getTelefone() : candidato.getTelefone());
		candidato.setEndereco(dto.getEndereco() != null ? dto.getEndereco() : candidato.getEndereco());
		candidato.setBairro(dto.getBairro() != null ? dto.getBairro() : candidato.getBairro());
		candidato.setCidade(dto.getCidade() != null ? dto.getCidade() : candidato.getCidade());
		candidato.setEstado(dto.getEstado() != null ? dto.getEstado() : candidato.getEstado());

		return candidato;
	}

	public static SalvarCandidatoDto converterCandidatoParaSalvarCandidatoDto(Candidato candidato) {
		SalvarCandidatoDto dto = new SalvarCandidatoDto();

		List<CartaoDto> cartoesSalvos = new ArrayList<>();
		candidato.getCartoes().stream()
				.forEach(cartao -> cartoesSalvos.add(CartaoMapper.converterCartaoParaCartaoDto(cartao)));

		dto.setId(candidato.getId());
		dto.setNome(candidato.getNome());
		dto.setCpf(candidato.getCpf());
		dto.setTelefone(candidato.getTelefone());
		dto.setEndereco(candidato.getEndereco());
		dto.setBairro(candidato.getBairro());
		dto.setCidade(candidato.getCidade());
		dto.setEstado(candidato.getEstado());
		if (cartoesSalvos.size() > 0) {
			dto.setCartoes(cartoesSalvos);
		}

		return dto;
	}

	public static AtualizarCandidatoDto converterCandidatoParaCandidatoDto(Candidato candidato) {
		AtualizarCandidatoDto dto = new AtualizarCandidatoDto();

		dto.setId(candidato.getId());
		dto.setNome(candidato.getNome());
		dto.setCpf(candidato.getCpf());
		dto.setTelefone(candidato.getTelefone());
		dto.setEndereco(candidato.getEndereco());
		dto.setBairro(candidato.getBairro());
		dto.setCidade(candidato.getCidade());
		dto.setEstado(candidato.getEstado());

		return dto;
	}

	public static ListarCandidatoDto converterCandidatoParaListarCandidatoDto(Candidato candidato) {
		List<CartaoDto> cartoes = new ArrayList<>();
		candidato.getCartoes().stream()
				.forEach(cartao -> cartoes.add(CartaoMapper.converterCartaoParaCartaoDto(cartao)));
		return new ListarCandidatoDto(candidato.getId(), candidato.getNome(), candidato.getCpf(),
				candidato.getTelefone(), candidato.getEndereco(), candidato.getBairro(), candidato.getCidade(),
				candidato.getEstado(), cartoes);
	}

}
