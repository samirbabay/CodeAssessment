package pojos;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Country {

    //method created to return capital name of any country based on provided value

    public static String capitalCity(String country){
        RestAssured.baseURI = "https://restcountries.com/v3.1/name/";
        Response response=given().pathParam("name", country).
                when().get("{name}");
        response.then().
                assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        String getCapital = jsonPath.getString("capital[0]");
        String capital=getCapital.replace("[","").replaceAll("]","");
        return capital;
    }

    //method created to return country code of any country based on provided value

    public static String countryCode(String countryCode){
        RestAssured.baseURI = "https://restcountries.com/v3.1/name/";
        Response response=given().pathParam("name", countryCode).
                when().get("{name}");
        response.then().
                assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        String capital = jsonPath.getString("ccn3");
        return capital;
    }




    public static String region(String countryCode){
        RestAssured.baseURI = "https://restcountries.com/v3.1/region/";
        Response response=given().pathParam("name", countryCode).
                when().get("{name}");
        response.then().
                assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        String capital = jsonPath.getString("ccn3");
        return capital;
    }
}
