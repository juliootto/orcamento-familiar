package br.com.julio.orcamento.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.julio.orcamento.modelo.Orcamento;

public class DespesaDto extends OrcamentoDto {
	
	private String categoria;

	public DespesaDto(Orcamento orcamento) {
		super(orcamento);
		if (orcamento.getCategoria() != null) {
			this.categoria = orcamento.getCategoria().getDescricao();
		}
	}

	public String getCategotia() {
		return categoria;
	}
	
	
	public static List<OrcamentoDto> converter(List<Orcamento> orcamento) {
		return orcamento.stream().map(DespesaDto::new).collect(Collectors.toList());
	}


}
