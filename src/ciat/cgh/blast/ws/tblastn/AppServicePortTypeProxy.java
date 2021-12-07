/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.tblastn.AppServicePortTypeProxy
 *
 * Copyright (C) 2021 Anestis Gkanogiannis <anestis@gkanogiannis.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 *
 */
package ciat.cgh.blast.ws.tblastn;

public class AppServicePortTypeProxy implements ciat.cgh.blast.ws.tblastn.AppServicePortType {
  private String _endpoint = null;
  private ciat.cgh.blast.ws.tblastn.AppServicePortType appServicePortType = null;
  
  public AppServicePortTypeProxy() {
    _initAppServicePortTypeProxy();
  }
  
  public AppServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAppServicePortTypeProxy();
  }
  
  private void _initAppServicePortTypeProxy() {
    try {
      appServicePortType = (new ciat.cgh.blast.ws.tblastn.AppServiceLocator()).getAppServicePort();
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
  
  public ciat.cgh.blast.ws.tblastn.AppServicePortType getAppServicePortType() {
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType;
  }
  
  public ciat.cgh.blast.ws.tblastn.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.tblastn.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getAppMetadata(getAppMetadataInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.tblastn.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getAppConfig(getAppConfigInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.tblastn.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getSystemInfo(getSystemInfoInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.tblastn.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.launchJob(launchJobInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.tblastn.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.launchJobBlocking(launchJobBlockingInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.queryStatus(queryStatusInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getJobStatistics(getJobStatisticsInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getOutputs(getOutputsInput);
  }
  
  public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.tblastn.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.getOutputAsBase64ByName(getOutputAsBase64ByNameInput);
  }
  
  public ciat.cgh.blast.ws.tblastn.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.tblastn.types.FaultType{
    if (appServicePortType == null)
      _initAppServicePortTypeProxy();
    return appServicePortType.destroy(destroyInput);
  }
  
  
}