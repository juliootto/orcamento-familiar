package br.com.julio.orcamento.modelo;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orcamento")

public class Orcamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orcamento")
	private long id;
	private String descricao;
	private BigDecimal valor;
	private Date data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_orcamento")
	private TipoOrcamento tipoOrcamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	public Orcamento() {

	}

	public Orcamento(String descricao, BigDecimal valor, Date data, TipoOrcamento id_tipo_orcamento, Categoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.tipoOrcamento = id_tipo_orcamento;
		this.categoria = categoria;

	}
	
	public Orcamento(String descricao, BigDecimal valor, Date data, TipoOrcamento id_tipo_orcamento) {
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.tipoOrcamento = id_tipo_orcamento;
		//System.out.println("tipoOrcamento: " + id_tipo_orcamento.getDescricao() + " tipo esperado: "+NomeTipoOrcamento.DESPESA.getDescricao() + " " + NomeCategoria.OUTRAS.getDescricao());
		if(id_tipo_orcamento.getDescricao().equals(NomeTipoOrcamento.DESPESA.getDescricao())) {
			this.categoria = new Categoria(NomeCategoria.OUTRAS.getDescricao());
		}
		
	}

	public long getId() {
		return id;
	}

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
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
