package br.com.julio.orcamento.controller.dto;

import java.math.BigDecimal;

public class ValorPorCategoriaDto {
	private String categoria;
	private BigDecimal valor;
	
	public ValorPorCategoriaDto(){
		
	}
	public ValorPorCategoriaDto(String categoria,BigDecimal valor){
		this.categoria = categoria;
		this.valor = valor;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	

}
