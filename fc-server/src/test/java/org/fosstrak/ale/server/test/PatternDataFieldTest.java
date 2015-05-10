/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Fosstrak (www.fosstrak.org).
 *
 * Fosstrak is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Fosstrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Fosstrak; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.fosstrak.ale.server.test;

import java.net.URL;

import junit.framework.Assert;

import org.apache.log4j.PropertyConfigurator;
import org.fosstrak.ale.exception.ECSpecValidationException;
import org.fosstrak.ale.server.PatternDataField;
import org.fosstrak.ale.server.PatternUsage;
import org.junit.Before;
import org.junit.Test;

/**
 * @author regli
 */
public class PatternDataFieldTest {

	private static final String LONG = "50";
	private static final long LOW = 0;
	private static final long HIGH = 100;
	private static final String RANGE = "[" + LOW + "-" + HIGH + "]";
	private static final String ASTERISK = "*";
	private static final String X = "X";
	private static final String ABC = "abc";

	@Before
	public void setUp() throws Exception {
		// configure Logger with properties file
		URL url = this.getClass().getResource("/log4j.properties");
		PropertyConfigurator.configure(url);
		
	}

	@Test
	public void testCreateTagDataField() throws Exception {
		
		new PatternDataField(LONG, PatternUsage.TAG);
		
	}

