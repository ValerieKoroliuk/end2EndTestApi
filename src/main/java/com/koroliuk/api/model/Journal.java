package com.koroliuk.api.model;

import com.koroliuk.api.model.stages.StageReport;

public interface Journal {
  
  void addStageReport(StageReport stageReport);
  
  void closeJournal();
}
