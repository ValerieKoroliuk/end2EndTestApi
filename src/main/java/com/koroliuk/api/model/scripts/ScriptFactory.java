package com.koroliuk.api.model.scripts;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import com.koroliuk.api.model.Request;

@Component
public class ScriptFactory {

  public Script getScript(Request request) {
    switch (request.getScript()) {
      case GET_GEO:
        return new GetGeoScript(request.getScriptParams());
       default:
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "scripts enum required");
    }
  }

}
