package com.automation.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/config/config.properties"})
public interface FrameworkConfig extends Config {

  String override_reports();

  boolean retry_failed_tests();

  int retry_count();

  String base_uri();
}
