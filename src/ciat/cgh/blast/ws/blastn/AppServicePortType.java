/**
 * AppServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ciat.cgh.blast.ws.blastn;

public interface AppServicePortType extends java.rmi.Remote {
    public ciat.cgh.blast.ws.blastn.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.blastn.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.blastn.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.blastn.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.blastn.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.blastn.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.blastn.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
    public ciat.cgh.blast.ws.blastn.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastn.types.FaultType;
}
