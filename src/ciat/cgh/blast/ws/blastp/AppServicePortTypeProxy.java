package ciat.cgh.blast.ws.blastp;

public class AppServicePortTypeProxy implements ciat.cgh.blast.ws.blastp.AppServicePortType {
  private String _endpoint = null;
  private ciat.cgh.blast.ws.blastp.AppServicePortType appServicePortType = null;
  
  public AppServicePortTypeProxy() {
    _initAppServicePortTypeProxy();
  }
  
  public AppServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAppServicePortTypeProxy();
  }
  
  private void _initAppServicePortTypeProxy() {
    try {
      appServicePortType = (new ciat.cgh.blast.ws.blastp.AppServiceLocator()).getAppServicePort();
      if (appServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)appServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)appServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (appServicePortType != null)
      ((javax.xml.rpc.Stub)appServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ciat.cgh.blast.ws.blastp.AppServicePortType getAppServicePortType() {
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType;
  }
  
  public ciat.cgh.blast.ws.blastp.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.blastp.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getAppMetadata(getAppMetadataInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.blastp.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getAppConfig(getAppConfigInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.blastp.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getSystemInfo(getSystemInfoInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.blastp.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.launchJob(launchJobInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.blastp.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.launchJobBlocking(launchJobBlockingInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.queryStatus(queryStatusInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getJobStatistics(getJobStatisticsInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getOutputs(getOutputsInput);
  }
  
  public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.blastp.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getOutputAsBase64ByName(getOutputAsBase64ByNameInput);
  }
  
  public ciat.cgh.blast.ws.blastp.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastp.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.destroy(destroyInput);
  }
  
  
}