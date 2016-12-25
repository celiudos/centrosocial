package org.iaff.csiaff.repository.helper.pessoa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.iaff.csiaff.model.Pessoa;
import org.iaff.csiaff.repository.filter.PessoaFilter;
import org.iaff.csiaff.repository.paginacao.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class PessoasImpl implements PessoasQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Pessoa> filtrar(PessoaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pessoa.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("endereco.cidade", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("c.estado", "e", JoinType.LEFT_OUTER_JOIN);		
				
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(PessoaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Pessoa.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(PessoaFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filtro.getNumeroDocumento())) {
				criteria.add(Restrictions.eq("numeroDocumento", filtro.getNumeroDocumentoSemFormatacao()));
			}
		}
	}

}
