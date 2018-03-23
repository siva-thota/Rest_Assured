package com.runnerCucumberJunit;

/**
 * The class is aimed at running Cucumber by Junit as well as generating a report in json format and html format
 */
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "html:target/cucumberHtmlReport", "json:target/cucumber-report.json" }, 
		features = "features", 
		glue = { "com.steps" },
		tags = {"@run"}
		)
public class RunnerCucumber {

}
