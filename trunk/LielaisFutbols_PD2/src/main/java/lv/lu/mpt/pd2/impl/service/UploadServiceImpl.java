package lv.lu.mpt.pd2.impl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lv.lu.mpt.pd2.interfaces.XmlParser;
import lv.lu.mpt.pd2.interfaces.service.UploadService;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UploadServiceImpl extends BaseService implements UploadService {

	private XmlParser xmlParser;
	
	public XmlParser getXmlParser() {
		return xmlParser;
	}

	public void setXmlParser(XmlParser xmlParser) {
		this.xmlParser = xmlParser;
	}

	@Override
	public void upload(File[] files) {

		for (File file : files) {
			InputStream xml = null;
			try {
				xml = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			parseAndSave(xml);
		}

	}

	private void parseAndSave(InputStream xml) {
		getCommonDAO().save(xmlParser.parse(xml));
	}

}
