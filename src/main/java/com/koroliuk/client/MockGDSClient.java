package com.koroliuk.client;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.koroliuk.api.model.responses.LocationsAutocompleteResponse;
import com.koroliuk.api.model.responses.LocationsCustomResponse;
import com.koroliuk.api.model.responses.LocationsResponse;
import com.koroliuk.api.model.responses.LoginResponse;
import com.koroliuk.api.model.responses.PointsResponse;
import com.koroliuk.api.model.scripts.Script;
import com.koroliuk.api.model.stages.StageReport;
import com.koroliuk.api.model.stages.StageResult;


public class MockGDSClient implements GDSClient {

  static final Logger log = LoggerFactory.getLogger(MockGDSClient.class);
  
  @Override
  public LoginResponse login(String username, String password) {
    log.info("{host}​/login method in progress ...");
    LoginResponse loginResponse = new LoginResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the Login request that we send ...");
    report.setResponse("... here will be display the Login response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("LoginStage description");
    loginResponse.setStageReport(report);
    loginResponse.getOutputParam().put(Script.TOKEN, "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VybmFtZSIsImxhbmciOiJ1ayIsInR6IjoiVVRDIiwiaWF0IjoxNTQ4NTExMDg4LCJleHAiOjE2MzQ5MTEwODh9.tNzsorBJ_dTLiXsgX7dil1tZjmgwwCpnda8XHKBAqLy4LwKFv_IunfiuqH0rHFKHZ2V23uY5ZLeNxvT00t7Xog");
    return loginResponse;
  }

  @Override
  public LocationsAutocompleteResponse locationsAutocomplete(String query, Integer limit, String lang, String acceptLanguage) {
    log.info("{host}​/geo/autocomplete/locations method in progress ...");
    LocationsAutocompleteResponse autocompleteLocationsResponse = new LocationsAutocompleteResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the AutocompleteLocations request that we send ...");
    report.setResponse("... here will be display the AutocompleteLocations response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("AutocompleteLocationsStage description");
    autocompleteLocationsResponse.setStageReport(report);
    return autocompleteLocationsResponse;
  }

  @Override
  public LocationsCustomResponse locationsCustom(List<String> locationIdArray, String lang, String acceptLanguage) {
    log.info("{host}​​/geo​/custom​/locations method in progress ...");
    LocationsCustomResponse locationsCustomResponse = new LocationsCustomResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the LocationsCustomStage request that we send ...");
    report.setResponse("... here will be display the LocationsCustomStage response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("LocationsCustomStage description");
    locationsCustomResponse.setStageReport(report);
    return locationsCustomResponse;
  }

  @Override
  public LocationsResponse locations(String acceptLanguage, List<String> langArray, String timestamp, String limit) {
    log.info("{host}​​/geo​/locations method in progress ...");
    LocationsResponse locationsResponse = new LocationsResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the LocationsStage request that we send ...");
    report.setResponse("... here will be display the LocationsStage response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("LocationsStage description");
    locationsResponse.setStageReport(report);
    locationsResponse.getOutputParam().put(Script.LOCATIONS_PAGE_UUID, "d266ed9dd17e4ed08b14dd77179bfe03");
    locationsResponse.getOutputParam().put(Script.LOCATIONS_NUMBER_PAGE, "3");
    return locationsResponse;
  }

  @Override
  public LocationsResponse locationsPage(String pageUuid, Integer numberPage) {
    log.info("{host}​​/geo​/locations/page method in progress ...");
    LocationsResponse locationsPageResponse = new LocationsResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the LocationsPage request that we send ...");
    report.setResponse("... here will be display the LocationsPage response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("LocationsPage description");
    locationsPageResponse.setStageReport(report);
    return locationsPageResponse;
  }

  @Override
  public PointsResponse points(String acceptLanguage, List<String> langArray, String timestamp, String limit) {
    log.info("{host}​​/geo​/points method in progress ...");
    PointsResponse pointsResponse = new PointsResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the PointsStage request that we send ...");
    report.setResponse("... here will be display the PointsStage response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("PointsStage description");
    pointsResponse.setStageReport(report);
    pointsResponse.getOutputParam().put(Script.POINTS_PAGE_UUID, "dfc82912765e64949ec0d9ea0223a513");
    pointsResponse.getOutputParam().put(Script.POINTS_NUMBER_PAGE, "3");    
    return pointsResponse;
  }

  @Override
  public PointsResponse pointsPage(String pageUuid, Integer numberPage) {
    log.info("{host}​​/geo​/poins/page method in progress ...");
    PointsResponse pointsPageResponse = new PointsResponse();
    StageReport report = new StageReport();
    report.setRequest("... here will be display the PointsPageStage request that we send ...");
    report.setResponse("... here will be display the PointsPageStage response that we get ...");
    report.setResult(StageResult.PASSED);
    report.setDescription("PointsPageStage description");
    pointsPageResponse.setStageReport(report);
    return pointsPageResponse;
  }
 
}
