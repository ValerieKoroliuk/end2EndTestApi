package com.koroliuk.api.model.responses;

import java.util.HashMap;
import java.util.Map;
import com.koroliuk.api.model.stages.StageReport;

public class LocationsAutocompleteResponse {
  
  private StageReport stageReport;

  private Map<String, String> outputParam;

  public StageReport getStageReport() {
    return stageReport;
  }

  public Map<String, String> getOutputParam() {
    if (outputParam == null) {
      outputParam = new HashMap<>();
    }
    return outputParam;
  }

  public void setStageReport(StageReport stageReport) {
    this.stageReport = stageReport;
  }

  public void setOutputParam(Map<String, String> outputParam) {
    this.outputParam = outputParam;
  }

}
