/**
 * AppServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ciat.cgh.blast.ws.tblastx;

public interface AppServicePortType extends java.rmi.Remote {
    public ciat.cgh.blast.ws.tblastx.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.tblastx.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.tblastx.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.tblastx.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.tblastx.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.tblastx.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.tblastx.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
    public ciat.cgh.blast.ws.tblastx.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastx.types.FaultType;
}
