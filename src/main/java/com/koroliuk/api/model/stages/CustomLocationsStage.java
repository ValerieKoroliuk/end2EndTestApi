package com.koroliuk.api.model.stages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.responses.LocationsCustomResponse;
import com.koroliuk.client.GDSClient;

public class CustomLocationsStage implements Stage {

  private Map<String, String> scriptParam;
  private Stage stage;
  private GDSClient api;
  private List<String> requiredFields = Arrays.asList("locationIdArray");

  public CustomLocationsStage(Stage stage, GDSClient api, Map<String, String> scriptParam) {
    this.stage = stage;
    this.api = api;
    this.scriptParam = scriptParam;
  }

  @Override
  public void workOut(Journal journal) {
    log.info("START CustomLocationsStage >_");
    
    List<String> listOfInvalidFields = new ArrayList<>();
    listOfInvalidFields = isValidFields(scriptParam, requiredFields);

    if (!listOfInvalidFields.isEmpty()) {
      journal.addStageReport(fillFailedStageReport(listOfInvalidFields));
    } else {

      LocationsCustomResponse response = 
          api.locationsCustom(java.util.Arrays.asList(scriptParam.get("locationIdArray").split(",")), scriptParam.get("lang"), scriptParam.get("acceptLanguage"));

      scriptParam.putAll(response.getOutputParam());
      journal.addStageReport(response.getStageReport());

    }
    stage.workOut(journal);
  }

}
