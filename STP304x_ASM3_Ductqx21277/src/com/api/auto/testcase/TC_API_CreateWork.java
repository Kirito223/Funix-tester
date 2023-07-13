package com.api.auto.testcase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreateWork {
	private String account;
	private String password;
	private Response response;
	private ResponseBody responseBody;
	private JsonPath jsonBody;
	private Map<String, Object> body;
	@BeforeClass
	public void init() throws IOException {
		String baseUrl = PropertiesFileUtils.getProperty("baseurl");
		account = PropertiesFileUtils.getProperty("account");
		password = PropertiesFileUtils.getProperty("password");
		String createWorkPath = PropertiesFileUtils.getProperty("createWorkPath");
		String token = PropertiesFileUtils.getToken("token");
		RestAssured.baseURI = baseUrl;
		
		body = new HashMap<String, Object>();
		body.put("nameWork", "Mobile Dev");
		body.put("experience", "1 năm");
		body.put("education", "Đại học");
		RequestSpecification req = RestAssured.given().header("token", token).contentType(ContentType.JSON).body(body);
		response = req.post(createWorkPath);
		responseBody = response.body();
		jsonBody = response.jsonPath();
	}

	@Test(priority = 0)
	public void TC01_Validate201Created() {
		assertEquals(response.getStatusCode(), 201, "Status is not 201");

	}

	@Test(priority = 1)
	public void TC02_ValidateWorkId() {
		assertTrue(responseBody.asString().contains("id"));
	}

	@Test(priority = 2)
	public void TC03_ValidateNameOfWorkMatched() {
		
		String nameWork = jsonBody.getString("nameWork");
		assertEquals(nameWork, body.get("nameWork"), "name work not matched");

	}

	@Test(priority = 3)
	public void TC03_ValidateExperienceMatched() {
		String experience = jsonBody.getString("experience");
		assertEquals(experience, body.get("experience"), "experience not matched");
	}

	@Test(priority = 4)
	public void TC03_ValidateEducationMatched() {
		String education = jsonBody.getString("education");
		assertEquals(education, body.get("education"), "education not matched");
	}

}
