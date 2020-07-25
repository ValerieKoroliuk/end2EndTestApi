package com.koroliuk.api.model.stages;

import java.util.Map;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.responses.PointsResponse;
import com.koroliuk.client.GDSClient;

public class PointsStage implements Stage{
  
  private Map<String, String> scriptParam;
  private Stage stage;
  private GDSClient api;

  public PointsStage(Stage stage, GDSClient api, Map<String, String> scriptParam) {
    this.stage = stage;
    this.api = api;
    this.scriptParam = scriptParam;
  }

  @Override
  public void workOut(Journal journal) {
    log.info("START PointsStage >_");

    PointsResponse response = 
          api.points(scriptParam.get("acceptLanguage"), java.util.Arrays.asList(scriptParam.get("langArray").split(",")), scriptParam.get("timestamp"), scriptParam.get("limit"));
      
      scriptParam.putAll(response.getOutputParam());
      journal.addStageReport(response.getStageReport());

    stage.workOut(journal);
  }

}
