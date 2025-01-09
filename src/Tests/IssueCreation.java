package Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import Data.Headers;
import Data.Payload;
import Data.Utils;
import Pojo.Fields;
import Pojo.IssueType;
import Pojo.ProjectDetails;


public class IssueCreation {
	
	RequestSpecification req;
	Response response;
	String issueId;
	Utils utils = new Utils();
	
	  @Test
	  public void issueCreationTest() {
		  try {
			req = given().log().all().spec(utils.requestSpecificationCreateIssue()).body(Payload.issuePayload());
		} catch (IOException e) {
			e.printStackTrace();
		}
		  try {
			response = req.log().all().post(Utils.getGlobalValue("createIssue")).then().log().all()
					              .assertThat().statusCode(201).extract().response();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		  
		issueId = utils.getJsonPath(response, "id");
	  }
	  
	  @Test(dependsOnMethods = "issueCreationTest")
	  public void addAttachment() {
		  try {
			req = given().log().all().spec(utils.requestSpecificationAddAttachment(issueId)).multiPart("file", Payload.attachmentFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		  try {
			response = req.log().all().post(Utils.getGlobalValue("addAttachment")).then().assertThat()
					     .statusCode(200).extract().response();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  
	  @Test(dependsOnMethods = "addAttachment")
	  public void deleteIssue() {
		  
		  try {
			req = given().log().all().spec(utils.requestSpecificationDeleteIssue(issueId));
		} catch (IOException e) {
			e.printStackTrace();
		}
		  try {
			response = req.log().all().delete(Utils.getGlobalValue("deleteIssue")).then().assertThat()
					     .statusCode(204).extract().response();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }
	  }
