package br.com.julio.orcamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.julio.orcamento.modelo.TipoOrcamento;

public interface TipoOrcamentoRepository extends JpaRepository<TipoOrcamento, Long> {

	TipoOrcamento findBydescricao(String nomeTipoOrcamento);


}
