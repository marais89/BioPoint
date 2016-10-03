package org.bio.service;

import java.util.List;

import org.bio.model.ExportOption;

public interface ExportOptionService {
	public void insertExportOption(ExportOption ExportOption);
	public void updateExportOption(ExportOption ExportOption);
	public List<ExportOption> findAllExportOptions();
	public void deleteExportOption(ExportOption ExportOption);
	public ExportOption getByid(int  id);
}
