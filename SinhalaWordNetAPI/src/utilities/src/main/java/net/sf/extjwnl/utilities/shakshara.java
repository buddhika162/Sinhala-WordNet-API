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

import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaAdjective;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaDerivationType;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaGender;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaNoun;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaOrigin;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPoinertTypeSemetric;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaPointerTyps;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaRoot;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSencePointer;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaSynset;
import org.sinhala.wordnet.wordnetDB.model.MongoSinhalaUsage;
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
	       
public long addSynsetToText(MongoSinhalaSynset mongoSynset,POS pos) throws FileNotFoundException, JWNLException{
 	
	dictionary = Dictionary.getInstance();
       dictionary.edit();

       Synset newSynset = dictionary.createSynset(pos);
       for(int i=0;i<mongoSynset.getWords().size();i++){
    	  mongoSynset.getWords().get(0).getLemma();
       newSynset.getWords().add(new Word(dictionary, newSynset, i+1, mongoSynset.getWords().get(i).getLemma()));
       
      
       }
       newSynset.setGloss(mongoSynset.getGloss());

       
       return newSynset.getOffset();
}

public void addRelations(List<MongoSinhalaSynset> nounSynset,List<MongoSinhalaSynset> verbSynset,List<MongoSinhalaSynset> adjSynset,HashMap<String, Integer> rootOrder) throws JWNLException{
	dictionary = Dictionary.getInstance();
    dictionary.edit();
    
    HashMap<Long, Integer> nOrder = new HashMap<Long, Integer>();
    int nord=0;
	for(MongoSinhalaSynset s : nounSynset){
		nOrder.put(s.getEWNId(), nord);
		//System.out.println(nord+"s"+s.toString());
		nord++;
	}
	HashMap<Long, Integer> vOrder = new HashMap<Long, Integer>();
    int vord=0;
	for(MongoSinhalaSynset s : verbSynset){
		vOrder.put(s.getEWNId(), vord);
		//System.out.println("s"+s.toString());
		vord++;
	}
	HashMap<Long, Integer> adjOrder = new HashMap<Long, Integer>();
    int adjord=0;
	for(MongoSinhalaSynset s : adjSynset){
		adjOrder.put(s.getEWNId(), adjord);
		//System.out.println("s"+s.toString());
		adjord++;
	}
    
    
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
    
    Iterator<Synset> vsynsets = dictionary.getSynsetIterator(POS.VERB);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> vsynsetlist = new ArrayList<Synset>();
    while(vsynsets.hasNext()){
    	vsynsetlist.add(vsynsets.next());
	
    }
    
    Collections.sort(vsynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    Iterator<Synset> adjsynsets = dictionary.getSynsetIterator(POS.ADJECTIVE);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> adjsynsetlist = new ArrayList<Synset>();
    while(adjsynsets.hasNext()){
    	adjsynsetlist.add(adjsynsets.next());
	
    }
    
    Collections.sort(adjsynsetlist, new Comparator<Synset>() {

       
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
    
    Iterator<Synset> dsynsets = dictionary.getSynsetIterator(POS.DERIVATIONLANG);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> dsynsetlist = new ArrayList<Synset>();
    while(dsynsets.hasNext()){
    	dsynsetlist.add(dsynsets.next());
	
    }
    
    Collections.sort(dsynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    
    Iterator<Synset> usynsets = dictionary.getSynsetIterator(POS.USAGE);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> usynsetlist = new ArrayList<Synset>();
    while(usynsets.hasNext()){
    	usynsetlist.add(usynsets.next());
	
    }
    
    Collections.sort(usynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    
    Iterator<Synset> osynsets = dictionary.getSynsetIterator(POS.ORIGIN);
	//Synset newSynset = new Synset(dictionary, POS.NOUN);
    List<Synset> osynsetlist = new ArrayList<Synset>();
    while(osynsets.hasNext()){
    	osynsetlist.add(osynsets.next());
	
    }
    
    Collections.sort(osynsetlist, new Comparator<Synset>() {

       
		@Override
		public int compare(Synset o1, Synset o2) {
			// TODO Auto-generated method stub
			return Long.compare(o1.getOffset(), o2.getOffset()); 
		}
    });
    
    
    PointerType jwnlpType = null;
	List<Synset> tempSynsetlist = new ArrayList<Synset>();
	List<MongoSinhalaSynset> tempSynsets = new ArrayList<MongoSinhalaSynset>();
	HashMap<Long, Integer> tempOrder = new HashMap<Long, Integer>();
	 for (int j=0;j<nounSynset.size();j++) {
		
		//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		MongoSinhalaNoun noun=  (MongoSinhalaNoun) nounSynset.get(j);
		//System.out.println("noun"+noun);
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
				else{
					//System.out.println(ptype);
					
					
					if(pointers.get(i).getPointedFile().equals("n")){
						tempSynsetlist = nsynsetlist;
						tempOrder = nOrder;
						tempSynsets = nounSynset;
					}
					else if(pointers.get(i).getPointedFile().equals("v")){
						tempSynsetlist = vsynsetlist;
						tempOrder = vOrder;
						tempSynsets = verbSynset;
					}
					else if(pointers.get(i).getPointedFile().equals("adj")){
						tempSynsetlist = adjsynsetlist;
						tempOrder = adjOrder;
						tempSynsets = adjSynset;
					}
					
					
					
					
					if(ptype == MongoSinhalaPointerTyps.HYPERNYM){
						jwnlpType = PointerType.HYPERNYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.HYPONYM){
						jwnlpType = PointerType.HYPONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.MEMBER_HOLONYM){
						jwnlpType = PointerType.MEMBER_HOLONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.SUBSTANCE_HOLONYM){
						jwnlpType = PointerType.SUBSTANCE_HOLONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.PART_HOLONYM){
						jwnlpType = PointerType.PART_HOLONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.MEMBER_MERONYM){
						jwnlpType = PointerType.MEMBER_MERONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.SUBSTANCE_MERONYM){
						jwnlpType = PointerType.SUBSTANCE_MERONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.PART_MERONYM){
						jwnlpType = PointerType.PART_MERONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.ATTRIBUTE){
						jwnlpType = PointerType.ATTRIBUTE;
					}
					else if(ptype == MongoSinhalaPointerTyps.DERIVATION){
						jwnlpType = PointerType.DERIVATION;
					}
					
					
					Long pid = pointers.get(i).getSynsetId();
					int nposision = tempOrder.get(pid);
					//System.out.println(pid+"nposision"+nposision);
					Integer intSynID = (int) (long) pid;
					Pointer newPointer5 = new Pointer(jwnlpType, nsynsetlist.get(j), tempSynsetlist.get(nposision));
					nsynsetlist.get(j).getPointers().add(newPointer5);
					MongoSinhalaPoinertTypeSemetric SymPointerGenarator = new MongoSinhalaPoinertTypeSemetric();
					MongoSinhalaPointerTyps symPointer = SymPointerGenarator.getSymetric(ptype);
					List<MongoSinhalaSencePointer> symPoynterList = tempSynsets.get(nposision).getSencePointers();
					List<MongoSinhalaSencePointer> newSymPointerList = new ArrayList<MongoSinhalaSencePointer>();
					//System.out.println("befor"+tempSynsets.get(nposision).toString());
					for(int k = 0;k<symPoynterList.size();k++){
						if(symPoynterList.get(k).getSynsetId().equals(nounSynset.get(j).getEWNId()) && symPoynterList.get(k).getPointerType().equals(symPointer)){
							
						}
						else{
							newSymPointerList.add(symPoynterList.get(k));
						}
					}
					
					if(pointers.get(i).getPointedFile().equals("n")){
						nounSynset.get(nposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+nounSynset.get(nposision).toString());
					}
					else if(pointers.get(i).getPointedFile().equals("v")){
						verbSynset.get(nposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+verbSynset.get(nposision).toString());
					}
					else if(pointers.get(i).getPointedFile().equals("adj")){
						adjSynset.get(nposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+adjSynset.get(nposision).toString());
					}
					
					
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
				else if(ptype == MongoSinhalaPointerTyps.DERIVATION_TYPE){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.DERIVATION_LANG, nsynsetlist.get(j).getWords().get(i), dsynsetlist.get(intSynID-1).getWords().get(0));
					nsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.USAGE){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.USAGE_MODE, nsynsetlist.get(j).getWords().get(i), usynsetlist.get(intSynID-1).getWords().get(0));
					nsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.ORIGIN){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.ORIGIN, nsynsetlist.get(j).getWords().get(i), osynsetlist.get(intSynID-1).getWords().get(0));
					nsynsetlist.get(j).getPointers().add(newPointer);
				}
			}
		}
		
		
	}
	 
	 
	 
	 jwnlpType = null;
	 tempSynsetlist = new ArrayList<Synset>();
	 tempSynsets = new ArrayList<MongoSinhalaSynset>();
	 tempOrder = new HashMap<Long, Integer>();
	 
	 for (int j=0;j<verbSynset.size();j++) {
			
		//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		MongoSinhalaVerb verb=  (MongoSinhalaVerb) verbSynset.get(j);
		//System.out.println("verb"+verb);
		List<MongoSinhalaSencePointer> pointers = verb.getSencePointers();
		
		if(pointers.size()>0){
			for(int i=0 ; i<pointers.size();i++){
				MongoSinhalaPointerTyps ptype =pointers.get(i).getPointerType();
				
				if(ptype == MongoSinhalaPointerTyps.GENDER){
					Long pid = pointers.get(i).getSynsetId();
					Integer intSynID = (int) (long) pid;
					//System.out.println(j+"size "+nsynsetlist.size());
					Pointer newPointer5 = new Pointer(PointerType.GENDER, vsynsetlist.get(j), gsynsetlist.get(intSynID-1));
					vsynsetlist.get(j).getPointers().add(newPointer5);
				}
				else{
					//System.out.println(ptype);
					
					
					if(pointers.get(i).getPointedFile().equals("n")){
						tempSynsetlist = nsynsetlist;
						tempOrder = nOrder;
						tempSynsets = nounSynset;
					}
					else if(pointers.get(i).getPointedFile().equals("v")){
						tempSynsetlist = vsynsetlist;
						tempOrder = vOrder;
						tempSynsets = verbSynset;
					}
					else if(pointers.get(i).getPointedFile().equals("adj")){
						tempSynsetlist = adjsynsetlist;
						tempOrder = adjOrder;
						tempSynsets = adjSynset;
					}
					
					
					
					
					if(ptype == MongoSinhalaPointerTyps.HYPERNYM){
						jwnlpType = PointerType.HYPERNYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.HYPONYM){
						jwnlpType = PointerType.HYPONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.ENTAILMENT){
						jwnlpType = PointerType.ENTAILMENT;
					}
					
					else if(ptype == MongoSinhalaPointerTyps.CAUSE){
						jwnlpType = PointerType.CAUSE;
					}
					else if(ptype == MongoSinhalaPointerTyps.DERIVATION){
						jwnlpType = PointerType.DERIVATION;
					}
					
					
					Long pid = pointers.get(i).getSynsetId();
					int vposision = tempOrder.get(pid);
					Integer intSynID = (int) (long) pid;
					Pointer newPointer5 = new Pointer(jwnlpType, vsynsetlist.get(j), tempSynsetlist.get(vposision));
					vsynsetlist.get(j).getPointers().add(newPointer5);
					MongoSinhalaPoinertTypeSemetric SymPointerGenarator = new MongoSinhalaPoinertTypeSemetric();
					MongoSinhalaPointerTyps symPointer = SymPointerGenarator.getSymetric(ptype);
					List<MongoSinhalaSencePointer> symPoynterList = tempSynsets.get(vposision).getSencePointers();
					List<MongoSinhalaSencePointer> newSymPointerList = new ArrayList<MongoSinhalaSencePointer>();
					//System.out.println("befor"+tempSynsets.get(nposision).toString());
					for(int k = 0;k<symPoynterList.size();k++){
						if(symPoynterList.get(k).getSynsetId().equals(verbSynset.get(j).getEWNId()) && symPoynterList.get(k).getPointerType().equals(symPointer)){
							
						}
						else{
							newSymPointerList.add(symPoynterList.get(k));
						}
					}
					
					if(pointers.get(i).getPointedFile().equals("n")){
						nounSynset.get(vposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+nounSynset.get(nposision).toString());
					}
					else if(pointers.get(i).getPointedFile().equals("v")){
						verbSynset.get(vposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+verbSynset.get(nposision).toString());
					}
					else if(pointers.get(i).getPointedFile().equals("adj")){
						adjSynset.get(vposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+adjSynset.get(nposision).toString());
					}
					
					
				}
				
				
			}
		}
		
		List<MongoSinhalaWord> words = verb.getWords();
		for(int i=0;i<words.size();i++){
			List<MongoSinhalaWordPointer> wPointers = words.get(i).getWordPointerList();
			for(int k=0;k<wPointers.size();k++){
				MongoSinhalaPointerTyps ptype = wPointers.get(k).getPointerType();
				if(ptype == MongoSinhalaPointerTyps.ROOT){
					
					String rId = wPointers.get(k).getSynsetIDasString();
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					int rposision = rootOrder.get(rId);
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.ROOT, vsynsetlist.get(j).getWords().get(i), rsynsetlist.get(rposision).getWords().get(0));
					vsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.DERIVATION_TYPE){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.DERIVATION_LANG, vsynsetlist.get(j).getWords().get(i), dsynsetlist.get(intSynID-1).getWords().get(0));
					vsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.USAGE){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.USAGE_MODE, vsynsetlist.get(j).getWords().get(i), usynsetlist.get(intSynID-1).getWords().get(0));
					vsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.ORIGIN){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.ORIGIN, vsynsetlist.get(j).getWords().get(i), osynsetlist.get(intSynID-1).getWords().get(0));
					vsynsetlist.get(j).getPointers().add(newPointer);
				}
			}
		}
		
		
	}
    
	 
	 jwnlpType = null;
	 tempSynsetlist = new ArrayList<Synset>();
	 tempSynsets = new ArrayList<MongoSinhalaSynset>();
	 tempOrder = new HashMap<Long, Integer>();
	 
	 for (int j=0;j<adjSynset.size();j++) {
			
		//System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		 MongoSinhalaAdjective adjective=  (MongoSinhalaAdjective) adjSynset.get(j);
		// System.out.println("adjective"+adjective);
		List<MongoSinhalaSencePointer> pointers = adjective.getSencePointers();
		
		if(pointers.size()>0){
			for(int i=0 ; i<pointers.size();i++){
				MongoSinhalaPointerTyps ptype =pointers.get(i).getPointerType();
				
				if(ptype == MongoSinhalaPointerTyps.GENDER){
					Long pid = pointers.get(i).getSynsetId();
					Integer intSynID = (int) (long) pid;
					//System.out.println(j+"size "+nsynsetlist.size());
					Pointer newPointer5 = new Pointer(PointerType.GENDER, adjsynsetlist.get(j), gsynsetlist.get(intSynID-1));
					adjsynsetlist.get(j).getPointers().add(newPointer5);
				}
				else{
					//System.out.println(ptype);
					
					
					if(pointers.get(i).getPointedFile().equals("n")){
						tempSynsetlist = nsynsetlist;
						tempOrder = nOrder;
						tempSynsets = nounSynset;
					}
					else if(pointers.get(i).getPointedFile().equals("v")){
						tempSynsetlist = vsynsetlist;
						tempOrder = vOrder;
						tempSynsets = verbSynset;
					}
					else if(pointers.get(i).getPointedFile().equals("adj")){
						tempSynsetlist = adjsynsetlist;
						tempOrder = adjOrder;
						tempSynsets = adjSynset;
					}
					
					
					
					
					if(ptype == MongoSinhalaPointerTyps.HYPERNYM){
						jwnlpType = PointerType.HYPERNYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.HYPONYM){
						jwnlpType = PointerType.HYPONYM;
					}
					else if(ptype == MongoSinhalaPointerTyps.ENTAILMENT){
						jwnlpType = PointerType.ENTAILMENT;
					}
					
					else if(ptype == MongoSinhalaPointerTyps.CAUSE){
						jwnlpType = PointerType.CAUSE;
					}
					else if(ptype == MongoSinhalaPointerTyps.DERIVATION){
						jwnlpType = PointerType.DERIVATION;
					}
					
					
					Long pid = pointers.get(i).getSynsetId();
					int adjposision = tempOrder.get(pid);
					Integer intSynID = (int) (long) pid;
					Pointer newPointer5 = new Pointer(jwnlpType, adjsynsetlist.get(j), tempSynsetlist.get(adjposision));
					adjsynsetlist.get(j).getPointers().add(newPointer5);
					MongoSinhalaPoinertTypeSemetric SymPointerGenarator = new MongoSinhalaPoinertTypeSemetric();
					MongoSinhalaPointerTyps symPointer = SymPointerGenarator.getSymetric(ptype);
					List<MongoSinhalaSencePointer> symPoynterList = tempSynsets.get(adjposision).getSencePointers();
					List<MongoSinhalaSencePointer> newSymPointerList = new ArrayList<MongoSinhalaSencePointer>();
					//System.out.println("befor"+tempSynsets.get(nposision).toString());
					for(int k = 0;k<symPoynterList.size();k++){
						if(symPoynterList.get(k).getSynsetId().equals(adjSynset.get(j).getEWNId()) && symPoynterList.get(k).getPointerType().equals(symPointer)){
							
						}
						else{
							newSymPointerList.add(symPoynterList.get(k));
						}
					}
					
					if(pointers.get(i).getPointedFile().equals("n")){
						nounSynset.get(adjposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+nounSynset.get(nposision).toString());
					}
					else if(pointers.get(i).getPointedFile().equals("v")){
						verbSynset.get(adjposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+verbSynset.get(nposision).toString());
					}
					else if(pointers.get(i).getPointedFile().equals("adj")){
						adjSynset.get(adjposision).SetSencePointers(newSymPointerList);
						//System.out.println("after"+adjSynset.get(nposision).toString());
					}
					
					
				}
				
				
			}
		}
		
		List<MongoSinhalaWord> words = adjective.getWords();
		for(int i=0;i<words.size();i++){
			List<MongoSinhalaWordPointer> wPointers = words.get(i).getWordPointerList();
			for(int k=0;k<wPointers.size();k++){
				MongoSinhalaPointerTyps ptype = wPointers.get(k).getPointerType();
				if(ptype == MongoSinhalaPointerTyps.ROOT){
					
					String rId = wPointers.get(k).getSynsetIDasString();
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					int rposision = rootOrder.get(rId);
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.ROOT, adjsynsetlist.get(j).getWords().get(i), rsynsetlist.get(rposision).getWords().get(0));
					adjsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.DERIVATION_TYPE){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.DERIVATION_LANG, adjsynsetlist.get(j).getWords().get(i), dsynsetlist.get(intSynID-1).getWords().get(0));
					adjsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.USAGE){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.USAGE_MODE, adjsynsetlist.get(j).getWords().get(i), usynsetlist.get(intSynID-1).getWords().get(0));
					adjsynsetlist.get(j).getPointers().add(newPointer);
				}
				else if(ptype == MongoSinhalaPointerTyps.ORIGIN){
					
					
					Long pid =  wPointers.get(k).getSynsetId();
					Integer intSynID = (int) (long) pid;
					
					//System.out.println(noun.getId()+"sence"+noun.getEWNId()+"id "+rId);
					
					//System.out.println(rposision+"id "+rId);
					Pointer newPointer = new Pointer(PointerType.ORIGIN, adjsynsetlist.get(j).getWords().get(i), osynsetlist.get(intSynID-1).getWords().get(0));
					adjsynsetlist.get(j).getPointers().add(newPointer);
				}
			}
		}
		
		
	}
	 
    
   /* for(int i=0;i<nounSynset.size();i++){
    System.out.println(nounSynset.get(i)+"size"+i);
    }
    
    Iterator it = nOrder.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry pairs = (Map.Entry)it.next();
        System.out.println(pairs.getKey() + " = " + pairs.getValue());
       // it.remove(); // avoids a ConcurrentModificationException
    }*/
	/*Pointer newPointer5 = new Pointer(PointerType.HYPERNYM, nsynsetlist.get(0), nsynsetlist.get(1));
	nsynsetlist.get(0).getPointers().add(newPointer5);
	Pointer newPointer6 = new Pointer(PointerType.HYPONYM, nsynsetlist.get(1), nsynsetlist.get(0));
	nsynsetlist.get(1).getPointers().add(newPointer6);*/
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