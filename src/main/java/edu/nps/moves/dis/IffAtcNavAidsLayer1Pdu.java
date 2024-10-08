package edu.nps.moves.dis;

import java.io.*;

/**
 * 5.3.7.4.1: Navigational and IFF PDU. COMPLETE
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IffAtcNavAidsLayer1Pdu extends DistributedEmissionsFamilyPdu implements Serializable {

    private static final int LAYER_PRESENT = 4;

    private static final int LAYER_NOT_PRESENT = 0;
    /**
     * ID of the entity that is the source of the emissions
     */
    protected EntityID emittingEntityId = new EntityID();

    /**
     * Number generated by the issuing simulation to associate realted events.
     */
    protected EventID eventID = new EventID();

    /**
     * Location wrt entity. There is some ambugiuity in the standard here, but
     * this is the order it is listed in the table.
     */
    protected Vector3Float location = new Vector3Float();

    /**
     * System ID information
     */
    protected SystemID systemID = new SystemID();

    /**
     * padding
     */
    protected int pad2;

    /**
     * fundamental parameters
     */
    protected IffFundamentalData fundamentalData = new IffFundamentalData();

    /**
     * layer 2 value: 0 = not present, 1 = present
     */
    protected int layer2Value = 0;

    /**
     * layer 2 part of an IFF/ATC/NAVAIDS PDU
     */
    protected IffAtcNavAidsLayer2Pdu layer2 = null;

    /**
     * Constructor
     */
    public IffAtcNavAidsLayer1Pdu() {
        setPduType((short) 28);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + emittingEntityId.getMarshalledSize();  // emittingEntityId
        marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
        marshalSize = marshalSize + location.getMarshalledSize();  // location
        marshalSize = marshalSize + systemID.getMarshalledSize();  // systemID
        marshalSize = marshalSize + 2;  // pad2
        marshalSize = marshalSize + fundamentalData.getMarshalledSize();  // fundamentalData
        if ((this.getLayer2Value() & LAYER_PRESENT) == LAYER_PRESENT) {
            marshalSize = marshalSize + layer2.getMarshalledSize();
        }
        return marshalSize;
    }

    public void setEmittingEntityId(EntityID pEmittingEntityId) {
        emittingEntityId = pEmittingEntityId;
    }

    public EntityID getEmittingEntityId() {
        return emittingEntityId;
    }

    public void setEventID(EventID pEventID) {
        eventID = pEventID;
    }

    public EventID getEventID() {
        return eventID;
    }

    public void setLocation(Vector3Float pLocation) {
        location = pLocation;
    }

    public Vector3Float getLocation() {
        return location;
    }

    public void setSystemID(SystemID pSystemID) {
        systemID = pSystemID;
    }

    public SystemID getSystemID() {
        return systemID;
    }

    public void setPad2(int pPad2) {
        pad2 = pPad2;
    }

    public int getPad2() {
        return pad2;
    }

    public void setFundamentalData(IffFundamentalData pFundamentalData) {
        fundamentalData = pFundamentalData;
    }

    public IffFundamentalData getFundamentalData() {
        return fundamentalData;
    }

    public void setLayer2Value(int pLayer2Value) {
        short newInformationLayer;
        this.layer2Value = pLayer2Value << 2;
        newInformationLayer = (short) (this.getFundamentalData().getInformationLayers() & 0xFB);
        newInformationLayer = (short) (newInformationLayer | this.layer2Value);
        this.getFundamentalData().setInformationLayers(newInformationLayer);
    }

    public int getLayer2Value() {
        this.layer2Value = this.getFundamentalData().getInformationLayers() & 0x4;    // Mask out everything except bit 2
        return this.layer2Value;
    }

    public void setIffAtcNavAidsLayer2(IffAtcNavAidsLayer2Pdu pLayer2) {
        this.layer2 = pLayer2;
        short data = getFundamentalData().getInformationLayers();
        data = (short) (data | (1 << 2)); //set bit 2 indicating that layer 2 is present
        getFundamentalData().setInformationLayers(data);

        this.layer2.getLayerHeader().setLayerNumber((short) 2);
        this.layer2.getLayerHeader().setLayerSpecificInformaiton((short) 0);
    }

    public IffAtcNavAidsLayer2Pdu getIffAtcNavAidsLayer2() {
        return this.layer2;
    }

    /**
     * Packs a Pdu into the ByteBuffer.
     *
     * @throws java.nio.BufferOverflowException if buff is too small
     * @throws java.nio.ReadOnlyBufferException if buff is read only
     * @see java.nio.ByteBuffer
     * @param buff The ByteBuffer at the position to begin writing
     * @since ??
     */
    public void marshal(java.nio.ByteBuffer buff) {
        super.marshal(buff);
        emittingEntityId.marshal(buff);
        eventID.marshal(buff);
        location.marshal(buff);
        systemID.marshal(buff);
        buff.putShort((short) pad2);
        fundamentalData.marshal(buff);
        if ((this.getLayer2Value() & LAYER_PRESENT) == LAYER_PRESENT) {
            layer2.marshal(buff);
        }
    } // end of marshal method

    /**
     * Unpacks a Pdu from the underlying data.
     *
     * @throws java.nio.BufferUnderflowException if buff is too small
     * @see java.nio.ByteBuffer
     * @param buff The ByteBuffer at the position to begin reading
 * @throws DISException 
     * @since ??
     */
public void unmarshal(java.nio.ByteBuffer buff) throws DISException
{
        super.unmarshal(buff);

        emittingEntityId.unmarshal(buff);
        eventID.unmarshal(buff);
        location.unmarshal(buff);
        systemID.unmarshal(buff);
        pad2 = (int) (buff.getShort() & 0xFFFF);
        fundamentalData.unmarshal(buff);

        if ((this.getLayer2Value() & LAYER_PRESENT) == LAYER_PRESENT) {
            layer2 = new IffAtcNavAidsLayer2Pdu();
            layer2.unmarshal(buff);
        }
    } // end of unmarshal method 


    /*
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        return equalsImpl(obj);
    }

    @Override
    public boolean equalsImpl(Object obj) {
        boolean ivarsEqual = true;

        if (!(obj instanceof IffAtcNavAidsLayer1Pdu)) {
            return false;
        }

        final IffAtcNavAidsLayer1Pdu rhs = (IffAtcNavAidsLayer1Pdu) obj;

        if (!(emittingEntityId.equals(rhs.emittingEntityId))) {
            ivarsEqual = false;
        }
        if (!(eventID.equals(rhs.eventID))) {
            ivarsEqual = false;
        }
        if (!(location.equals(rhs.location))) {
            ivarsEqual = false;
        }
        if (!(systemID.equals(rhs.systemID))) {
            ivarsEqual = false;
        }
        if (!(pad2 == rhs.pad2)) {
            ivarsEqual = false;
        }
        if (!(fundamentalData.equals(rhs.fundamentalData))) {
            ivarsEqual = false;
        }

        if ((this.getLayer2Value() & LAYER_PRESENT) == LAYER_PRESENT) {
            if (!(layer2.equals(rhs.layer2))) {
                ivarsEqual = false;
            }
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
