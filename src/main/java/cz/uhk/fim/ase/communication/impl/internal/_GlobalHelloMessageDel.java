// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.0
//
// <auto-generated>
//
// Generated from file `communication.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package cz.uhk.fim.ase.communication.impl.internal;

public interface _GlobalHelloMessageDel extends Ice._ObjectDel
{
    String getInstanceId(java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;

    cz.uhk.fim.ase.model.internal.AgentEntity[] getAgents(java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper;
}