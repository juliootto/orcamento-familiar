package br.com.julio.orcamento.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.julio.orcamento.controller.dto.ResumoDto;
import br.com.julio.orcamento.controller.dto.ValorPorCategoriaDto;
import br.com.julio.orcamento.modelo.NomeTipoOrcamento;
import br.com.julio.orcamento.repository.OrcamentoRepository;

@RestController
@RequestMapping("/resumo")
public class ResumoController {
	@Autowired
	private OrcamentoRepository orcamentoRepository;

	@GetMapping("/{ano}/{mes}")
	public ResumoDto lista(@PathVariable int ano, @PathVariable int mes) {
		LocalDate dia = LocalDate.of(ano, mes, 1);

		Date firstDay = Date.valueOf(dia.with(TemporalAdjusters.firstDayOfMonth()));
		Date lastDay = Date.valueOf(dia.with(TemporalAdjusters.lastDayOfMonth()));
		
		Optional<BigDecimal> valorTotalReceita = orcamentoRepository.somaOrcamentoMes(NomeTipoOrcamento.RECEITA.getDescricao(), firstDay, lastDay);
		Optional<BigDecimal> valorTotalDespesa = orcamentoRepository.somaOrcamentoMes(NomeTipoOrcamento.DESPESA.getDescricao(), firstDay, lastDay);
		List<ValorPorCategoriaDto> valorPorCategoria = orcamentoRepository.buscaValorPorCategoriaMes(NomeTipoOrcamento.DESPESA.getDescricao(), firstDay, lastDay); 
		
		return new ResumoDto(valorTotalReceita.orElse(BigDecimal.ZERO), valorTotalDespesa.orElse(BigDecimal.ZERO),valorPorCategoria);
	}



}