	@Test
	public void testCreateTagDataFieldWithInvalidStringRepresentation_Range() throws Exception {
		
		try {
			new PatternDataField(RANGE, PatternUsage.TAG);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + RANGE + "'. Only 'long' is allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + RANGE + "'.");
		
	}

	@Test
	public void testCreateTagDataFieldWithInvalidStringRepresentation_Asterisk() throws Exception {
		
		try {
			new PatternDataField(ASTERISK, PatternUsage.TAG);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + ASTERISK + "'. Only 'long' is allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + ASTERISK + "'.");
		
	}

	@Test
	public void testCreateTagDataFieldWithInvalidStringRepresentation_X() throws Exception {
		
		try {
			new PatternDataField(X, PatternUsage.TAG);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + X + "'. Only 'long' is allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + X + "'.");
		
	}

	@Test
	public void testCreateTagDataFieldWithInvalidStringRepresentation_Abc() throws Exception {
		
		try {
			new PatternDataField(ABC, PatternUsage.TAG);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + ABC + "'. Only 'long' is allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + ABC + "'.");
		
	}

	@Test
	public void testCreateFilterDataField() throws Exception {
		
		new PatternDataField(LONG, PatternUsage.FILTER);
		new PatternDataField(RANGE, PatternUsage.FILTER);
		new PatternDataField(ASTERISK, PatternUsage.FILTER);
		
	}

	@Test
	public void testCreateFilterDataFieldWithInvalidStringRepresentation_X() throws Exception {
		
		try {
			new PatternDataField(X, PatternUsage.FILTER);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + X + "'. Only '*', '[lo-hi]' or 'long' are allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + X + "'.");
		
	}

	@Test
	public void testCreateFilterDataFieldWithInvalidStringRepresentation_Abc() throws Exception {
		
		try {
			new PatternDataField(ABC, PatternUsage.FILTER);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + ABC + "'. Only '*', '[lo-hi]' or 'long' are allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + ABC + "'.");
		
	}

	@Test
	public void testCreateGroupDataField() throws Exception {
		
		new PatternDataField(LONG, PatternUsage.GROUP);
		new PatternDataField(RANGE, PatternUsage.GROUP);
		new PatternDataField(ASTERISK, PatternUsage.GROUP);
		
	}

	@Test
	public void testCreateGroupDataFieldWithInvalidStringRepresentation_Abc() throws Exception {
		
		try {
			new PatternDataField(ABC, PatternUsage.GROUP);
		} catch(ECSpecValidationException e) {
			Assert.assertEquals("Invalid data field '" + ABC + "'. Only '*', 'X', '[lo-hi]' or 'long' are allowed.", e.getMessage());
			return;
		}
		
		Assert.fail("Should throw an ECSpecValidationException because the string representation of the data field contains a '" + ABC + "'.");
		
	}

	@Test
	public void testTypes() throws Exception {

		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		PatternDataField asteriskField = new PatternDataField(ASTERISK, PatternUsage.GROUP);
		PatternDataField xField = new PatternDataField(X, PatternUsage.GROUP);
		
		Assert.assertTrue(longField.isLong());
		Assert.assertFalse(longField.isRange());
		Assert.assertFalse(longField.isAsterisk());
		Assert.assertFalse(longField.isX());
		
		Assert.assertFalse(rangeField.isLong());
		Assert.assertTrue(rangeField.isRange());
		Assert.assertFalse(rangeField.isAsterisk());
		Assert.assertFalse(rangeField.isX());
		
		Assert.assertFalse(asteriskField.isLong());
		Assert.assertFalse(asteriskField.isRange());
		Assert.assertTrue(asteriskField.isAsterisk());
		Assert.assertFalse(asteriskField.isX());
		
		Assert.assertFalse(xField.isLong());
		Assert.assertFalse(xField.isRange());
		Assert.assertFalse(xField.isAsterisk());
		Assert.assertTrue(xField.isX());
		
	}

	@Test
	public void testGetValue() throws Exception {

		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		
		Assert.assertEquals(Long.parseLong(LONG), longField.getValue());
		
	}

	/**
	 * must throw an ECSpecValidationException because data field is not an of type long.
	 * @throws ECSpecValidationException test expected.
	 */
	@Test(expected = ECSpecValidationException.class)
	public void testGetValueWithNonIntDataField() throws ECSpecValidationException {	
		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		rangeField.getValue();		
	}

	@Test
	public void testGetLow() throws Exception {

		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		
		Assert.assertEquals(LOW, rangeField.getLow());
		
	}

	/**
	 * must throw an ECSpecValidationException because data field is not of type range.
	 * @throws ECSpecValidationException test expected.
	 */
	@Test(expected = ECSpecValidationException.class)
	public void testGetLowWithNonRangeDataField() throws Exception {
		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		longField.getLow();
	}

	@Test
	public void testGetHigh() throws Exception {

		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		
		Assert.assertEquals(HIGH, rangeField.getHigh());
		
	}

	/**
	 * must throw an ECSpecValidationException because data field is not of type range.
	 * @throws ECSpecValidationException test expected.
	 */
	@Test(expected = ECSpecValidationException.class)
	public void testGetHighWithNonRangeDataField() throws Exception {
		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		longField.getHigh();
	}

	@Test
	public void testIsDisjoint() throws Exception {

		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		PatternDataField asteriskField = new PatternDataField(ASTERISK, PatternUsage.GROUP);
		PatternDataField xField = new PatternDataField(X, PatternUsage.GROUP);
		
		PatternDataField disjointIntField = new PatternDataField(new Long(HIGH + 1).toString(), PatternUsage.GROUP);
		PatternDataField disjointRangeField = new PatternDataField("[" + new Long(HIGH + 100).toString() + "-" +
				new Long(HIGH + 200).toString() + "]", PatternUsage.GROUP);
		
		// test longField
		Assert.assertFalse(longField.isDisjoint(xField));
		Assert.assertFalse(longField.isDisjoint(asteriskField));
		Assert.assertFalse(longField.isDisjoint(longField));
		Assert.assertFalse(longField.isDisjoint(rangeField));
		Assert.assertTrue(longField.isDisjoint(disjointIntField));
		Assert.assertTrue(longField.isDisjoint(disjointRangeField));
		
		// test rangeField
		Assert.assertFalse(rangeField.isDisjoint(xField));
		Assert.assertFalse(rangeField.isDisjoint(asteriskField));
		Assert.assertFalse(rangeField.isDisjoint(longField));
		Assert.assertFalse(rangeField.isDisjoint(rangeField));
		Assert.assertTrue(rangeField.isDisjoint(disjointIntField));
		Assert.assertTrue(rangeField.isDisjoint(disjointRangeField));
		
		// test asteriskField
		Assert.assertFalse(asteriskField.isDisjoint(xField));
		Assert.assertFalse(asteriskField.isDisjoint(asteriskField));
		Assert.assertFalse(asteriskField.isDisjoint(longField));
		Assert.assertFalse(asteriskField.isDisjoint(rangeField));
		Assert.assertFalse(asteriskField.isDisjoint(disjointIntField));
		Assert.assertFalse(asteriskField.isDisjoint(disjointRangeField));
		
		// test xField
		Assert.assertFalse(xField.isDisjoint(xField));
		Assert.assertFalse(xField.isDisjoint(asteriskField));
		Assert.assertFalse(xField.isDisjoint(longField));
		Assert.assertFalse(xField.isDisjoint(rangeField));
		Assert.assertFalse(xField.isDisjoint(disjointIntField));
		Assert.assertFalse(xField.isDisjoint(disjointRangeField));
	}

	@Test
	public void testIsMember() throws Exception {
		
		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		PatternDataField asteriskField = new PatternDataField(ASTERISK, PatternUsage.GROUP);
		PatternDataField xField = new PatternDataField(X, PatternUsage.GROUP);
		
		long member = Long.parseLong(LONG);
		long nonMember = HIGH + 100;
		
		// test with member
		Assert.assertTrue(longField.isMember(member));
		Assert.assertTrue(rangeField.isMember(member));
		Assert.assertTrue(asteriskField.isMember(member));
		Assert.assertTrue(xField.isMember(member));
		
		// test with non member
		Assert.assertFalse(longField.isMember(nonMember));
		Assert.assertFalse(rangeField.isMember(nonMember));
		Assert.assertTrue(asteriskField.isMember(nonMember));
		Assert.assertTrue(xField.isMember(nonMember));
	}

	@Test
	public void testToString() throws Exception {

		PatternDataField longField = new PatternDataField(LONG, PatternUsage.GROUP);
		PatternDataField rangeField = new PatternDataField(RANGE, PatternUsage.GROUP);
		PatternDataField asteriskField = new PatternDataField(ASTERISK, PatternUsage.GROUP);
		PatternDataField xField = new PatternDataField(X, PatternUsage.GROUP);
		
		Assert.assertEquals(LONG, longField.toString());
		Assert.assertEquals(RANGE, rangeField.toString());
		Assert.assertEquals(ASTERISK, asteriskField.toString());
		Assert.assertEquals(X, xField.toString());
	}

}
