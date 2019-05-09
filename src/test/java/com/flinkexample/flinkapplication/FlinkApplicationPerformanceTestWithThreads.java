package com.flinkexample.flinkapplication;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import static org.junit.Assert.assertEquals;


//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class FlinkApplicationPerformanceTestWithThreads implements Runnable{


	public void performanceTest()  {

		TestRestTemplate restTemplate = new TestRestTemplate();
		Random rand = new Random();
		String str = String.valueOf(rand.nextInt(1000));
		String ref_Id = "Ref_id="+str;
		try {
			URI url = urlformat(ref_Id);
			//System.out.println("my url "+ url.toString());
			ResponseEntity<String> response = restTemplate.postForEntity(url,null,String.class);
			assertEquals(200,response.getStatusCodeValue());
			//assertEquals(true,response.getBody().contains("All results pushed to kafka for -> 102"));
		}
		catch (URISyntaxException e)
		{
			System.out.println("URL Exception");
		}
	}

	public URI urlformat(String ref) throws URISyntaxException{
		String baseUrl = "http://localhost:8080//kafka_to_flink?"+"&"+ref;
		URI url = new URI(baseUrl);
		return url;
	}

	@Override
	public void run() {
		performanceTest();
		//System.out.println("Count "+ Thread.currentThread().getId());
	}

	@Test
	public void testing(){
		Thread object = null;
		for(int i=0; i<30;i++) {
			object = new Thread(new FlinkApplicationPerformanceTestWithThreads());
			object.start();
			try{
				Thread.sleep(1);
			} catch (Exception e){}
		}
		try{
			object.join();} catch (Exception e){}
	}

}
