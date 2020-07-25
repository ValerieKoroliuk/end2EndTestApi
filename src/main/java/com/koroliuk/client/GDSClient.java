package com.koroliuk.client;

import java.util.List;
import com.koroliuk.api.model.responses.LocationsAutocompleteResponse;
import com.koroliuk.api.model.responses.LocationsCustomResponse;
import com.koroliuk.api.model.responses.LocationsResponse;
import com.koroliuk.api.model.responses.LoginResponse;
import com.koroliuk.api.model.responses.PointsResponse;

public interface GDSClient {

  LoginResponse login(String username, String password);
  
  LocationsAutocompleteResponse locationsAutocomplete(String query, Integer limit, String lang, String acceptLanguage);

  LocationsCustomResponse locationsCustom(List<String> locationIdArray, String lang, String acceptLanguage);

  LocationsResponse locations(String acceptLanguage, List<String> langArray, String timestamp, String limit);

  LocationsResponse locationsPage(String pageUuid, Integer numberPage);

  PointsResponse points(String acceptLanguage, List<String> langArray, String timestamp, String limit);

  PointsResponse pointsPage(String pageUuid, Integer numberPage);
}
