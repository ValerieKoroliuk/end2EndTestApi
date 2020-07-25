package com.koroliuk.api.model.stages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.koroliuk.api.model.Journal;

public interface Stage {
  
  static final Logger log = LoggerFactory.getLogger(Stage.class);

  void workOut(Journal journal);

  default StageReport fillFailedStageReport(List<String> listOfInvalidFields) {
    StageReport report = new StageReport();
    report.setRequest(null);
    report.setResponse(null);
    report.setResult(StageResult.FAILED);
    report.setDescription("fields: " + listOfInvalidFields.toString() + " must be not null");
    return report;
  }

  default List<String> isValidFields(Map<String, String> scriptParam, List<String> fields) {
    List<String> invalidFilds = new ArrayList<>();
    for (String field : fields) {
      if (StringUtils.isEmpty(scriptParam.get(field))) {
        invalidFilds.add(field);
      }
    }
    return invalidFilds;
  }

}
