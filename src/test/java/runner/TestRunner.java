package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
//	    glue = {"com.bdd.stepdefinitions"},
	    glue = {"stepdefinitions", "hooks", "pages", "utils"},
	    plugin = {"pretty","html:target/report.html"}
	)

public class TestRunner extends AbstractTestNGCucumberTests {
}