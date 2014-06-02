package com.john_na.main.service;

import com.john_na.common.entity.RecordInProgressVo;

public interface IMainService {

	public RecordInProgressVo getRecordInProgress() throws Exception;
	public String start(RecordInProgressVo vo) throws Exception;
	public String update(RecordInProgressVo vo) throws Exception;
	public String finish(RecordInProgressVo vo) throws Exception;
}
