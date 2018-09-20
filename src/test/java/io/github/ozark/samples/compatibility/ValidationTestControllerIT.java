package io.github.ozark.samples.compatibility;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

public class ValidationTestControllerIT {

    @Test
    public void testIndexPage() {
        Response response =
            when()
                    .get("/ozark-test/")
            .then()
                    .statusCode(200)
                    .extract()
                    .response();
        String html = response.asString();
        XmlPath doc = new XmlPath(XmlPath.CompatibilityMode.HTML, html);

        String title = doc.getString("html.head.title");
        assertEquals("Title not matched","Ozark Compatibility Check", title);

        String paragraph = doc.getString("html.body.p");
        assertEquals("Text not matched", "Ozark Compatibility Check (index.html)", paragraph);

        String anchor = doc.getString("html.body.a");
        assertEquals("Anchor not matched", "compatibility-check/validation-test?name=john", anchor);
    }

    @Test
    public void testValidationTestControllerResponse() {
        String name = "john";
        Response response =
                when()
                        .get("/ozark-test/compatibility-check/validation-test?name=" + name)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        String html = response.asString();
        XmlPath doc = new XmlPath(XmlPath.CompatibilityMode.HTML, html);

        String title = doc.getString("html.head.title");
        assertEquals("Title not matched","Validation Test", title);

        String paragraph = doc.getString("html.body.p");
        assertEquals("Text not matched", "Ozark Compatibility Check (validation.jsp)", paragraph);

        String h1 = doc.getString("html.body.h1");
        assertEquals("H1 not matched", "Hello " + name, h1);
    }
}
