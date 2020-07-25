package com.koroliuk.api.model.stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.responses.PointsResponse;
import com.koroliuk.client.GDSClient;

public class PointsPageStage implements Stage {

  private Map<String, String> scriptParam;
  private Stage stage;
  private GDSClient api;
  private List<String> requiredFields = Arrays.asList("pointsPageUuid", "pointsNumberPage");

  public PointsPageStage(Stage stage, GDSClient api, Map<String, String> scriptParam) {
    this.stage = stage;
    this.api = api;
    this.scriptParam = scriptParam;
  }

  @Override
  public void workOut(Journal journal) {
    log.info("START PointsPageStage >_");
   
    List<String> listOfInvalidFields = new ArrayList<>();
    listOfInvalidFields = isValidFields(scriptParam, requiredFields);

    if (!listOfInvalidFields.isEmpty()) {
      journal.addStageReport(fillFailedStageReport(listOfInvalidFields));
    } else {
      
      PointsResponse response = 
          api.pointsPage(scriptParam.get("pointsPageUuid"), Integer.valueOf(scriptParam.get("pointsNumberPage")));

      scriptParam.putAll(response.getOutputParam());
      journal.addStageReport(response.getStageReport());

    }
    stage.workOut(journal);
  }

}
