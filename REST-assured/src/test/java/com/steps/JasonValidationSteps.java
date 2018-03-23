package com.steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JasonValidationSteps {
	
	private Response response;
	private RequestSpecification request;
	
	@Before
	public void setUp() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
			
	@When("^I input a valid postid \"([^\"]*)\" for comments$")
	public void i_input_a_valid_CEP(String commentNo) throws Throwable {
		RestAssured.basePath = "/comments";
		request = RestAssured.given();
		response = request.when().get("/" + commentNo);
		response.then().log().all();
	}

	@Then("^I should have the status code \"([^\"]*)\"$")
	public void i_should_have_the_status_code(String statusCode) throws Throwable {
		response.then().statusCode(Integer.parseInt(statusCode));
	}
		
	@Then("^the body response content should be matched$")
	public void the_body_response_content_should_be_matched(DataTable table) throws Throwable {

		List<List<String>> data = table.raw();
		for(int i = 1; i < data.size(); i++){
			response.then().assertThat().body(data.get(i).get(0), equalTo(data.get(i).get(1)));
		}	
	}
	
	@When("^I request to get the list of users$")
	public void i_request_to_get_the_list_of_users() throws Throwable {
		RestAssured.basePath = "/users";
		request = RestAssured.given();
		response = request.when().get();
		response.then().log().all();
	}

	@Then("^the body response content should have \"([^\"]*)\" users$")
	public void the_body_response_content_should_have_users(int NoOfRecords) throws Throwable {
		Assert.assertEquals(NoOfRecords,response.body().path("findall.size()"));
		
	}

}
