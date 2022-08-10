package br.com.julio.orcamento.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import br.com.julio.orcamento.controller.dto.ValorPorCategoriaDto;
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

	List<Orcamento> findByDescricaoAndTipoOrcamento_descricao(String descricao, String tipoOrcamentoDescricao);

	List<Orcamento> findByDataBetweenAndTipoOrcamento_descricao(Date firstDay, Date lastDay, String nomeTipoOrcamento);

	@Query("SELECT SUM(o.valor) " + "FROM Orcamento o JOIN  o.tipoOrcamento tp "
			+ "WHERE tp.descricao = :descricaoTipo " + " AND o.data BETWEEN :firstDay AND :lastDay")
	Optional<BigDecimal> somaOrcamentoMes(@Param("descricaoTipo") String descricaoTipo,
			@Param("firstDay") Date firstDay, @Param("lastDay") Date lastDay);

	@Query("SELECT New br.com.julio.orcamento.controller.dto.ValorPorCategoriaDto( c.descricao,SUM(o.valor) ) "
			+ "FROM Orcamento o JOIN  o.tipoOrcamento tp JOIN o.categoria c  "
			+ "WHERE tp.descricao = :descricaoTipo "
			+ " AND o.data BETWEEN :firstDay AND :lastDay GROUP BY c.descricao")
	List<ValorPorCategoriaDto> buscaValorPorCategoriaMes(@Param("descricaoTipo") String descricaoTipo,
			@Param("firstDay") Date firstDay, @Param("lastDay") Date lastDay);

}
