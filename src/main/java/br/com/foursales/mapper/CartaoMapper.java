package br.com.foursales.mapper;

import br.com.foursales.model.cartao.Cartao;
import br.com.foursales.model.cartao.CartaoDto;
import br.com.foursales.util.CriptografiaUtil;

public class CartaoMapper {

	public static CartaoDto converterCartaoParaCartaoDto(Cartao cartao) {
		return new CartaoDto(CriptografiaUtil.descriptografar(cartao.getNumero()),
				CriptografiaUtil.descriptografar(cartao.getValidade()), cartao.getId());
	}

	public static Cartao converterCartaoDtoParaCartao(CartaoDto cartao) {
		return new Cartao(null, CriptografiaUtil.criptografar(cartao.getNumero()),
				CriptografiaUtil.criptografar(cartao.getValidade()));
	}

}
