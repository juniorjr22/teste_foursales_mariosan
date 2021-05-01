package br.com.foursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foursales.model.candidato.Candidato;

public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

	Candidato findByCartoesId(Integer id);

}
