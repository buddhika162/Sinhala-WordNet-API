package net.sf.extjwnl.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.TestSynset;
import net.sf.extjwnl.data.TestWord;

public class example1 {
public static void main(String[] args) {
	
	int filePos = 0;
	byte[] data = null;
	Path path = Paths.get("D:\\wordnet\\extjwnl-1.6.10\\extjwnl-1.6.10\\data\\clean-file\\data.noun");
    try {
		 data = Files.readAllBytes(path);
		System.out.println("byte"+data.length);	
	    
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	shakshara shakshara =  new shakshara();
	/*try {
		//shakshara.addSynsetSakshara();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JWNLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	/*try {
		shakshara.addNewWord(2406,"newWord");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JWNLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
	/*try {
		shakshara.editWord(2671,2,"nihal123");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JWNLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		
	}

}
