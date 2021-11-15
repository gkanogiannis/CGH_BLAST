/**
 * AppServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ciat.cgh.blast.ws.tblastn;

public interface AppServicePortType extends java.rmi.Remote {
    public ciat.cgh.blast.ws.tblastn.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.tblastn.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.tblastn.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.tblastn.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.tblastn.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.tblastn.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.tblastn.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
    public ciat.cgh.blast.ws.tblastn.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType;
}
