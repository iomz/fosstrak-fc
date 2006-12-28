/*
 * Copyright (c) 2006, ETH Zurich
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * - Neither the name of the ETH Zurich nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.accada.ale.server;

import org.accada.ale.wsdl.ale.epcglobal.ECSpecValidationException;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
import org.accada.ale.wsdl.ale.epcglobal.ImplementationExceptionSeverity;

/**
 * This clas represents a data field of a tag, filter or group pattern.
 * 
 * @author regli
 */
public class PatternDataField {

	/** string representation of this data field */
	private final String stringRepresentation;
	/** indicates if this datafield contains an asterisk */
	private final boolean isAsterisk;
	/** indicates if this datafield conains an X */
	private final boolean isX;
	/** indicates if this data field contains an int */
	private final boolean isInt;
	/** indicates if this data field contains a range */
	private final boolean isRange;
	
	/** value of this data field if it is an int field */
	private int value;
	/** lower limit of the range of this data field if this data field contains a range */ 
	private int low;
	/** higher limit of the range of this data field if this data field contains a range */
	private int high;

	/**
	 * Constructor parses and validates the string representation of the data field.
	 * 
	 * @param stringRepresentation of the data field
	 * @param usage of the pattern this data field belongs to
	 * @throws ECSpecValidationException if the data field is invalid
	 */
	public PatternDataField(String stringRepresentation, PatternUsage usage) throws ECSpecValidationException {
		
		this.stringRepresentation = stringRepresentation;
		
		// check for asterisk
		if ((usage == PatternUsage.FILTER || usage == PatternUsage.GROUP) && "*".equals(stringRepresentation)) {
			isAsterisk  = true;
			isX = false;
			isInt = false;
			isRange = false;
			return;
		} else {
			isAsterisk = false;
		}
		
		// check for X if usage is group
		if (usage == PatternUsage.GROUP && "X".equals(stringRepresentation)) {
			isX = true;
			isInt = false;
			isRange = false;
			return;
		} else {
			isX = false;
		}
		
		// check for int
		try {
			value = Integer.parseInt(stringRepresentation);
		} catch(NumberFormatException e) {
			
			if (usage == PatternUsage.TAG) {
				throw new ECSpecValidationException("Invalid data field '" + stringRepresentation + "'. " +
						"Only 'int' is allowed.");
			}
			
			// check for range
			String[] parts = stringRepresentation.split("-");
			if (parts.length != 2 || !parts[0].startsWith("[") || !parts[1].endsWith("]")) {
				throw new ECSpecValidationException("Invalid data field '" + stringRepresentation + "'. " +
						"Only '*', " + (usage == PatternUsage.GROUP ? "'X', " : "") + "'[lo-hi]' or 'int' are allowed.");
			}
			try {
				low = Integer.parseInt(parts[0].substring(1));
				high = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
			} catch (NumberFormatException e1) {
				throw new ECSpecValidationException("Invalid data field '" + stringRepresentation + "'. " +
						"Only '*', " + (usage == PatternUsage.GROUP ? "'X', " : "") + "'[lo-hi]' or 'int' are allowed.");
			}
			
			// check if low is lower than high
			if (low <= high) {
				isRange = true;
			} else {
				throw new ECSpecValidationException("Invalid range '" + stringRepresentation + "'. " +
						"Range must have the form '[lo-hi]' with lo <= hi.");
			}
			isInt = false;
			return;
		}

		// check if value is positive
		if (value >= 0) {
			isInt = true;
		} else {
			throw new ECSpecValidationException("Invalid data field '" + stringRepresentation + "' in pattern '. " +
					"Only positive int is allowed.");
		}
		isRange = false;
		
	}
	
	/**
	 * This method indicates if this data field contains an asterisk.
	 * 
	 * @return true if this data field contains an asterisk and false otherwise
	 */
	public boolean isAsterisk() {
		
		return isAsterisk;
		
	}
	
	/**
	 * This method indicates if this data field contains an X.
	 * 
	 * @return true if this data field contains an X and false otherwise
	 */
	public boolean isX() {
		
		return isX;
		
	}
	
	/**
	 * This method indicates if this data field contains an int.
	 * 
	 * @return true if this data field contains an int and false otherwise
	 */
	public boolean isInt() {
		
		return isInt;
	}
	
	/**
	 * This method indicates if this data field contains a range.
	 * 
	 * @return true if this data field contains a range and false otherwise 
	 */
	public boolean isRange() {
		
		return isRange;
		
	}
	
	/**
	 * This method returns the value of this data field if this data field contains an int
	 * and throws an exception otherwise.
	 * 
	 * @return value of data field
	 * @throws ImplementationException if the data field contains not an int
	 */
	public int getValue() throws ImplementationException {

		if (isInt) {
			return value;
		} else {
			throw new ImplementationException("Data field is not an int.", ImplementationExceptionSeverity.ERROR);
		}
		
	}
	
	/**
	 * This method returns the lower limit of the range of this data field if this data field
	 * contains a range and throws an exception otherwise.
	 * 
	 * @return lower limit of the range of this data field
	 * @throws ImplementationException if the data field contains not a range
	 */
	public int getLow() throws ImplementationException {
		
		if (isRange) {
			return low;
		} else {
			throw new ImplementationException("Data field is not a range.", ImplementationExceptionSeverity.ERROR);
		}
		
	}
	
	/**
	 * This method returns the higher limit of the range of this data field if this data field
	 * contains a range and throws an exception otherwise.
	 * 
	 * @return higher limit of the range of this data field
	 * @throws ImplementationException if the data field contains not a range
	 */
	public int getHigh() throws ImplementationException {
		
		if (isRange) {
			return high;
		} else {
			throw new ImplementationException("Data field is not a range.", ImplementationExceptionSeverity.ERROR);
		}
		
	}
	
	/**
	 * This method indicates if this data field is disjoint to the specified data field.
	 * 
	 * @param field to check disjointness
	 * @return true if the data field are disjoint and false otherwise
	 * @throws ImplementationException if an implementation exception occurs
	 */
	public boolean isDisjoint(PatternDataField field) throws ImplementationException {

		if (isAsterisk || isX || field.isAsterisk() || field.isX()) {
			return false;
		}
		if (isInt) {
			if (field.isInt()) {
				if (value == field.getValue()) {
					return false;
				} else {
					return true;
				}
			} else if (field.isRange()) {
				if (field.getLow() <= value && value <= field.getHigh()) {
					return false;
				} else {
					return true;
				}
			}
		} else if (isRange) {
			if (field.isInt()) {
				if (low <= field.getValue() && field.getValue() <= high) {
					return false;
				} else {
					return true;
				}
			} else if (field.isRange()) {
				if ((field.getLow() <= low && low <= field.getHigh()) || (field.getLow() <= high && high <= field.getHigh())) {
					return false;
				} else {
					return true;
				}
			}
		}
		return false;
		
	}
	
	/**
	 * This method indicates if the specified field value is a member of this data field.
	 * 
	 * @param fieldValue to check for member.
	 * @return true if the specified value is member of this data field
	 */
	public boolean isMember(int fieldValue) {
		
		if (isInt) {
			
			// if pattern field is an int, the tag field must be the same int
			if (value != fieldValue) {
				return false;
			}
		} else if (isRange) {
			
			// if pattern field is a range, the tag field must be in this range
			if (fieldValue < low || fieldValue > high) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	/**
	 * This method returns a string representation of this data field.
	 * 
	 * @return string representation
	 */
	public String toString() {
		
		return stringRepresentation;
		
	}
	
}