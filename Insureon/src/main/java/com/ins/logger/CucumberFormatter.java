package com.ins.logger;

import gherkin.formatter.PrettyFormatter;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.Tag;

import java.util.ArrayList;
import java.util.List;

import cucumber.runtime.formatter.ColorAware;
/**
 * Class for writing CAPI cucumber results to output. Extending PrettyFormatter
 * and implementing ColorAware to display the summary of passed and failed scenarios
 * to the output. 
 * 
 * NOTE: This class would be the default class for console output on CAPI cucumber
 * tests in order to display a summary of scenarios passed and failed
 *
 */
public class CucumberFormatter extends PrettyFormatter implements ColorAware {

  private final int LINES_NUMBER_FOR_BETWEEN_TEST_SUMARY = 1;
  
  /** Number of steps passed in the current scenario */
  int stepsPassed = 0;
  /** Number of steps failed in the current scenario */
  int stepsFailed = 0;
  /** Number of steps skipped in the current scenario */
  int stepsSkupped = 0;
  /** Number of scenarios passed in the current output */
  int scenariosPassed = 0;
  /** Number of scenarios failed in the current output */
  int scenariosFailed = 0;
  /** Number of scenarios executed for the current output */
  int scenariosTotal = 0;
  /** Name of the scenario just loaded to the output */
  String prevScenario = null;
  /** List of scenario names failed in the current output  */
  List<String> failedScenarios = new ArrayList<String>();
  /**
   * Constructor for the formatter using the passed Appendable
   * @param out The Appendable object to be used during the output
   */
  public CucumberFormatter(Appendable out) {
    super(out, false, true);
  }

  /**
   * Override the result method to count steps results after 
   * normal behavior
   */
  @Override
  public void result(Result result) {    
    LogUtil.logTrace("Result received: "+result);
    super.result(result);
    if (Result.PASSED.equalsIgnoreCase(result.getStatus())){
      stepsPassed++;
    }else if (Result.FAILED.equalsIgnoreCase(result.getStatus())){
      stepsFailed++;
    }else if (Result.SKIPPED.equals(result.getStatus())){
      stepsSkupped++;
    }else{
      stepsSkupped++;
    }
  }

  /**
   * Override scenario method to save the current scenario name loaded
   * and count results every time a new scenario is loaded.
   */
  @Override
  public void scenario(Scenario scenario) {
    LogUtil.logTrace("Scenario received: "+scenario);
    scenariosTotal++;
    //Check if there are results from previous scenarios to count
    checkAndCountResults();
    //Start the new Scenario
    prevScenario = getTagsString(scenario.getTags()) + scenario.getName();
    super.scenario(scenario);
  }
  
  /**
   * Reset the step counters for the current scenario. It will
   * set to 0 the counters for stepsPassed, stepsFailed and 
   * stepsSkipped
   */
  private void resetStepsCounters() {
    stepsPassed = 0;
    stepsFailed = 0;
    stepsSkupped = 0;
  }

  /**
   * Override done to output the test results summary after the
   * normal behavior of the formatter.
   */
  @Override
  public void done() {
    super.done();
    //Count last scenario
    checkAndCountResults();
    writeTestResults();
  }

  /**
   * Write system property line.separator using the write function
   * in order to print the desired number of empty lines
   * @param number The int number of empty lines
   */
  private void writeEmptyLines(int number){
    for(int i=0; i < number; i++){
      write(System.getProperty("line.separator"));
    }
  }
  
  /**
   * Writes the results summary for the output using the write method. It will
   * write the scenarios passed and failed message and the list of scenarios
   * name that were saved as failed.
   */
  private void writeTestResults() {
    writeEmptyLines(LINES_NUMBER_FOR_BETWEEN_TEST_SUMARY);
    write("***********************************************************************************************");
    //TODO bug present in counting sometimes one scenario passed is counted more.
    write(String.format("Tests complete. %s Scenarios executed. Scenarios Passed: %s. Scenarios Failed: %s",
        scenariosTotal, scenariosPassed, scenariosFailed));
    
    StringBuilder msg = new StringBuilder();
    //Form the failures message
    msg.append("Failures: ").append(System.getProperty("line.separator"));
    for(String scenario: failedScenarios){
      msg.append(scenario)
        .append(System.getProperty("line.separator"));
    }
    
    if (failedScenarios.size() > 0){
      write(msg.toString());
    }
    
    write("*************************************************************************************************");
    writeEmptyLines(LINES_NUMBER_FOR_BETWEEN_TEST_SUMARY);
  }

  /**
   * check if steps have been executed for the scenarios loaded and
   * counts the result of the scenario based on the steps results.
   * If any step has failed or has been skipped the scenario will be marked as
   * failure.
   */
  private void checkAndCountResults(){
  //Check if there are results from previous scenarios to count
    if (stepsPassed > 0 || stepsFailed > 0 || stepsSkupped > 0){
      if (stepsFailed > 0 || stepsSkupped > 0){
      //Errors on previous scenarios
        scenariosFailed++;
        failedScenarios.add(prevScenario);
      }else{
      //Previous Scenario Passed
        scenariosPassed++;
      }
    }
    resetStepsCounters();
  }
  
  @Override
  public void setMonochrome(boolean monochrome) {
    super.setMonochrome(monochrome);
  }
  
  /**
   * Builds a String with the name of the tags
   * passed on the list separated with a blank space
   * @param tags The List<Tag> of tags to get the names from
   * @return String containing tag's names
   */
  private String getTagsString(List<Tag> tags){
    StringBuilder builder = new StringBuilder();
    for (Tag tag : tags){
      builder.append(tag.getName()).append(" ");
    }
    return builder.toString();
  }
}