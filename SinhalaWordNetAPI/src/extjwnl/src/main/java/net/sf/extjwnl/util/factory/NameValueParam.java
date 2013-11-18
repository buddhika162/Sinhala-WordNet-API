package net.sf.extjwnl.util.factory;

import net.sf.extjwnl.dictionary.Dictionary;

import java.util.List;

/**
 * Param with name and value.
 *
 * @author John Didion <jdidion@didion.net>
 * @author <a rel="author" href="http://autayeu.com/">Aliaksandr Autayeu</a>
 */
public class NameValueParam extends AbstractValueParam {
    /**
	 * @uml.property  name="name"
	 */
    private final String name;
    /**
	 * @uml.property  name="value"
	 */
    private final String value;

    public NameValueParam(Dictionary dictionary, String name, String value) {
        super(dictionary);
        this.name = name;
        this.value = value;
    }

    public NameValueParam(Dictionary dictionary, String name, String value, List<Param> params) {
        super(dictionary, params);
        this.name = name;
        this.value = value;
    }

    /**
	 * @return
	 * @uml.property  name="name"
	 */
    public String getName() {
        return name;
    }

    /**
	 * @return
	 * @uml.property  name="value"
	 */
    public String getValue() {
        return value;
    }
}