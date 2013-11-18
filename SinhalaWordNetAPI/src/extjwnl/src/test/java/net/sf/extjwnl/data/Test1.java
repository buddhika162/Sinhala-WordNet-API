package net.sf.extjwnl.data;

import java.io.IOException;

import net.sf.extjwnl.JWNLException;



public class Test1 {
	public static void main(String[] args) {
		
		System.out.println("hiiiiiii");
		TestSynset testSyn = new TestSynset();
		
		TestWord testword = new TestWord();
		try {
			testSyn.setUp();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		testSyn.testGetGloss();
			//testSyn.testGetGloss();
			System.out.println("done");
		
	}

}
