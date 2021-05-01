package br.com.foursales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursales.model.cartao.CartaoDto;
import br.com.foursales.model.cartao.ListarCartaoDto2;
import br.com.foursales.service.CartaoService;

@RestController
@RequestMapping("/cartao")
@CrossOrigin(origins = "http://localhost:4200")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	@GetMapping(value = "/{idCandidato}")
	public ResponseEntity<List<CartaoDto>> buscar(@PathVariable(required = true) Integer idCandidato) {
		List<CartaoDto> cartoes = cartaoService.buscarPorCandidato(idCandidato);
		if (cartoes.size() > 0) {
			return new ResponseEntity<List<CartaoDto>>(cartoes, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<CartaoDto>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping
	public ResponseEntity<CartaoDto> atualizar(@Valid @RequestBody ListarCartaoDto2 listarCartaoDto) {
		CartaoDto cartaoSalvo = cartaoService.atualizar(listarCartaoDto);
		if (cartaoSalvo != null) {
			return new ResponseEntity<CartaoDto>(cartaoSalvo, HttpStatus.OK);
		} else {
			return new ResponseEntity<CartaoDto>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluir(@PathVariable(required = true) Integer id) {
		return new ResponseEntity<>(cartaoService.excluir(id));
	}

}
