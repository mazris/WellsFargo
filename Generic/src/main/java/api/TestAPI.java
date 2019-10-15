package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAPI {
    /*
    There are following public apis
#	Route	        Method  Type	                    Full route	                                Description	Details
1	/employee	    GET	    JSON	http://dummy.restapiexample.com/api/v1/employees	    Get all employee data Details
2	/employee/{id}	GET	    JSON	http://dummy.restapiexample.com/api/v1/employee/{id}	Get a single employee data Details
3	/create	        POST	JSON	http://dummy.restapiexample.com/api/v1/create	        Create new record in database Details
4	/update/{id}	PUT	    JSON	http://dummy.restapiexample.com/api/v1/update/{id}	    Update an employee record Details
5	/delete/{id}	DELETE	JSON	http://dummy.restapiexample.com/api/v1/update/{id}	    Delete an employee record Details
*/
    private String base_URI= RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/";
    private String employees="employees";
    private String employeeWithID = "employee/";

    private String create = "create/";
    private String update = "update/";

    //Youtube tutorial
    @Test
    public void testGet(){
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/employee";
        RequestSpecification httpRequest= RestAssured.given();
        Response response=httpRequest.request(Method.GET,"/45113");
        //Extract body
        String responseBody=response.getBody().asString();
        System.out.println(responseBody);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void post(){
        RestAssured.baseURI="http://restapi.demoqa.com/customer";
        RequestSpecification httpRequest=RestAssured.given();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("FirstName","Ros2");
        jsonObject.put("LastName","Hamadi2");
        jsonObject.put("UserName","roh1232");
        jsonObject.put("Password","134562");
        jsonObject.put("Email","titm2072@gmail.com");
        httpRequest.header("Content_Type","application/json");
        httpRequest.body(jsonObject.toString());
        Response response= httpRequest.post("/register").then().extract().response();
        //Response response=httpRequest.post("/register");
        System.out.println("response body is "+response.body().asString());
        Assert.assertEquals(response.getStatusCode(),201);
        String successCode=response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode,"OPERATION_SUCCESS");

    }
    @Test
    public void getWithParameter(){
        RestAssured.baseURI="https://reqres.in";
        RequestSpecification request=RestAssured.given();
        Response response= request.get("/api/users?page=2").then().extract().response();
        //Response response= request.request(Method.GET,"/api/users?page=2");
        System.out.println(response.body().asString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");

    }
    //fin
    @Test
    public void testGetAllEmployeeY(){
        //eq in sql select * from employees
        RequestSpecification request=RestAssured.given();
        Response response = request.get(employees).then().extract().response();
        System.out.println(response.asString());
        // to get employees specific information(column)
        // eq in sql select employee_name from employees;
        JsonPath x = new JsonPath(response.asString());
        System.out.println("employee_name is : " + x.get("employee_name").toString());
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
    }
    @Test
    public void testGetAllEmployee(){
        //eq in sql select * from employees
        Response response = given().get(employees).then().extract().response();
        System.out.println(response.asString());
        // to get employees specific information(column)
        // eq in sql select employee_name from employees;
        JsonPath x = new JsonPath(response.asString());
        System.out.println("employee_name is : " + x.get("employee_name").toString());
        System.out.println("Response : " + response.asString());
        System.out.println("Status Code : " + response.getStatusCode());
    }
    @Test
    public void testGetSpecificEmployee() {
        //eq in sql select * from employees where employee_id = 19449
        RequestSpecification request = RestAssured.given();
        Response response = request.get(employeeWithID + 45241).then()
                .extract().response();
        System.out.println("Response : " + response.asString());
        Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void testPostCalls(){
        //creating the jsonObject first
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","4555632");
        jsonObject.put("employee_name","peopleNTech2");
        jsonObject.put("employee_salary","100000");
        jsonObject.put("employee_age","24");
        jsonObject.put("profile_image","");
        System.out.println(jsonObject);
        Response response= given().header("Content-Type", "application/json").body(jsonObject.toString()).when().post(create).then().statusCode(200).extract().response();
        System.out.println(response.asString());
    }
    @Test
    public void testPutCalls() {
        RequestSpecification request= RestAssured.given();
        String requestBody="{\r\n" +
                " \"name\":\"sonia\",\r\n" +
                " \"salary\":\"100000\",\r\n" +
                " \"age\":\"25\"\r\n" +
                "}";
        request.contentType(ContentType.JSON);
        request.body(requestBody);
        Response response= request.put(update+19449).then().extract().response();
        // Response response = request.contentType(ContentType.JSON).body(requestBody).put(update+19449);
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());

    }
    @Test
    public void changeName(){
        //get all the id
        //for each id change the name
        Response response = given().get(employees).then().extract().response();
        JsonPath x = new JsonPath(response.asString());


    }
    @Test
    public void testDeleteCalls(){
        RequestSpecification request= RestAssured.given();
        request.contentType(ContentType.JSON);
        Response response = request.delete("delete/19449");
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
    }

}
