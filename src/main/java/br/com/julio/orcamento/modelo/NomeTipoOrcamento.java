package br.com.julio.orcamento.modelo;

public enum NomeTipoOrcamento {
	RECEITA("Receita"),
	DESPESA("Despesa");
	
	private String descricao;

	NomeTipoOrcamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
