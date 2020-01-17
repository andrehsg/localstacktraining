package com.daitan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class MessageResponse {

  @Length(max = 300)
  private String content;

  public MessageResponse() {
    // Jackson deserialization
  }

  public MessageResponse(String content) {
    this.content = content;
  }

  @JsonProperty
  public String getContent() {
    return content;
  }
}
