package com.koroliuk.api.model.stages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StageReport {

  @JsonProperty("request")
  private String request;

  @JsonProperty("response")
  private String response;

  @JsonProperty("resul_of_method_work")
  private StageResult result;

  @JsonProperty("description")
  private String description;

  public String getRequest() {
    return request;
  }

  public String getResponse() {
    return response;
  }

  public StageResult getResult() {
    return result;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public void setResult(StageResult result) {
    this.result = result;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
