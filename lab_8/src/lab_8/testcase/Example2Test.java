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

import org.testng.Assert;

public class Example2Test {
	private int userId;
	private Response response;
    private ResponseBody resBody;
    private JsonPath bodyJson;  


	@BeforeClass
	
	public void init() {
		userId = 13;
		RestAssured.baseURI ="https://reqres.in/api";
		RestAssured.basePath="/users";
		RequestSpecification req = given().contentType(ContentType.JSON).when().pathParam("userId", userId);
		response = req.get("/{userId}");
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();
		
	}
	
    @Test
    public void T01_StatusCodeTest() {
        assertEquals( 404, response.getStatusCode(),"Status Check Success!");
    }
    @Test
    public void T02_MessageChecked() {
    	assertEquals(true, resBody.asString().contains("message"), "message field check Failed!");
    }
   
    @Test
    public void T03_MessageContentCheck() {
    	String message = bodyJson.getString("data.message");
    	if (message != null) {
    		assertEquals(message, "User not found!", "message check failed");			
		}else {
			Assert.fail();
		}
    }
    @AfterClass
    public void afterTest() {
        //Reset Values
        RestAssured.baseURI = null; //Setup Base URI
        RestAssured.basePath = null;
    }

}
