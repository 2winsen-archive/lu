package lv.lu.mpt.pd2.xml;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Error handler for DOM XML parser.
 */
public class XmlErrorHandler implements ErrorHandler {

	private final static Logger log = Logger.getLogger(XmlErrorHandler.class);

	public void error(SAXParseException e) throws SAXException {
		log.error("Error while parsing XML file");
		log.error(e);
		throw new RuntimeException(e);
	}

	public void fatalError(SAXParseException e) throws SAXException {
		log.error("Fatal error while parsing XML file");
		log.error(e);
		throw new RuntimeException(e);
	}

	public void warning(SAXParseException e) throws SAXException {
		log.warn("Warning is generated while parsing XML file");
		log.warn(e.getMessage());
	}

}
