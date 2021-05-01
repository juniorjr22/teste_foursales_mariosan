package br.com.foursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foursales.model.cartao.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

}
