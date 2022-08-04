package br.com.julio.orcamento.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import br.com.julio.orcamento.modelo.Orcamento;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

	// List<Orcamento> findByidTipoOrcamento(TipoOrcamento tipoOrcamento);

	List<Orcamento> findByid(Long id);

	List<Orcamento> findByTipoOrcamento_descricao(String string);

	List<Orcamento> findByidAndTipoOrcamento_descricao(Long id, String tipoOrcamentoDescricao);

	List<Orcamento> findBydescricaoAndTipoOrcamento_descricao(String descricao, String tipoOrcamentoDescricao);
	
	@Transactional
	void deleteByIdAndTipoOrcamento_descricao(Long id, String string);

	Optional<Orcamento> findByDescricaoAndDataBetweenAndTipoOrcamento_descricao(@NotNull @NotEmpty String descricao,
			Date firstDay, Date lastDay, String tipoOrcamentoDescricao);

	List<Orcamento> findByIdNotAndDescricaoAndDataBetweenAndTipoOrcamento_descricao(@NotEmpty @NotNull Long id,
			@NotEmpty @NotNull String descricao, Date firstDay, Date lastDay, String nomeTipoOrcamento);

}
