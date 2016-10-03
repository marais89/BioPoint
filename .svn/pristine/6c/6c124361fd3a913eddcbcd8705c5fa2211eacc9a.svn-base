package org.bio.service;

import java.io.Serializable;
import java.util.List;

import org.bio.dao.ExportOptionDao;
import org.bio.model.ExportOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ExportOptionServiceImpl implements ExportOptionService,Serializable {
@Autowired
	private ExportOptionDao exportdao;

	public ExportOptionDao getExportdao() {
		return exportdao;
	}

	public void setExportdao(ExportOptionDao exportdao) {
		this.exportdao = exportdao;
	}

	@Override
	@Transactional
	public void insertExportOption(ExportOption ExportOption) {
		// TODO Auto-generated method stub
		
		exportdao.insertExportOption(ExportOption);
	}

	@Override
	public void updateExportOption(ExportOption ExportOption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExportOption> findAllExportOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExportOption(ExportOption ExportOption) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ExportOption getByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
