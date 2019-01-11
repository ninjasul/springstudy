package com.ninjasul.unittesting.controller;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

    private static String actualResponse = "{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}";

    @Test
    public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {
        String expectedResponse1 = "{\"id\" : 1, \"name\" : \"Ball\", \"price\" : 10, \"quantity\" : 100}";
        String expectedResponse2 = "{\"id\" : 1, \"name\" : \"Ball\"}";

        JSONAssert.assertEquals( expectedResponse1, actualResponse, true );
        JSONAssert.assertNotEquals( expectedResponse2, actualResponse, true );
    }

    @Test
    public void jsonAssert_StrictFalse() throws JSONException {
        String expectedResponse1 = "{\"id\" : 1}";
        String expectedResponse2 = "{\"id\" : 1, \"name\" : \"Ball\"}";
        String expectedResponse3 = "{\"id\" : 1, \"name\" : \"Ball\", \"price\" : 10}";
        String expectedResponse4 = "{\"id\" : 1, \"name\" : \"Ball\", \"price\" : 10, \"quantity\" : 100}";

        String expectedResponse5 = "{\"id\" : 2}";
        String expectedResponse6 = "{\"id\" : 1, \"name\" : \"Bale\"}";
        String expectedResponse7 = "{\"id\" : 1, \"name\" : \"Ball\", \"price\" : 11}";
        String expectedResponse8 = "{\"id\" : 1, \"name\" : \"Ball\", \"price\" : 10, \"quantity\" : 101}";

        JSONAssert.assertEquals( expectedResponse1, actualResponse, false );
        JSONAssert.assertEquals( expectedResponse2, actualResponse, false );
        JSONAssert.assertEquals( expectedResponse3, actualResponse, false );
        JSONAssert.assertEquals( expectedResponse4, actualResponse, false );

        JSONAssert.assertNotEquals( expectedResponse5, actualResponse, false );
        JSONAssert.assertNotEquals( expectedResponse6, actualResponse, false );
        JSONAssert.assertNotEquals( expectedResponse7, actualResponse, false );
        JSONAssert.assertNotEquals( expectedResponse8, actualResponse, false );
    }

    @Test
    public void jsonAssert_StrictFalse_WithoutEscapeCharacters() throws JSONException {
        String expectedResponse = "{id:1, name: Ball, price:10, quantity:100}";

        JSONAssert.assertEquals( expectedResponse, actualResponse, false );
    }
}