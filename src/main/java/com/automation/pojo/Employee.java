package com.automation.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL) // Ignore fields with null value
@JsonPropertyOrder(alphabetic = true) // To alphabetically order the JSON
//@JsonIgnoreProperties(value = {"email"}) // To ignore the specified value
@Builder
public class Employee {

  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private List<String> jobs;
  private Skill skill;
}
