package br.com.foursales.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.foursales.mapper.CandidatoMapper;
import br.com.foursales.mapper.CartaoMapper;
import br.com.foursales.model.candidato.AtualizarCandidatoDto;
import br.com.foursales.model.candidato.Candidato;
import br.com.foursales.model.candidato.ListarCandidatoDto;
import br.com.foursales.model.candidato.SalvarCandidatoDto;
import br.com.foursales.model.cartao.Cartao;
import br.com.foursales.repository.CandidatoRepository;

@Service
public class CandidatoService {

	@Autowired
	private CandidatoRepository candidatoRepository;

	@Autowired
	private CartaoService cartaoService;

	public List<ListarCandidatoDto> listar() {
		List<Candidato> candidatos = candidatoRepository.findAll();
		List<ListarCandidatoDto> listaDto = new ArrayList<>();
		candidatos.stream().forEach(
				candidato -> listaDto.add(CandidatoMapper.converterCandidatoParaListarCandidatoDto(candidato)));
		return listaDto;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SalvarCandidatoDto salvar(SalvarCandidatoDto candidatoFormularioDto) {
		List<Cartao> cartoesSalvos = salvarCartao(candidatoFormularioDto);
		Candidato candidatoSalvo = candidatoRepository.save(
				CandidatoMapper.converterSalvarCandidatoDtoParaCandidato(candidatoFormularioDto, cartoesSalvos));
		return CandidatoMapper.converterCandidatoParaSalvarCandidatoDto(candidatoSalvo);

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void salvar(Candidato candidato, Integer idCartao) {
		List<Cartao> cartoesBd = candidato.getCartoes();
		int i = 0;
		for (Cartao cartao : cartoesBd) {
			if (cartao.getId() == idCartao) {
				cartoesBd.remove(i);
				break;
			}
			i++;
		}
		candidatoRepository.save(candidato);
	}

	private List<Cartao> salvarCartao(SalvarCandidatoDto candidatoFormularioDto) {
		List<Cartao> cartoes = new ArrayList<>();
		List<Cartao> cartoesSalvos = null;

		if (candidatoFormularioDto.getCartoes() != null && candidatoFormularioDto.getCartoes().size() > 0) {
			candidatoFormularioDto.getCartoes().stream()
					.filter(cartao -> cartao.getNumero() != null && cartao.getValidade() != null)
					.forEach(cartao -> cartoes.add(CartaoMapper.converterCartaoDtoParaCartao(cartao)));
		}
		cartoesSalvos = cartaoService.salvar(cartoes);
		return cartoesSalvos;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AtualizarCandidatoDto atualizar(AtualizarCandidatoDto candidatoDto) {
		Optional<Candidato> optional = candidatoRepository.findById(candidatoDto.getId());
		if (optional.isPresent()) {
			Candidato candidatoSalvo = candidatoRepository
					.save(CandidatoMapper.converterCandidatoDtoParaCandidato(candidatoDto, optional.get()));
			return CandidatoMapper.converterCandidatoParaCandidatoDto(candidatoSalvo);
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public HttpStatus excluir(Integer id) {
		HttpStatus status = HttpStatus.NO_CONTENT;
		if (candidatoRepository.findById(id).isPresent()) {
			candidatoRepository.deleteById(id);
		} else {
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return status;
	}

	public Candidato buscar(Integer idCandidato) {
		Optional<Candidato> optional = candidatoRepository.findById(idCandidato);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Candidato buscarPorIdCartao(Integer idCartao) {
		return candidatoRepository.findByCartoesId(idCartao);
	}

}
