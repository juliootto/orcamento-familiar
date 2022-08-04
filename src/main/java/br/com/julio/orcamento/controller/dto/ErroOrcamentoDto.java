package br.com.julio.orcamento.controller.dto;

import br.com.julio.orcamento.modelo.Orcamento;

public class ErroOrcamentoDto extends OrcamentoDto {
	
	private String erro;

	public ErroOrcamentoDto(Orcamento orcamento,String erro) {
		super(orcamento);
		this.erro = erro;
	}

	public String getErro() {
		return erro;
	}

}
