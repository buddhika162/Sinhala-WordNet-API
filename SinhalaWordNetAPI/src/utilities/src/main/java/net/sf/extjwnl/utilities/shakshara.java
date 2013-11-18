package net.sf.extjwnl.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaGender;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaVerb;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWord;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaWordPointer;

import net.sf.extjwnl.JWNL;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Pointer;
import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.data.list.PointerTargetTree;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.dictionary.file.DictionaryFile;
import net.sf.extjwnl.dictionary.file.DictionaryFileType;

public class shakshara {
/*
* This test to add a new synset to English WorNet
*/
	String propsFile = "C:\\Users\\Buddhika\\git\\Sinhala-WordNet-API\\SinhalaWordNetAPI\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml";
	   
	Dictionary dictionary ;
	       
public long addNounSynsetSakshara(MongoSinhalaNoun mongoNoun) throws FileNotFoundException, JWNLException{
  
       //Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.NOUN);
//	       Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.VERB);
	dictionary = Dictionary.getInstance();
       dictionary.edit();
//	       DI
       Synset newSynset = dictionary.createSynset(POS.NOUN);
      // Synset newSynset = new Synset(dictionary, POS.NOUN);
       for(int i=0;i<mongoNoun.getWords().size();i++){
       mongoNoun.getWords().get(0).getLemma();
       //Synset newSynset = new Synset(dictionary, POS.NOUN);
      // newSynset.setLexFileNum(4);
       newSynset.getWords().add(new Word(dictionary, newSynset, i+1, mongoNoun.getWords().get(i).getLemma()));
       
      
       }
       newSynset.setGloss(mongoNoun.getGloss());
//	       IndexWord newWord = new IndexWord(dictionary, "indeewari", POS.NOUN, newSynset);
       
//	       Synset newSynset = new Synset(dictionary, POS.VERB);
//	       IndexWord newWord = new IndexWord(dictionary, "indeewariIsEditing", POS.VERB, newSynset);
       

      // Synset synsetFromTop1 = synsets.next();
       //Synset synsetFromTop2 = synsets.next();
       //Synset synsetFromTop3 = synsets.next();
      // Synset synsetFromTop4 = synsets.next();
       //Synset synsetFromTop5 = synsets.next();
       
       //IndexWord newWord3 = new IndexWord(dictionary, "test123", POS.NOUN, synsetFromTop4);
       //List<Word> nextword = synsetFromTop5.getWords();
	    
       //Pointer newPointer4 = new Pointer(PointerType.CATEGORY, synsetFromTop1, newSynset);
      //synsetFromTop1.getPointers().add(newPointer4);
       //Pointer newPointer5 = new Pointer(PointerType.DERIVATION, synsetFromTop2, newSynset);
      // synsetFromTop2.getPointers().add(newPointer5);
     // Pointer newPointer1 = new Pointer(PointerType.CATEGORY_MEMBER, synsetFromTop3, newSynset);
      // synsetFromTop3.getPointers().add(newPointer1);
     /* Pointer newPointer2 = new Pointer(PointerType.HYPERNYM, synsetFromTop4, newSynset);
       synsetFromTop4.getPointers().add(newPointer2);
      Pointer newPointer3 = new Pointer(PointerType.ROOT,newSynset,synsetFromTop5 );
      System.out.println("my pointer"+newPointer3);
      newSynset.getPointers().add(newPointer3);
     */
       
      // System.out.println("my pointer"+newPointer3);
       
       return newSynset.getOffset();
}


public long addVerbSynsetSakshara(MongoSinhalaVerb mongoverb) throws FileNotFoundException, JWNLException{
	  
 
	dictionary = Dictionary.getInstance();
    dictionary.edit();

    Synset newSynset = dictionary.createSynset(POS.VERB);
 
    for(int i=0;i<mongoverb.getWords().size();i++){
    	mongoverb.getWords().get(0).getLemma();
    
    newSynset.getWords().add(new Word(dictionary, newSynset, i+1, mongoverb.getWords().get(i).getLemma()));
    
   
    }
    newSynset.setGloss(mongoverb.getGloss());
    
    return newSynset.getOffset();
}

public long addRootSynsetSakshara(MongoSinhalaRoot mongoRoot) throws FileNotFoundException, JWNLException{
	  
	dictionary = Dictionary.getInstance();
    dictionary.edit();
//	       DI
    Synset newSynset = dictionary.createSynset(POS.ROOT);
    for(int i=0;i<mongoRoot.getWords().size();i++){
    	mongoRoot.getWords().get(0).getLemma();
    newSynset.getWords().add(new Word(dictionary, newSynset, i+1, mongoRoot.getWords().get(i).getLemma()));
    
   
    }
    newSynset.setGloss(mongoRoot.getGloss());
    
    return newSynset.getOffset();
}

public long addGenderSynsetSakshara(MongoSinhalaGender mongoGender) throws FileNotFoundException, JWNLException{
	  
    //Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.NOUN);
//	       Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.VERB);
	dictionary = Dictionary.getInstance();
    dictionary.edit();
//	       DI
    Synset newSynset = dictionary.createSynset(POS.GENDER);
   // Synset newSynset = new Synset(dictionary, POS.NOUN);
    for(int i=0;i<mongoGender.getWords().size();i++){
    	mongoGender.getWords().get(0).getLemma();
    //Synset newSynset = new Synset(dictionary, POS.NOUN);
   // newSynset.setLexFileNum(4);
    newSynset.getWords().add(new Word(dictionary, newSynset, i+1, mongoGender.getWords().get(i).getLemma()));
    
   
    }
    newSynset.setGloss(mongoGender.getGloss());

    
    return newSynset.getOffset();
}

public void addNounRelations(List<MongoSinhalaSynset> hm,HashMap<String, Integer> rootOrder) throws JWNLException{
	dictionary = Dictionary.getInstance();
    dictionary.edit();
    Iterator<Synset> nsynsets = dictionary.getSynsetIterator(POS.NOUN);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> nsynsetlist = new ArrayList<Synset>();
    while(nsynsets.hasNext()){
    	nsynsetlist.add(nsynsets.next());
	
    }
    
    Collections.sort(nsynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    Iterator<Synset> rsynsets = dictionary.getSynsetIterator(POS.ROOT);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> rsynsetlist = new ArrayList<Synset>();
    while(rsynsets.hasNext()){
    	rsynsetlist.add(rsynsets.next());
	
    }
    
    Collections.sort(rsynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    
    Iterator<Synset> gsynsets = dictionary.getSynsetIterator(POS.GENDER);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> gsynsetlist = new ArrayList<Synset>();
    while(gsynsets.hasNext()){
    	gsynsetlist.add(gsynsets.next());
	
    }
    
    Collections.sort(gsynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    
    
	 
	 for (int j=0;j<hm.size();j++) {
		
		//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		MongoSinhalaNoun noun=  (MongoSinhalaNoun) hm.get(j);
		List<MongoSinhalaSencePointer> pointers = noun.getSencePointers();
		if(pointers.size()>0){
			for(int i=0 ; i<pointers.size();i++){
				MongoSinhalaPointerTyps ptype =pointers.get(i).getPointerType();
				if(ptype == MongoSinhalaPointerTyps.GENDER){
					Long pid = pointers.get(i).getSynsetId();
					Integer intSynID = (int) (long) pid;
					//System.out.println(j+"size "+nsynsetlist.size());
					Pointer newPointer5 = new Pointer(PointerType.GENDER, nsynsetlist.get(j), gsynsetlist.get(intSynID-1));
					nsynsetlist.get(j).getPointers().add(newPointer5);
				}
				
			}
		}
		
		List<MongoSinhalaWord> words = noun.getWords();
		for(int i=0;i<words.size();i++){
			List<MongoSinhalaWordPointer> wPointers = words.get(i).getWordPointerList();
			for(int k=0;k<wPointers.size();k++){
				MongoSinhalaPointerTyps ptype = wPointers.get(k).getPointerType();
				if(ptype == MongoSinhalaPointerTyps.ROOT){
					
					String rId = wPointers.get(k).getSynsetIDasString();
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					int rposision = rootOrder.get(rId);
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.ROOT, nsynsetlist.get(j).getWords().get(i), rsynsetlist.get(rposision).getWords().get(0));
					nsynsetlist.get(j).getPointers().add(newPointer);
				}
			}
		}
		
		
	}
    
    System.out.println("size "+nsynsetlist.size());
    for(int i=0;i<nsynsetlist.size();i++){
    //System.out.println(nsynsetlist.get(i)+"size"+i);
    }
	Pointer newPointer5 = new Pointer(PointerType.HYPERNYM, nsynsetlist.get(0), nsynsetlist.get(1));
	nsynsetlist.get(0).getPointers().add(newPointer5);
}



public void initialize(){
	try {
		JWNL.initialize(new FileInputStream(propsFile));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JWNLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void delete(){
	try {
		dictionary = Dictionary.getInstance();
        dictionary.close();
        dictionary.delete();

        dictionary = Dictionary.getInstance();
	} catch (JWNLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void close(){
	try {
		dictionary.save();
	} catch (JWNLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void printWord() throws FileNotFoundException, JWNLException{
String propsFile = "D:\\wordnet\\extjwnl-1.6.10\\extjwnl-1.6.10\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml";
Dictionary dictionary = Dictionary.getInstance(new FileInputStream(propsFile));
        
        IndexWord indexWord = dictionary.getIndexWord(POS.NOUN, "sinhala");
        System.out.println("inword"+indexWord);
        PointerTargetTree hyponyms = PointerUtils.getHypernymTree(indexWord.getSenses().get(0));
        System.out.println("Hyponyms of \"" + indexWord.getLemma() + "\":");
        hyponyms.print();
        
        
}

public void addNewWord(int offset,String newWd)throws FileNotFoundException, JWNLException{
	String propsFile = "D:\\wordnet\\extjwnl-1.6.10\\extjwnl-1.6.10\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml";
	   JWNL.initialize(new FileInputStream(propsFile));
	       Dictionary dictionary = Dictionary.getInstance();
	      // Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.NOUN);
//	       Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.VERB);
	       dictionary.edit();
	       //Synset synsetFromTop4 = synsets.next();
	       Synset selectedSynset = dictionary.getSynsetAt(POS.NOUN, offset);
	      // System.out.println("synset"+selectedSynset);
	       IndexWord newWord3 = new IndexWord(dictionary, newWd, POS.NOUN, selectedSynset);
	       dictionary.save();
	      // System.out.println("synset"+selectedSynset);
}

public void editWord(int offset,int wordNo,String newWd)throws FileNotFoundException, JWNLException{
	String propsFile = "D:\\wordnet\\extjwnl-1.6.10\\extjwnl-1.6.10\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml";
	   JWNL.initialize(new FileInputStream(propsFile));
	       Dictionary dictionary = Dictionary.getInstance();
	      // Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.NOUN);
//	       Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.VERB);
	       dictionary.edit();
	       
	       //Synset synsetFromTop4 = synsets.next();
	       Synset selectedSynset = dictionary.getSynsetAt(POS.NOUN, offset);
	       List<Word> nextword = selectedSynset.getWords();
	       //System.out.println("word"+nextword.get(2));
	       Word newWord = new Word(dictionary, selectedSynset, wordNo,newWd);
	       //Word newWord1 = new 
	       nextword.set(wordNo, newWord);
	       //IndexWord newWord3 = new IndexWord(dictionary, "bud123", POS.NOUN, selectedSynset);
	       dictionary.save();
	       //System.out.println("word"+nextword.get(2)+" new word "+newWord);
}

}