package com.deloitte.interview.icecream;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses( {
	
	ServeIceCreamServiceTest.class,
	IceCreamControllerTest.class
	
})
public class TestSuite {

}
