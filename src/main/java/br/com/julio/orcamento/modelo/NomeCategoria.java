package br.com.julio.orcamento.modelo;

public enum NomeCategoria {
	ALIMENTACAO("Alimentação"),
	SAUDE("Saúde"),
	MORADIA("Moradia"),
	TRANSPORTE("Transporte"),
	EDUCACAO("Educação"),
	LAZER("Lazer"),
	IMPREVISTOS("Imprevistos"),
	OUTRAS("Outras");

	
	private String descricao;

	NomeCategoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
