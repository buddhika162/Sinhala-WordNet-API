package net.sf.extjwnl.data;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;

import java.io.IOException;

/**
 * Base class for dictionary elements.
 *
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public abstract class BaseDictionaryElement implements DictionaryElement {

    private static final long serialVersionUID = 4L;

    /**
	 * @uml.property  name="dictionary"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    protected transient Dictionary dictionary;

    protected BaseDictionaryElement(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    /**
	 * @return
	 * @uml.property  name="dictionary"
	 */
    public Dictionary getDictionary() {
        return dictionary;
    }

    /**
	 * @param dictionary
	 * @throws JWNLException
	 * @uml.property  name="dictionary"
	 */
    public void setDictionary(Dictionary dictionary) throws JWNLException {
        if (dictionary != this.dictionary) {
            if (null != this.dictionary) {
                Dictionary old = this.dictionary;
                this.dictionary = dictionary;
                old.removeElement(this);
            }
            this.dictionary = dictionary;
            if (null != dictionary) {
                dictionary.addElement(this);
            }
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        dictionary = Dictionary.getRestoreDictionary();
    }
}
