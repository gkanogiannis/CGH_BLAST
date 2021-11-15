/**
 * AppServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ciat.cgh.blast.ws.blastp;

public interface AppServicePortType extends java.rmi.Remote {
    public ciat.cgh.blast.ws.blastp.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.blastp.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.blastp.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.blastp.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.blastp.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.blastp.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.blastp.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
    public ciat.cgh.blast.ws.blastp.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType;
}
