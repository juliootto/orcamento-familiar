package br.com.julio.orcamento.controller.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.julio.orcamento.modelo.Orcamento;

public class OrcamentoDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private Date data;
	
	public OrcamentoDto(Orcamento orcamento) {
		this.id = orcamento.getId();
		this.descricao = orcamento.getDescricao();
		this.valor = orcamento.getValor();
		this.data = orcamento.getData();
	}
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public Date getData() {
		return data;
	}

	public static List<OrcamentoDto> converter(List<Orcamento> orcamento) {
		return orcamento.stream().map(OrcamentoDto::new).collect(Collectors.toList());
	}
	
	

}
