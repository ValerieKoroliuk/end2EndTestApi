package com.koroliuk.api.model.stages;

import java.util.Map;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.responses.LocationsResponse;
import com.koroliuk.client.GDSClient;

public class LocationsStage implements Stage {

  private Map<String, String> scriptParam;
  private Stage stage;
  private GDSClient api;

  public LocationsStage(Stage stage, GDSClient api, Map<String, String> scriptParam) {
    this.stage = stage;
    this.api = api;
    this.scriptParam = scriptParam;
  }

  @Override
  public void workOut(Journal journal) {
    log.info("START LocationsStage >_");
    
    LocationsResponse response =
        api.locations(scriptParam.get("acceptLanguage"), java.util.Arrays.asList(scriptParam.get("langArray").split(",")), scriptParam.get("timestamp"), scriptParam.get("limit"));
   
    scriptParam.putAll(response.getOutputParam());
    journal.addStageReport(response.getStageReport());

    stage.workOut(journal);
  }

}
