package datadriven;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import utils.ConfigDataprovider;
import utils.XLUtils;

public class AddEmployes {
	Logger logger;
	ConfigDataprovider config;
	
	@BeforeClass
	public void setUp() {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\resources\\log4j.properties");
		logger = Logger.getLogger(ConfigDataprovider.class);
		config=new ConfigDataprovider();
		
		

	}
	@Test(dataProvider="empdata")
	public void PostData(String name, String salary,String age) throws JsonProcessingException {
		
		
		RestAssured.baseURI=config.getDataFromConfig("postpath");
		RequestSpecification httpRequest=RestAssured.given();
        JSONObject requestParams=new JSONObject();
		
		requestParams.put("name", name);
		requestParams.put("salary", salary);
		requestParams.put("age", age);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		Response responce=httpRequest.request(Method.POST);
		
		String responseBody=responce.getBody().asString();
		logger.info(responseBody);
		logger.info(responce.getStatusCode());
		
		
		
	}
	
	@DataProvider(name="empdata")
	String [][] getEmpData() throws IOException{
		String path=System.getProperty("user.dir")+"/test-data/dataforEmp.xlsx";
		System.out.println(path);
		int row=XLUtils.getRowCount(path,"Sheet1");
		int col=XLUtils.getCellcount(path, "Sheet1", 1);
		String empData[][]= new String[row][col];
		for(int rowCount=1;rowCount<=row;rowCount++) {
			for(int colCount=0;colCount<col;colCount++) {
				empData[rowCount-1][colCount]=XLUtils.getCellData(path,"Sheet1", rowCount, colCount);
			}
		}
		
		
		return empData;
		
	}
}
