package testScripts;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Country;
import static io.restassured.RestAssured.given;

public class TestCase {


    Country country = new Country();

    @Test
    public void getCountryCode() {

        //positive scenario
        //base on input of Country
        //verify code
        String codeOfCountry = country.countryCode(("Italy"));
        System.out.println("Code is:" + codeOfCountry);

    }

    //by passing country name in string parameter
    //assert that if it equals to expected result

    @Test
    public void getCapitalCityPositive() {

        String capital= country.capitalCity(("Portugal"));
        Assert.assertEquals("Lisbon",capital);
        System.out.println("Capital is:" + capital);
    }

    //Verify negative scenario
    //User enters path param as Integer
    //expected result is status code 404

    @Test
    public void getCapitalCityNegative() {

        RestAssured.baseURI = "https://restcountries.com/v3.1/name/";
        Response response = given().pathParam("name", "898").
                when().get("{name}");
        response.prettyPeek().then().
                assertThat().statusCode(404);
    }


    @Test
    public void getEuropeRegionsCountry() {

        //Verifying if given country, whether included in europe regions or not

        RestAssured.baseURI = "https://restcountries.com/v3.1/region/";
        Response response = given().queryParam("europe").
                when().get("europe");
        response.then().
                assertThat().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        String getCountry = jsonPath.getString("name.common");
        System.out.println("All countries "+getCountry);

        if(getCountry.contains("Denmark")){

            System.out.println("Included in europe region");
        }
        else{
            System.out.println("Not included in europe region");
        }
        Assert.assertTrue(getCountry.contains("Denmark"));

    }
}