package lv.lu.mpt.pd2.interfaces;

import java.io.InputStream;

import lv.lu.mpt.pd2.model.Game;

public interface XmlParser {
	
	public Game parse(InputStream xml);
}
