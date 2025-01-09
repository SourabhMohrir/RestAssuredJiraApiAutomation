package Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import Pojo.Fields;
import Pojo.IssueType;
import Pojo.ProjectDetails;

public class BugTest {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://sourabhmohrir.atlassian.net/";
		
		String createIssueResponse =given()
		.header("Content-Type","application/json")
		.header("Authorization","Basic c291cmFiaG1vaHJpckBnbWFpbC5jb206QVRBVFQzeEZmR0YwdnIwTVZ5T3dhdklaYVhmeGhBelRmeFJTMS1tcXFsZDcyakdqcDBsWlV1MUJMVW1UeG05QUI3ZS1ENTNRMjFMN2MwbWd6YVRGWC1vTGFDNnpvSjJ4TEhFbjhfRlNuR0JTcFhzNWpJNFlUR0pXaHUtYUIyQmhfbnJ4cXZ5NkhyeVdpRXE5OXg0NFdRc2NfMUhLaEtSSG5DZGNyWW5rRDZnMVRWUk9ONTNzQkFJPTA2M0YxNTBE")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\":\r\n"
				+ "       {\r\n"
				+ "          \"key\": \"RJ\"\r\n"
				+ "       },\r\n"
				+ "       \"summary\": \"Testing the create issue api from rest assured with attachment\",\r\n"
				+ "       \"issuetype\": {\r\n"
				+ "          \"name\": \"Bug\"\r\n"
				+ "       }\r\n"
				+ "   }\r\n"
				+ "}").log().all()
		.post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		
		given()
		.pathParams("key",issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic c291cmFiaG1vaHJpckBnbWFpbC5jb206QVRBVFQzeEZmR0YwdnIwTVZ5T3dhdklaYVhmeGhBelRmeFJTMS1tcXFsZDcyakdqcDBsWlV1MUJMVW1UeG05QUI3ZS1ENTNRMjFMN2MwbWd6YVRGWC1vTGFDNnpvSjJ4TEhFbjhfRlNuR0JTcFhzNWpJNFlUR0pXaHUtYUIyQmhfbnJ4cXZ5NkhyeVdpRXE5OXg0NFdRc2NfMUhLaEtSSG5DZGNyWW5rRDZnMVRWUk9ONTNzQkFJPTA2M0YxNTBE")
		.multiPart("file",new File("C:\\Users\\mohrir\\eclipse-workspace\\JiraApiAutomation\\src\\Data\\Screenshot (478).png")).log().all()
		.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		

	}

}
