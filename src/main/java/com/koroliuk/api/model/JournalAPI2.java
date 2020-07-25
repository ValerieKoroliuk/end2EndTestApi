package com.koroliuk.api.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import com.koroliuk.api.model.scripts.ScriptResult;
import com.koroliuk.api.model.stages.StageReport;
import com.koroliuk.api.model.stages.StageResult;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JournalAPI2 implements Journal {

  @JsonProperty("name_of_script")
  private String nameOfScript;

  @JsonProperty("info_about_stages")
  private List<StageReport> infoAboutStages;

  @JsonProperty("result_of_tests")
  private ScriptResult resultOfTests;

  public JournalAPI2(String scriptName) {
    this.nameOfScript = scriptName;
  }

  @JsonIgnore
  @Override
  public void addStageReport(StageReport stageReport) {
    getInfoAboutStages().add(stageReport);
  }

  public String getNameOfScript() {
    return nameOfScript;
  }

  public List<StageReport> getInfoAboutStages() {
    if (infoAboutStages == null) {
      infoAboutStages = new LinkedList<>();
    }
    return infoAboutStages;
  }

  public ScriptResult getResultOfTests() {
    return resultOfTests;
  }

  public void setNameOfScript(String nameOfScript) {
    this.nameOfScript = nameOfScript;
  }

  public void setInfoAboutStages(List<StageReport> infoAboutStages) {
    this.infoAboutStages = infoAboutStages;
  }

  public void setResultOfTests(ScriptResult resultOfTests) {
    this.resultOfTests = resultOfTests;
  }

  @JsonIgnore
  @Override
  public void closeJournal() {
    resultOfTests = ScriptResult.SUCCESSFUL;

    for (StageReport stageReport : infoAboutStages) {
      if (Objects.equals(stageReport.getResult(), StageResult.FAILED)) {
        resultOfTests = ScriptResult.FAILED;
        break;
      }
    }
  }

}
