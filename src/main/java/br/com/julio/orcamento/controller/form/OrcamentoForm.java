package br.com.julio.orcamento.controller.form;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.julio.orcamento.modelo.Categoria;
import br.com.julio.orcamento.modelo.Orcamento;
import br.com.julio.orcamento.modelo.TipoOrcamento;
import br.com.julio.orcamento.repository.OrcamentoRepository;
import br.com.julio.orcamento.repository.TipoOrcamentoRepository;

public class OrcamentoForm {

	@NotNull
	@NotEmpty
	private String descricao;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private Date data;
	private String categoria;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public Orcamento converter(TipoOrcamentoRepository tipoOrcamentoRepository, String nomeTipoOrcamento) {
		TipoOrcamento tipoOrcamento = tipoOrcamentoRepository.findBydescricao(nomeTipoOrcamento);
		//System.out.println("categoria: " + this.categoria);
		if(this.categoria==null) {
			return new Orcamento(this.descricao, this.valor, this.data, tipoOrcamento);
		}
		return new Orcamento(this.descricao, this.valor, this.data, tipoOrcamento,new Categoria(this.categoria));
	}

	public boolean verificaOrcamentoNoMes(OrcamentoRepository orcamentoRepository, String nomeTipoOrcamento) {
		Date firstDay = Date.valueOf(this.getData().toLocalDate().with(TemporalAdjusters.firstDayOfMonth()));
		Date lastDay = Date.valueOf(this.getData().toLocalDate().with(TemporalAdjusters.lastDayOfMonth()));

		Optional<Orcamento> orcamento = orcamentoRepository
				.findByDescricaoAndDataBetweenAndTipoOrcamento_descricao(this.descricao, firstDay, lastDay,nomeTipoOrcamento);

		if (orcamento.isPresent()) {
			return true;
		}
		return false;
	}

}
