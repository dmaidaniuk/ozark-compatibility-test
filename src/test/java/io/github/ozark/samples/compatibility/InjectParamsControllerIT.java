package io.github.ozark.samples.compatibility;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

public class InjectParamsControllerIT {

    @Test
    public void testValidationTestControllerResponse() {
        String fieldValue = "test";
        Response response =
                when()
                        .get("/ozark-test/compatibility-check/inject/field?fieldValue=" + fieldValue)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String html = response.asString();
        XmlPath doc = new XmlPath(XmlPath.CompatibilityMode.HTML, html);

        String title = doc.getString("html.head.title");
        assertEquals("Title not matched","Inject Params Test", title);

        String paragraph = doc.getString("html.body.p");
        assertEquals("Text not matched", "Ozark Compatibility Check (params.jsp)", paragraph);

        String h1 = doc.getString("html.body.h1");
        assertEquals("H1 not matched", fieldValue, h1);
    }
}
