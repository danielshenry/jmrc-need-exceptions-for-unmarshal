package edu.nps.moves.dis;

import java.io.*;

/**
 * Section 5.3.12: Abstract superclass for reliable simulation management PDUs
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SimulationManagementWithReliabilityFamilyPdu extends Pdu implements Serializable {

    /**
     * Object originatig the request
     */
    protected EntityID originatingEntityID = new EntityID();

    /**
     * Object with which this point object is associated
     */
    protected EntityID receivingEntityID = new EntityID();

    /**
     * Constructor
     */
    public SimulationManagementWithReliabilityFamilyPdu() {
        setProtocolFamily((short) 10);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + originatingEntityID.getMarshalledSize();  // originatingEntityID
        marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID

        return marshalSize;
    }

    public void setOriginatingEntityID(EntityID pOriginatingEntityID) {
        originatingEntityID = pOriginatingEntityID;
    }

    public EntityID getOriginatingEntityID() {
        return originatingEntityID;
    }

    public void setReceivingEntityID(EntityID pReceivingEntityID) {
        receivingEntityID = pReceivingEntityID;
    }

    public EntityID getReceivingEntityID() {
        return receivingEntityID;
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
        originatingEntityID.marshal(buff);
        receivingEntityID.marshal(buff);
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

        originatingEntityID.unmarshal(buff);
        receivingEntityID.unmarshal(buff);
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

        if (!(obj instanceof SimulationManagementWithReliabilityFamilyPdu)) {
            return false;
        }

        final SimulationManagementWithReliabilityFamilyPdu rhs = (SimulationManagementWithReliabilityFamilyPdu) obj;

        if (!(originatingEntityID.equals(rhs.originatingEntityID))) {
            ivarsEqual = false;
        }
        if (!(receivingEntityID.equals(rhs.receivingEntityID))) {
            ivarsEqual = false;
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
