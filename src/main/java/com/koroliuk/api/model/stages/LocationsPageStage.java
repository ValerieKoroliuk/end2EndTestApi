package com.koroliuk.api.model.stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.responses.LocationsResponse;
import com.koroliuk.client.GDSClient;

public class LocationsPageStage implements Stage {

  private Map<String, String> scriptParam;
  private Stage stage;
  private GDSClient api;
  private List<String> requiredFields = Arrays.asList("locationsPageUuid", "locationsNumberPage");

  public LocationsPageStage(Stage stage, GDSClient api, Map<String, String> scriptParam) {
    this.stage = stage;
    this.api = api;
    this.scriptParam = scriptParam;
  }

  @Override
  public void workOut(Journal journal) {    
    log.info("START LocationsPageStage >_");

    List<String> listOfInvalidFields = new ArrayList<>();
    listOfInvalidFields = isValidFields(scriptParam, requiredFields);

    if (!listOfInvalidFields.isEmpty()) {
      journal.addStageReport(fillFailedStageReport(listOfInvalidFields));
    } else {
      LocationsResponse response =
          api.locationsPage(scriptParam.get("locationsPageUuid"), Integer.valueOf(scriptParam.get("locationsNumberPage")));

      scriptParam.putAll(response.getOutputParam());
      journal.addStageReport(response.getStageReport());
    }

    stage.workOut(journal);
  }

}
