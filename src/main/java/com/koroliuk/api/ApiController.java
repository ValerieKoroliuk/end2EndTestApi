package com.koroliuk.api;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.koroliuk.api.model.Journal;
import com.koroliuk.api.model.JournalAPI2;
import com.koroliuk.api.model.Request;
import com.koroliuk.api.model.scripts.Script;
import com.koroliuk.api.model.scripts.ScriptFactory;
import com.koroliuk.api.model.scripts.ScriptName;

@RestController
public class ApiController {

  private final ScriptFactory factory;
  
  @Autowired
  public ApiController(ScriptFactory factory) {
    this.factory = factory;
  }
  
  @PostMapping("/tests/scripts")
  public ResponseEntity<Journal> processScript(@RequestBody Request request){
    
    if(request.getScript() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "scripts enum required");
    }
    
    Journal journal = new JournalAPI2(request.getScript().toString());
    
    Script script = factory.getScript(request);
    script.run(journal);
    
    return ResponseEntity.ok(journal);
  }
  
  /**
   * Reference method
   * @return list declared fields.
   */
  @GetMapping("/tests/references")
  public ResponseEntity<Map<String, Object>> getReference (){
     return ResponseEntity.ok(ScriptName.getScriptsInfo());
  }
  
}