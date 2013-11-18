package net.sf.extjwnl.dictionary.morph;

import net.sf.extjwnl.dictionary.Dictionary;

/**
 * Base class for operations.
 *
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public abstract class AbstractOperation implements Operation {

    /**
	 * @uml.property  name="dictionary"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    protected Dictionary dictionary;

    protected AbstractOperation(Dictionary dictionary) {
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
	 * @uml.property  name="dictionary"
	 */
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}