/*
 * Copyright (C) 2015 Keio University
 */
package org.fosstrak.ale.server.readers.llrp.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.epcglobalinc.tdt.LevelTypeList;
import org.fosstrak.ale.exception.DuplicateSubscriptionException;
import org.fosstrak.ale.exception.InvalidURIException;
import org.fosstrak.ale.exception.NoSuchSubscriberException;
import org.fosstrak.ale.server.EventCycle;
import org.fosstrak.ale.server.ReportsGenerator;
import org.fosstrak.ale.server.Tag;
import org.fosstrak.ale.server.impl.EventCycleImpl;
import org.fosstrak.ale.server.readers.LogicalReader;
import org.fosstrak.ale.server.readers.LogicalReaderManager;
import org.fosstrak.ale.server.util.TagHelper;
import org.fosstrak.ale.util.DeserializerUtil;
import org.fosstrak.ale.xsd.ale.epcglobal.ECReport;
import org.fosstrak.ale.xsd.ale.epcglobal.ECReportGroupListMember;
import org.fosstrak.ale.xsd.ale.epcglobal.ECReports;
import org.fosstrak.ale.xsd.ale.epcglobal.ECSpec;
import org.fosstrak.tdt.TDTEngine;
import org.junit.Test;

/**
 * small test suite in order to test reading multi-standard tags at an event cycle.<br/>
 * basically this means:<br/>
 * <ol>
 * <li>start an event cycle</li>
 * <li>deliver some tags into the cycle</li>
 * <li>wait for the cycle to go through and obtain the reports</li>
 * <li>check the report contains the given tags</li>
 * </ol>
 * 
 * @author iomz
 *
 */
// TODO: Make runnable with maven test
//@Ignore
public class MultiStandardTest {

	private final String TAG1_BINARY = "001100000010110110110011000110011010000000000000000000000100000000000000000000000000000000000011";
	private final String TAG1_HEX = "302DB319A000004000000003";
	private final String TAG1_C1G2PC = "12288";
	private final String TAG1_PURE_URI = "urn:epc:id:sgtin:456235520.0001.3";

	private final String TAG2_BINARY = "001100000010110110110011000110011010000000000000000000000100000000000000000000000000000010010001";
	private final String TAG2_HEX = "302DB319A000004000000091";
	private final String TAG2_C1G2PC = "12288";
	private final String TAG2_PURE_URI = "urn:epc:id:sgtin:456235520.0001.145";
	
	@Test
	public void testTDTEngine() throws Exception {
	    TDTEngine tdt = TagHelper.getTDTEngine();
		Map<String,String> params = new HashMap<String,String>();

		final Tag t1 = new Tag();
		t1.setTagAsBinary(TAG1_BINARY);
		t1.setNsiafi(TAG1_C1G2PC);
		
	    String t1PureUri = tdt.convert(tdt.hex2bin(TAG1_HEX), TAG1_HEX, params, t1.getNsi(), 
	    		t1.getAfi(), t1.getTagLength(), LevelTypeList.PURE_IDENTITY);
	    
	    Assert.assertEquals(TAG1_PURE_URI, t1PureUri);
	    
		final Tag t2 = new Tag();
		t2.setTagAsBinary(TAG2_BINARY);
		t2.setNsiafi(TAG2_C1G2PC);
		
	    String t2PureUri = tdt.convert(tdt.hex2bin(TAG2_HEX), TAG2_HEX, params, t2.getNsi(), 
	    		t2.getAfi(), t2.getTagLength(), LevelTypeList.PURE_IDENTITY);
	    
	    Assert.assertEquals(TAG2_PURE_URI, t2PureUri);

		System.out.println("Assertion finished for testTDTEngine()");
	}
}
