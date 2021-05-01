package br.com.foursales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.foursales.mapper.CartaoMapper;
import br.com.foursales.model.candidato.Candidato;
import br.com.foursales.model.cartao.Cartao;
import br.com.foursales.model.cartao.CartaoDto;
import br.com.foursales.model.cartao.ListarCartaoDto2;
import br.com.foursales.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CandidatoService candidatoService;

	public List<Cartao> salvar(List<Cartao> cartoes) {
		List<Cartao> cartoesSalvos = new ArrayList<>();
		cartoes.stream().forEach(cartao -> cartoesSalvos.add(cartaoRepository.save(cartao)));
		return cartoesSalvos;
	}

	public Cartao buscar(Integer idCartao) {
		Optional<Cartao> optional = cartaoRepository.findById(idCartao);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public List<CartaoDto> buscarPorCandidato(Integer idCandidato) {
		List<CartaoDto> cartoes = new ArrayList<>();

		Candidato candidato = candidatoService.buscar(idCandidato);

		if (candidato != null) {
			candidato.getCartoes().stream()
					.forEach(cartao -> cartoes.add(CartaoMapper.converterCartaoParaCartaoDto(cartao)));
		}
		return cartoes;
	}

	public CartaoDto atualizar(@Valid ListarCartaoDto2 listarCartaoDto) {
		if (listarCartaoDto != null) {
			Optional<Cartao> optional = cartaoRepository.findById(listarCartaoDto.getId());
			if (optional.isPresent()) {
				Cartao cartaoBd = optional.get();
				cartaoBd.setNumero(
						listarCartaoDto.getNumero() != null ? listarCartaoDto.getNumero() : cartaoBd.getNumero());
				cartaoBd.setValidade(
						listarCartaoDto.getValidade() != null ? listarCartaoDto.getValidade() : cartaoBd.getValidade());
				return CartaoMapper.converterCartaoParaCartaoDto(cartaoRepository.save(cartaoBd));
			}
		}
		return null;
	}

	public HttpStatus excluir(Integer idCartao) {
		HttpStatus status = HttpStatus.NO_CONTENT;

		Candidato candidato = candidatoService.buscarPorIdCartao(idCartao);
		if (candidato != null) {
			candidatoService.salvar(candidato, idCartao);
		} else {
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return status;
	}

}
