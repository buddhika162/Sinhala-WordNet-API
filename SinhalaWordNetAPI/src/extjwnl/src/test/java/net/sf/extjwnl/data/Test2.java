package net.sf.extjwnl.data;

import java.io.*;
import net.sf.extjwnl.*;
import net.sf.extjwnl.dictionary.*;


import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Test2 {



/**
*
* @author Prudhvi
*/

   
    //public String getsyns(String inputword) throws JWNLException, IOException
    public static void main(String[] args) throws JWNLException, IOException
    {
         JWNL.initialize(new FileInputStream("C:\\Users\\Prudhvi\\Desktop\\SEMI_PROJECT\\jwnl14-rc2\\config\\file_properties.xml"));
         Dictionary wordnet = Dictionary.getInstance();
        
         //IndexWord s = wordnet.
         IndexWordSet set = wordnet.lookupAllIndexWords("director");
         // Turn it into an array of IndexWords
         IndexWord[] ws = set.getIndexWordArray();
         POS p = ws[0].getPOS();
        
        
         Set<String> synonyms = new HashSet<String>();
         //IndexWord indexWord = wordnet.lookupIndexWord(POS.NOUN, "wife");
         IndexWord indexWord = wordnet.lookupIndexWord(p, "director");
         List<Synset> synSets = indexWord.getSenses();
        
         for (Synset synset : synSets)
         {
            List<Word> words = synset.getWords();
           
            for (Word word : words)
            {
               synonyms.add(word.getLemma());
            }
         }
         System.out.println(synonyms);
         //check if synonyms is empty or not.
         //to iterate through synonym list and form an array.
         /*String syns = new String;
         Iterator<String> itr = synonyms.iterator();
         int k=0;
         while(itr.hasNext())
         {
             syns = itr.next();
             System.out.println(syns);
             k++;
         }*/
        
         Set<String> hypernyms = new HashSet<String>();
         for (Synset synset : synSets)
         {
               List<PointerTarget> targets =
                  synset.getTargets(PointerType.HYPERNYM);
               for (PointerTarget target : targets)
               {
                  List<Word> words = ((Synset) target).getWords();
                  for (Word word : words)
                  {
                     hypernyms.add(word.getLemma());
                  }
               }
         }
         System.out.println(hypernyms);
        
         Set<String> hyponyms = new HashSet<String>();
         for (Synset synset : synSets)
         {
               List<PointerTarget> targets =
                  synset.getTargets(PointerType.HYPONYM);
               for (PointerTarget target : targets)
               {
                  List<Word> words = ((Synset) target).getWords();
                  for (Word word : words)
                  {
                     hyponyms.add(word.getLemma());
                  }
               }
         }
         System.out.println(hyponyms);
         //return "prudhvi";
    }
}