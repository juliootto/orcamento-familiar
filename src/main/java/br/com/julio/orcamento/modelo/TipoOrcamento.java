package br.com.julio.orcamento.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.julio.orcamento.util.JPAUtil;

@Entity
@Table(name = "tipo_orcamento")
public class TipoOrcamento {
	// public static final long RECEITA = 1l;
	// public static final long DESPESA = 2l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_orcamento")
	private long idTipoOrcamento;
	private String descricao;

	public TipoOrcamento() {
	}

	public TipoOrcamento(String descricao) {
		this.descricao = descricao;
		EntityManager em = JPAUtil.getEntityManager();
		String selectionQuery = "SELECT idTipoOrcamento FROM TipoOrcamento WHERE descricao = :descricao";
		this.idTipoOrcamento = (long) em.createQuery(selectionQuery).setParameter("descricao", descricao)
				.getSingleResult(); // This should be placed in a try/catch block to handle
									// org.hibernate.NonUniqueResultException
	}

	public long getId_tipo_orcamento() {
		return idTipoOrcamento;
	}

	public String getDescricao() {
		return descricao;
	}

}
