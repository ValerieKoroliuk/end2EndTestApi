package com.koroliuk.api.model;

import java.util.Map;
import com.koroliuk.api.model.scripts.ScriptName;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {

  @JsonProperty("script")
  private ScriptName script;

  @JsonProperty("script_params")
  private Map<String, String> scriptParams;

  public Map<String, String> getScriptParams() {
    return scriptParams;
  }

  public void setScriptParams(Map<String, String> scriptParams) {
    this.scriptParams = scriptParams;
  }

  public ScriptName getScript() {
    return script;
  }

  public void setScript(ScriptName script) {
    this.script = script;
  }

}
