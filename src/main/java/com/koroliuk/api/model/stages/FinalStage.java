package com.koroliuk.api.model.stages;

import com.koroliuk.api.model.Journal;

public class FinalStage implements Stage {

  @Override
  public void workOut(Journal journal) {
    log.info("START FinalStage >_");
    journal.closeJournal();
    log.info("COMPLETE FinalStage >_");
  }

}
