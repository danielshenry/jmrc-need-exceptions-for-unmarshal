package edu.nps.moves.dis;

import java.io.*;

/**
 * Section 5.3.3. Common superclass for EntityState, Collision,
 * collision-elastic, and entity state update PDUs. This should be abstract.
 * COMPLETE
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EntityInformationFamilyPdu extends Pdu implements Serializable {

    /**
     * Constructor
     */
    public EntityInformationFamilyPdu() {
        setProtocolFamily((short) 1);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();

        return marshalSize;
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

        if (!(obj instanceof EntityInformationFamilyPdu)) {
            return false;
        }

        final EntityInformationFamilyPdu rhs = (EntityInformationFamilyPdu) obj;

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
