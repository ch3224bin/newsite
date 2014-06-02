package com.john_na.main.service.impl;

import javax.inject.Inject;

import org.anyframe.query.QueryService;
import org.anyframe.query.dao.AbstractDao;
import org.springframework.stereotype.Repository;

import com.john_na.common.entity.RecordInProgressVo;

@Repository
public class MainDao extends AbstractDao {
	
	@Inject
	public void setQueryService(QueryService queryService) {
		super.setQueryService(queryService);
	}

	public RecordInProgressVo findRecordInProgressByPk(RecordInProgressVo vo) throws Exception {
		return (RecordInProgressVo) findByPk("RecordInProgress", vo);
	}
	public void createRecordInProgress(RecordInProgressVo vo) throws Exception {
		create("RecordInProgress", vo);
	}
	public void updateRecordInProgress(RecordInProgressVo vo) throws Exception {
		update("RecordInProgress", vo);
	}
	public void removeRecordInProgress(RecordInProgressVo vo) throws Exception {
		remove("RecordInProgress", vo);
	}
}
