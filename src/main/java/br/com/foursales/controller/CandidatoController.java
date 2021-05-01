package br.com.foursales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foursales.model.candidato.AtualizarCandidatoDto;
import br.com.foursales.model.candidato.ListarCandidatoDto;
import br.com.foursales.model.candidato.SalvarCandidatoDto;
import br.com.foursales.service.CandidatoService;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

	@Autowired
	private CandidatoService candidatoService;

	@GetMapping
	public ResponseEntity<List<ListarCandidatoDto>> listar() {
		List<ListarCandidatoDto> candidatos = candidatoService.listar();
		return new ResponseEntity<List<ListarCandidatoDto>>(candidatos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SalvarCandidatoDto> salvar(@Valid @RequestBody SalvarCandidatoDto candidatoFormularioDto) {
		SalvarCandidatoDto candidatoSalvo = candidatoService.salvar(candidatoFormularioDto);
		return new ResponseEntity<SalvarCandidatoDto>(candidatoSalvo, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<AtualizarCandidatoDto> atualizar(
			@Valid @RequestBody AtualizarCandidatoDto candidatoFormularioDto) {
		AtualizarCandidatoDto candidatoSalvo = candidatoService.atualizar(candidatoFormularioDto);
		if (candidatoSalvo != null) {
			return new ResponseEntity<AtualizarCandidatoDto>(candidatoSalvo, HttpStatus.OK);
		} else {
			return new ResponseEntity<AtualizarCandidatoDto>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> excluir(@PathVariable(required = true) Integer id) {
		return new ResponseEntity<>(candidatoService.excluir(id));
	}

}
