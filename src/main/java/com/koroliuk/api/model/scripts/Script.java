package com.koroliuk.api.model.scripts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.koroliuk.api.model.Journal;

public interface Script {
  
  void run(Journal journal);
  
  public static final String ACCEPT_LANGUAGE = "acceptLanguage";
  public static final String LANG = "lang";
  public static final String PASSWORD = "password";
  public static final String TIMEZONE = "timeZone";
  public static final String USERNAME = "username";
  public static final String QUERY = "query";
  public static final String LIMIT = "limit";
  public static final String LOCATION_ID_ARRAY = "locationIdArray";
  public static final String LANG_ARRAY = "langArray";
  public static final String TIMESTAMP = "timestamp";
  public static final String LOCATIONS_PAGE_UUID = "locationsPageUuid";
  public static final String LOCATIONS_NUMBER_PAGE = "locationsNumberPage";
  public static final String POINTS_PAGE_UUID = "pointsPageUuid";
  public static final String POINTS_NUMBER_PAGE = "pointsNumberPage";
  public static final String TOKEN = "token";

  public static final String API2_GEO_HOST = "gdsGeoApiHost";
  public static final String API2_SALE_HOST = "gdsSaleApiHost";
  public static final String API2_SEARCH_HOST = "gdsSearchApiHost";
  

  public static Map<String, Object> getGeoConstansDescription() {    

    Map<String, String> getGeoScriptParams = new LinkedHashMap<>();
    Map<String, Object> geoScriptDescription = new LinkedHashMap<>();

    geoScriptDescription.put("Description", "Получение Географии: НП по названию/списку идентификаторов, списка географических объектов и остановок.");
    geoScriptDescription.put("Script Params", getGeoScriptParams);
    
    getGeoScriptParams.put(API2_GEO_HOST, "REQUIRED. Хост сервера получения географии.");    
    getGeoScriptParams.put(PASSWORD, "REQUIRED. Пароль");
    getGeoScriptParams.put(USERNAME, "REQUIRED. Имя пользователя");
    getGeoScriptParams.put(QUERY, "REQUIRED. Шаблон поиска по вхождению");
    getGeoScriptParams.put(LIMIT, "REQUIRED. Лимит елементов в ответе");
    getGeoScriptParams.put(LOCATION_ID_ARRAY, "REQUIRED. Список Ид НП. Разделителем Ид является ',' (запятая).");
    
    getGeoScriptParams.put(ACCEPT_LANGUAGE, "Локаль на которой будет сформирован ответ, формат ISO 639-1. " 
        + "Если переданная локаль не поддерживается, то ответ будет сформирован на дефолтной локале.");
    getGeoScriptParams.put(LANG, "Язык возвращаемых данных, формат ISO 639-1. " + "Если переданная локаль не поддерживается, то ответ будет сформирован на дефолтной локале. "
        + "Если параметр задан, то значение в Accept-Language игнорируется.");
    getGeoScriptParams.put(TIMEZONE, "Часовой пояс в формате <Region>/<City>. Даты операций отображаются с учетом указанного пояса.");
    getGeoScriptParams.put(LANG_ARRAY, "Список поддерживаемых локалей на которых будет сформирован ответ, формат ISO 639-1. "
        + "Если какая-то из переданных локалей не поддерживается, то вместо нее ответ будет сформирован на дефолтной локале. " 
          + "Если параметр задан, то значение в Accept-Language игнорируется.");
    getGeoScriptParams.put(TIMESTAMP, "Если параметр задан, то метод вернет только созданные и измененные данные, после даты указанной в параметре. "
        + "Если параметр не задан, то метод вернет полный набор данных Формат: date-time - RFC3339");
    
    return geoScriptDescription;
  }

  default List<String> isValidFields(Map<String, String> scriptParam, List<String> fields) {
    List<String> invalidFilds = new ArrayList<>();
    for (String field : fields) {
      if (scriptParam.get(field).isEmpty()) {
        invalidFilds.add(field);
      }
    }
    return invalidFilds;
  }
  
}
