package com.kaizenflow.daymapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  @GetMapping("/test")
  public String getTest() {
    return "Hello World";
  }
}
