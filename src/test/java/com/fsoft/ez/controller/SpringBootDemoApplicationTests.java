//package com.fsoft.ez.controller;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//public class SpringBootDemoApplicationTests {
//
//    @Test
//    public void test1() throws URISyntaxException {
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String baseUrl = "http://localhost:8080" + "/alive";
//        URI uri = new URI(baseUrl);
//
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//        // Verify request succeed
//        Assert.assertEquals(200, result.getStatusCodeValue());
//        System.out.println(result.getBody());
//    }
//
//    @Test
//    public void test2() throws URISyntaxException {
//        RestTemplate restTemplate = new RestTemplate();
//
//        final String baseUrl = "http://localhost:8080" + "/alive";
//        URI uri = new URI(baseUrl);
//
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//
//        // Verify request succeed
//        Assert.assertEquals(200, result.getStatusCodeValue());
//        System.out.println(result.getBody());
//    }
//}
