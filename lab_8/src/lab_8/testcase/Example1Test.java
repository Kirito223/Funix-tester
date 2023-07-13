package lab_8.testcase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Example1Test {
	private int userId;
	private Response response;
    private ResponseBody resBody;
    private JsonPath bodyJson;  


	@BeforeClass
	
	public void init() {
		userId = 7;
		RestAssured.baseURI ="https://reqres.in/api";
		RestAssured.basePath="/users";
		RequestSpecification req = given().contentType(ContentType.JSON).when().pathParam("userId", userId);
		
		response = req.get("/{userId}");
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();
		
	}
	
    @Test
    public void T01_StatusCodeTest() {
        assertEquals( 200, response.getStatusCode(),"Status Check Success!");
    }
    @Test
    public void T02_IdChecked() {
    	assertTrue(resBody.asString().contains("id"), "id field check Failed!");
    }
    
    @Test
    public void T03_EmailChecked() {
    	assertTrue(resBody.asString().contains("email"), "email field check Failed!");
    }
    
    @Test
    public void T04_EmailChecked() {
    	assertTrue(resBody.asString().contains("first_name"), "first_name field check Failed!");
    	
    }
    
    @Test
    public void T05_EmailChecked() {
    	assertTrue(resBody.asString().contains("last_name"), "last_name field check Failed!");
    }
    @Test
    public void T06_AvatarChecked() {
    	assertTrue(resBody.asString().contains("avatar"), "avatar field check Failed!");
    }
    @Test
    public void T07_Compare_id() {
    	int id = bodyJson.getInt("data.id");
    	assertEquals(id, userId, "id isn't same userId");
    }
    @AfterClass
    public void afterTest() {
        //Reset Values
        RestAssured.baseURI = null; //Setup Base URI
        RestAssured.basePath = null;
    }

}
