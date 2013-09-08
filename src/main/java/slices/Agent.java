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
// Generated from file `Slices.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package slices;

public class Agent implements java.lang.Cloneable, java.io.Serializable
{
    public String id;

    public ContainerAddress container;

    public AgentCoordinates position;

    public Agent()
    {
    }

    public Agent(String id, ContainerAddress container, AgentCoordinates position)
    {
        this.id = id;
        this.container = container;
        this.position = position;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Agent _r = null;
        if(rhs instanceof Agent)
        {
            _r = (Agent)rhs;
        }

        if(_r != null)
        {
            if(id != _r.id)
            {
                if(id == null || _r.id == null || !id.equals(_r.id))
                {
                    return false;
                }
            }
            if(container != _r.container)
            {
                if(container == null || _r.container == null || !container.equals(_r.container))
                {
                    return false;
                }
            }
            if(position != _r.position)
            {
                if(position == null || _r.position == null || !position.equals(_r.position))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::slices::Agent");
        __h = IceInternal.HashUtil.hashAdd(__h, id);
        __h = IceInternal.HashUtil.hashAdd(__h, container);
        __h = IceInternal.HashUtil.hashAdd(__h, position);
        return __h;
    }

    public java.lang.Object
    clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(id);
        container.__write(__os);
        position.__write(__os);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        id = __is.readString();
        container = new ContainerAddress();
        container.__read(__is);
        position = new AgentCoordinates();
        position.__read(__is);
    }

    public static final long serialVersionUID = -1102744475L;
}
