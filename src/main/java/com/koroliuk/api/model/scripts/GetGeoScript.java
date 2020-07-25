package com.koroliuk.api.model.scripts;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.stages.AutocompleteLocationsStage;
import com.koroliuk.api.model.stages.FinalStage;
import com.koroliuk.api.model.stages.LocationsPageStage;
import com.koroliuk.api.model.stages.LocationsStage;
import com.koroliuk.api.model.stages.CustomLocationsStage;
import com.koroliuk.api.model.stages.LoginStage;
import com.koroliuk.api.model.stages.PointsPageStage;
import com.koroliuk.api.model.stages.PointsStage;
import com.koroliuk.api.model.stages.Stage;
import com.koroliuk.client.GDSClient;
import com.koroliuk.client.MockGDSClient;

public class GetGeoScript implements Script {

  static final Logger log = LoggerFactory.getLogger(GetGeoScript.class);
  
  private Map<String, String> scriptParam = new HashMap<>();
  private GDSClient api;
  private Stage stage;
  
  private List<String> requiredFields = Arrays.asList("username", "password", "host");

  public GetGeoScript(Map<String, String> scriptParam) {
    
    log.info("START GET GEO Script ---------------------------------------------------------------------------");
    
    if (scriptParam != null && !CollectionUtils.isEmpty(isValidFields(scriptParam, requiredFields))) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "host, username and password must be not empty");
    }
    
    this.api = new MockGDSClient(/* {host} */);
    this.scriptParam = scriptParam;

    stage = new LoginStage(new AutocompleteLocationsStage(
                new CustomLocationsStage(
                    new LocationsStage(
                        new LocationsPageStage(
                            new PointsStage(
                                new PointsPageStage(
                                    new FinalStage(), api, scriptParam), api, scriptParam), api, scriptParam), api, scriptParam), api, scriptParam),api, scriptParam), api, scriptParam);
    
  }

  @Override
  public void run(Journal journal) {
    stage.workOut(journal);
  }

  public Map<String, String> getScriptParam() {
    return scriptParam;
  }

  public void setScriptParam(Map<String, String> scriptParam) {
    this.scriptParam = scriptParam;
  }

}
