package br.com.julio.orcamento.controller;

import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.julio.orcamento.controller.dto.ErroOrcamentoDto;
import br.com.julio.orcamento.controller.dto.OrcamentoDto;
import br.com.julio.orcamento.controller.form.AtualizarOrcamentoForm;
import br.com.julio.orcamento.controller.form.OrcamentoForm;
import br.com.julio.orcamento.modelo.NomeTipoOrcamento;
import br.com.julio.orcamento.modelo.Orcamento;
import br.com.julio.orcamento.repository.OrcamentoRepository;
import br.com.julio.orcamento.repository.TipoOrcamentoRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private OrcamentoRepository receitaRepository;
	@Autowired
	private TipoOrcamentoRepository tipoOrcamentoRepository;

	@GetMapping
	public List<OrcamentoDto> listaDescricao(@RequestParam(required = false) String descricao) {
		if (descricao == null) {
			List<Orcamento> orcamentos = receitaRepository
					.findByTipoOrcamento_descricao(NomeTipoOrcamento.RECEITA.getDescricao());
			return OrcamentoDto.converter(orcamentos);
		} else {
			List<Orcamento> orcamentos = receitaRepository.findByDescricaoAndTipoOrcamento_descricao(descricao,
					NomeTipoOrcamento.RECEITA.getDescricao());
			return OrcamentoDto.converter(orcamentos);
		}

	}

	/*@GetMapping
	public List<OrcamentoDto> lista() {

		List<Orcamento> orcamentos = receitaRepository
				.findByTipoOrcamento_descricao(NomeTipoOrcamento.RECEITA.getDescricao());
		return OrcamentoDto.converter(orcamentos);

	}*/

	@GetMapping("/{id}")
	public List<OrcamentoDto> buscaPorId(@PathVariable Long id) {
		List<Orcamento> orcamentos = receitaRepository.findByidAndTipoOrcamento_descricao(id,
				NomeTipoOrcamento.RECEITA.getDescricao());
		return OrcamentoDto.converter(orcamentos);
	}
	
	@GetMapping("/{ano}/{mes}")
	public List<OrcamentoDto> buscaPorAnoMes(@PathVariable int ano,@PathVariable int mes ) {
		LocalDate dia = LocalDate.of(ano, mes, 1);
		System.out.println(dia);
		Date firstDay = Date.valueOf(dia.with(TemporalAdjusters.firstDayOfMonth()));
		Date lastDay = Date.valueOf(dia.with(TemporalAdjusters.lastDayOfMonth()));

		List<Orcamento> orcamentos = receitaRepository.findByDataBetweenAndTipoOrcamento_descricao(firstDay,lastDay,
				NomeTipoOrcamento.RECEITA.getDescricao());
		return OrcamentoDto.converter(orcamentos);
	}

	@PostMapping
	public ResponseEntity<OrcamentoDto> cadastrarReceita(@RequestBody @Valid OrcamentoForm form,
			UriComponentsBuilder uriBuilder) {
		Orcamento orcamento = form.converter(tipoOrcamentoRepository, NomeTipoOrcamento.RECEITA.getDescricao());
		if (!form.verificaOrcamentoNoMes(receitaRepository, NomeTipoOrcamento.RECEITA.getDescricao())) {
			receitaRepository.save(orcamento);
			URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(orcamento.getId()).toUri();
			return ResponseEntity.created(uri).body(new OrcamentoDto(orcamento));
		}
		return ResponseEntity.badRequest()
				.body(new ErroOrcamentoDto(orcamento,
						"Já existe uma receita cadastrada com a mesma descrição dentro do mês de "
								+ orcamento.getData().toLocalDate().getMonthValue() + "/"
								+ orcamento.getData().toLocalDate().getYear()));
	}

	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<OrcamentoDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid AtualizarOrcamentoForm form, UriComponentsBuilder uriBuilder) {
		Orcamento orcamento = form.atualizar(id, receitaRepository);
		if (Objects.nonNull(orcamento)) {
			if (!form.verificaOrcamentoNoMes(receitaRepository, id, NomeTipoOrcamento.RECEITA.getDescricao())) {
				return ResponseEntity.ok(new OrcamentoDto(orcamento));
			}
			return ResponseEntity.badRequest()
					.body(new ErroOrcamentoDto(orcamento,
							"Já existe uma receita cadastrada com a mesma descrição dentro do mês de "
									+ orcamento.getData().toLocalDate().getMonthValue() + "/"
									+ orcamento.getData().toLocalDate().getYear()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	void deletarReceita(@PathVariable Long id) {
		receitaRepository.deleteByIdAndTipoOrcamento_descricao(id, NomeTipoOrcamento.RECEITA.getDescricao());
	}

}
