package br.com.julio.orcamento.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public class ResumoDto {

	private BigDecimal valorTotalReceitas;
	private BigDecimal valorTotalDespesas;
	private BigDecimal saldo;
	private List<ValorPorCategoriaDto> valorTotalDespesaPorCategoria;

	public ResumoDto(BigDecimal valorTotalReceitas,BigDecimal valorTotalDespesas) {
		this.valorTotalReceitas = valorTotalReceitas;
		this.valorTotalDespesas = valorTotalDespesas;
		this.saldo = valorTotalReceitas.subtract(valorTotalDespesas);
	}
	
	public ResumoDto(BigDecimal valorTotalReceitas,BigDecimal valorTotalDespesas, List<ValorPorCategoriaDto> valorTotalDespesaPorCategoria) {
		this.valorTotalReceitas = valorTotalReceitas;
		this.valorTotalDespesas = valorTotalDespesas;
		this.saldo = valorTotalReceitas.subtract(valorTotalDespesas);
		this.valorTotalDespesaPorCategoria = valorTotalDespesaPorCategoria;
	}
	
	public BigDecimal getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public BigDecimal getValorTotalDespesas() {
		return valorTotalDespesas;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public List<ValorPorCategoriaDto> getValorTotalPorCategoria() {
		return valorTotalDespesaPorCategoria;
	}

	public void addValorPorCategoria(String categoria,BigDecimal valor) {
		this.valorTotalDespesaPorCategoria.add(new ValorPorCategoriaDto(categoria, valor));
	}



}
