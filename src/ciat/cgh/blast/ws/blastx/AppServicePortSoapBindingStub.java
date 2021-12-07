/*
 *
 * CGH_BLAST ciat.cgh.blast.ws.blastx.AppServicePortSoapBindingStub
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

package ciat.cgh.blast.ws.blastx;

public class AppServicePortSoapBindingStub extends org.apache.axis.client.Stub implements ciat.cgh.blast.ws.blastx.AppServicePortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[10];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAppMetadata");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getAppMetadataInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppMetadataInputType"), ciat.cgh.blast.ws.blastx.types.AppMetadataInputType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppMetadataType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.AppMetadataType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getAppMetadataOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAppConfig");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getAppConfigInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppConfigInputType"), ciat.cgh.blast.ws.blastx.types.AppConfigInputType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppConfigType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.AppConfigType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getAppConfigOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSystemInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getSystemInfoInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "SystemInfoInputType"), ciat.cgh.blast.ws.blastx.types.SystemInfoInputType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "SystemInfoType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.SystemInfoType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getSystemInfoOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("launchJob");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "launchJobInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobInputType"), ciat.cgh.blast.ws.blastx.types.JobInputType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobSubOutputType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.JobSubOutputType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "launchJobOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("launchJobBlocking");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "launchJobBlockingInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobInputType"), ciat.cgh.blast.ws.blastx.types.JobInputType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "BlockingOutputType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.BlockingOutputType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "launchJobBlockingOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "queryStatusInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "StatusOutputType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.StatusOutputType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "queryStatusOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getJobStatistics");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getJobStatisticsInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobStatisticsType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.JobStatisticsType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getJobStatisticsOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getOutputs");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getOutputsInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobOutputType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.JobOutputType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getOutputsOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getOutputAsBase64ByName");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getOutputAsBase64ByNameInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "OutputsByNameInputType"), ciat.cgh.blast.ws.blastx.types.OutputsByNameInputType.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", ">getOutputAsBase64ByNameOutput"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "getOutputAsBase64ByNameOutput"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("", "item"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("destroy");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "destroyInput"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "StatusOutputType"));
        oper.setReturnClass(ciat.cgh.blast.ws.blastx.types.StatusOutputType.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "destroyOutput"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "opalFaultOutput"),
                      "edu.sdsc.nbcr.opal.types.FaultType",
                      new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType"), 
                      true
                     ));
        _operations[9] = oper;

    }

    public AppServicePortSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public AppServicePortSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public AppServicePortSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", ">getOutputAsBase64ByNameOutput");
            cachedSerQNames.add(qName);
            cls = byte[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppConfigInputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.AppConfigInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppConfigType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.AppConfigType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppMetadataInputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.AppMetadataInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "AppMetadataType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.AppMetadataType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ArgumentsType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.ArgumentsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "BlockingOutputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.BlockingOutputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FaultType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.FaultType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FlagsArrayType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.FlagsType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FlagsType");
            qName2 = new javax.xml.namespace.QName("", "flag");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "FlagsType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.FlagsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "GroupsArrayType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.GroupsType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "GroupsType");
            qName2 = new javax.xml.namespace.QName("", "group");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "GroupsType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.GroupsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ImplicitParamsArrayType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.ImplicitParamsType[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ImplicitParamsType");
            qName2 = new javax.xml.namespace.QName("", "param");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ImplicitParamsType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.ImplicitParamsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "InputFileType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.InputFileType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "IOType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.IOType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobInputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.JobInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobOutputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.JobOutputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobStatisticsType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.JobStatisticsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "JobSubOutputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.JobSubOutputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "OutputFileType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.OutputFileType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "OutputsByNameInputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.OutputsByNameInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ParamsArrayType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.ParamsArrayType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ParamsType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.ParamsType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "ParamType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.ParamType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "StatusOutputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.StatusOutputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "SystemInfoInputType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.SystemInfoInputType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://nbcr.sdsc.edu/opal/types", "SystemInfoType");
            cachedSerQNames.add(qName);
            cls = ciat.cgh.blast.ws.blastx.types.SystemInfoType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public ciat.cgh.blast.ws.blastx.types.AppMetadataType getAppMetadata(ciat.cgh.blast.ws.blastx.types.AppMetadataInputType getAppMetadataInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/getAppMetadata");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAppMetadata"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAppMetadataInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.AppMetadataType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.AppMetadataType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.AppMetadataType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.AppConfigType getAppConfig(ciat.cgh.blast.ws.blastx.types.AppConfigInputType getAppConfigInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/getAppConfig");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getAppConfig"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getAppConfigInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.AppConfigType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.AppConfigType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.AppConfigType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.SystemInfoType getSystemInfo(ciat.cgh.blast.ws.blastx.types.SystemInfoInputType getSystemInfoInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/getSystemInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getSystemInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getSystemInfoInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.SystemInfoType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.SystemInfoType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.SystemInfoType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.JobSubOutputType launchJob(ciat.cgh.blast.ws.blastx.types.JobInputType launchJobInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/launchJob");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "launchJob"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {launchJobInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.JobSubOutputType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.JobSubOutputType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.JobSubOutputType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.BlockingOutputType launchJobBlocking(ciat.cgh.blast.ws.blastx.types.JobInputType launchJobBlockingInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/launchJobBlocking");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "launchJobBlocking"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {launchJobBlockingInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.BlockingOutputType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.BlockingOutputType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.BlockingOutputType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.StatusOutputType queryStatus(java.lang.String queryStatusInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/queryStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "queryStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {queryStatusInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.StatusOutputType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.StatusOutputType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.StatusOutputType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.JobStatisticsType getJobStatistics(java.lang.String getJobStatisticsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/getJobStatistics");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getJobStatistics"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getJobStatisticsInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.JobStatisticsType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.JobStatisticsType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.JobStatisticsType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.JobOutputType getOutputs(java.lang.String getOutputsInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/getOutputs");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getOutputs"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getOutputsInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.JobOutputType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.JobOutputType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.JobOutputType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] getOutputAsBase64ByName(ciat.cgh.blast.ws.blastx.types.OutputsByNameInputType getOutputAsBase64ByNameInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/getOutputAsBase64ByName");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getOutputAsBase64ByName"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getOutputAsBase64ByNameInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public ciat.cgh.blast.ws.blastx.types.StatusOutputType destroy(java.lang.String destroyInput) throws java.rmi.RemoteException, ciat.cgh.blast.ws.blastx.types.FaultType {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://nbcr.sdsc.edu/opal/destroy");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "destroy"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {destroyInput});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (ciat.cgh.blast.ws.blastx.types.StatusOutputType) _resp;
            } catch (java.lang.Exception _exception) {
                return (ciat.cgh.blast.ws.blastx.types.StatusOutputType) org.apache.axis.utils.JavaUtils.convert(_resp, ciat.cgh.blast.ws.blastx.types.StatusOutputType.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof ciat.cgh.blast.ws.blastx.types.FaultType) {
              throw (ciat.cgh.blast.ws.blastx.types.FaultType) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
