/**
 * ALEServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.accada.ale.wsdl.ale.epcglobal;

public interface ALEServicePortType extends java.rmi.Remote {
    public org.accada.ale.wsdl.ale.epcglobal.VoidHolder define(org.accada.ale.wsdl.ale.epcglobal.Define parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.ECSpecValidationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.DuplicateNameException;
    public org.accada.ale.wsdl.ale.epcglobal.VoidHolder undefine(org.accada.ale.wsdl.ale.epcglobal.Undefine parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.xsd.ale.epcglobal.ECSpec getECSpec(org.accada.ale.wsdl.ale.epcglobal.GetECSpec parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public java.lang.String[] getECSpecNames(org.accada.ale.wsdl.ale.epcglobal.EmptyParms parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.wsdl.ale.epcglobal.VoidHolder subscribe(org.accada.ale.wsdl.ale.epcglobal.Subscribe parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.DuplicateSubscriptionException, org.accada.ale.wsdl.ale.epcglobal.InvalidURIException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.wsdl.ale.epcglobal.VoidHolder unsubscribe(org.accada.ale.wsdl.ale.epcglobal.Unsubscribe parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.InvalidURIException, org.accada.ale.wsdl.ale.epcglobal.NoSuchSubscriberException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.xsd.ale.epcglobal.ECReports poll(org.accada.ale.wsdl.ale.epcglobal.Poll parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.xsd.ale.epcglobal.ECReports immediate(org.accada.ale.wsdl.ale.epcglobal.Immediate parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.ECSpecValidationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public java.lang.String[] getSubscribers(org.accada.ale.wsdl.ale.epcglobal.GetSubscribers parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public java.lang.String getStandardVersion(org.accada.ale.wsdl.ale.epcglobal.EmptyParms parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
    public java.lang.String getVendorVersion(org.accada.ale.wsdl.ale.epcglobal.EmptyParms parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException;
    public org.accada.ale.wsdl.ale.epcglobal.DefineReaderResult defineReader(org.accada.ale.wsdl.ale.epcglobal.DefineReader parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.DuplicateNameException, org.accada.ale.wsdl.ale.epcglobal.ValidationException;
    public org.accada.ale.wsdl.ale.epcglobal.UpdateReaderResult updateReader(org.accada.ale.wsdl.ale.epcglobal.UpdateReader parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImmutableReaderException, org.accada.ale.wsdl.ale.epcglobal.InUseException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.ReaderLoopException, org.accada.ale.wsdl.ale.epcglobal.ValidationException;
    public org.accada.ale.wsdl.ale.epcglobal.UndefineReaderResult undefineReader(org.accada.ale.wsdl.ale.epcglobal.UndefineReader parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImmutableReaderException, org.accada.ale.wsdl.ale.epcglobal.InUseException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public java.lang.String[] getLogicalReaderNames(org.accada.ale.wsdl.ale.epcglobal.EmptyParms parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.xsd.ale.epcglobal.LRSpec getLRSpec(org.accada.ale.wsdl.ale.epcglobal.GetLRSpec parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
    public org.accada.ale.wsdl.ale.epcglobal.AddReadersResult addReaders(org.accada.ale.wsdl.ale.epcglobal.AddReaders parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImmutableReaderException, org.accada.ale.wsdl.ale.epcglobal.InUseException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.NonCompositeReaderException, org.accada.ale.wsdl.ale.epcglobal.ReaderLoopException, org.accada.ale.wsdl.ale.epcglobal.ValidationException;
    public org.accada.ale.wsdl.ale.epcglobal.SetReadersResult setReaders(org.accada.ale.wsdl.ale.epcglobal.SetReaders parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImmutableReaderException, org.accada.ale.wsdl.ale.epcglobal.InUseException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.NonCompositeReaderException, org.accada.ale.wsdl.ale.epcglobal.ReaderLoopException, org.accada.ale.wsdl.ale.epcglobal.ValidationException;
    public org.accada.ale.wsdl.ale.epcglobal.RemoveReadersResult removeReaders(org.accada.ale.wsdl.ale.epcglobal.RemoveReaders parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImmutableReaderException, org.accada.ale.wsdl.ale.epcglobal.InUseException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.NonCompositeReaderException;
    public org.accada.ale.wsdl.ale.epcglobal.SetPropertiesResult setProperties(org.accada.ale.wsdl.ale.epcglobal.SetProperties parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.ImmutableReaderException, org.accada.ale.wsdl.ale.epcglobal.InUseException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException, org.accada.ale.wsdl.ale.epcglobal.ValidationException;
    public java.lang.String getPropertyValue(org.accada.ale.wsdl.ale.epcglobal.GetPropertyValue parms) throws java.rmi.RemoteException, org.accada.ale.wsdl.ale.epcglobal.NoSuchNameException, org.accada.ale.wsdl.ale.epcglobal.ImplementationException, org.accada.ale.wsdl.ale.epcglobal.SecurityException;
}
