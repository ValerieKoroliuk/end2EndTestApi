package com.koroliuk.api.model.scripts;

import java.util.LinkedHashMap;
import java.util.Map;

public enum ScriptName {

  GET_GEO(Script.getGeoConstansDescription());

  private Map<String, Object> scriptDescription;

  private ScriptName(Map<String, Object> scriptDescription) {
    this.scriptDescription = scriptDescription;
  }

  public Map<String, Object> getScriptDescription() {
    return scriptDescription;
  }

  // метод, который возвращает описание скриптов и перечень параметров для их запуска
  public static Map<String, Object> getScriptsInfo() {
    Map<String, Object> scriptsInfo = new LinkedHashMap<>();
    for (ScriptName element : ScriptName.values()) {
      scriptsInfo.put(element.toString(), element.getScriptDescription());
    }
    return scriptsInfo;
  }

}
