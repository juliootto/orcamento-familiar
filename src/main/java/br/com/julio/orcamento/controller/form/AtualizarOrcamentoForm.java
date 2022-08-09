package br.com.julio.orcamento.controller.form;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.julio.orcamento.modelo.Categoria;
import br.com.julio.orcamento.modelo.Orcamento;
import br.com.julio.orcamento.modelo.TipoOrcamento;
import br.com.julio.orcamento.repository.OrcamentoRepository;
import br.com.julio.orcamento.repository.TipoOrcamentoRepository;

public class AtualizarOrcamentoForm {
	
	
	private Long id;
	@NotEmpty @NotNull
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
	
	public String getCategoria() {
		return categoria;
	}
	
	public Orcamento converter(TipoOrcamentoRepository tipoOrcamentoRepository ,String nomeTipoOrcamento) {
		TipoOrcamento tipoOrcamento = tipoOrcamentoRepository.findBydescricao(nomeTipoOrcamento);
		if(this.categoria==null) {
			return new Orcamento(this.descricao, this.valor, this.data, tipoOrcamento);
		}
		return new Orcamento(this.descricao, this.valor, this.data, tipoOrcamento,new Categoria(this.categoria));
	}
	
	public Orcamento atualizar(Long id, OrcamentoRepository orcamentoRepository) {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(id);

        if(orcamento.isPresent()) {
        	orcamento.get().setDescricao(this.descricao);
        	orcamento.get().setValor(this.valor);
        	orcamento.get().setData(this.data);
        	if(this.categoria != null) {
        		orcamento.get().setCategoria(new Categoria(this.categoria));	
        	}
        	
            return orcamento.get();
        }
        return null;
	}
	public Long getId() {
		return id;
	}
	public boolean verificaOrcamentoNoMes(OrcamentoRepository orcamentoRepository, Long idParam, String nomeTipoOrcamento) {
		Date firstDay = Date.valueOf(this.getData().toLocalDate().with(TemporalAdjusters.firstDayOfMonth()));
		Date lastDay = Date.valueOf(this.getData().toLocalDate().with(TemporalAdjusters.lastDayOfMonth()));
		
		/*System.out.println("this.id = "+ this.id + " idParam = " + idParam + 
				" descricao = " + this.descricao + " primeiroDia = " + firstDay + " ultimoDia = " + lastDay + 
				" nomeTipoOrcamento = " + nomeTipoOrcamento);*/
		List<Orcamento> orcamento = orcamentoRepository
				.findByIdNotAndDescricaoAndDataBetweenAndTipoOrcamento_descricao(idParam,this.descricao, firstDay, lastDay,nomeTipoOrcamento);

		if (!orcamento.isEmpty()) {
			//System.out.println("true");
			return true;
		}
		//System.out.println("False");
		return false;
	}
	

}
