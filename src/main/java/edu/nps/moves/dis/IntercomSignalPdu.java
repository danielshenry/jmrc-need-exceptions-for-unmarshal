package edu.nps.moves.dis;

import java.util.*;
import java.io.*;

/**
 * Section 5.3.8.4. Actual transmission of intercome voice data. COMPLETE
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IntercomSignalPdu extends RadioCommunicationsFamilyPdu implements Serializable {

    /**
     * ID of the entitythat is the source of the communication
     */
    protected EntityID entityId = new EntityID();

    /**
     * particular radio within an entity
     */
    protected int communicationsDeviceID;

    /**
     * encoding scheme
     */
    protected int encodingScheme;

    /**
     * tactical data link type
     */
    protected int tdlType;

    /**
     * sample rate
     */
    protected long sampleRate;

    /**
     * data length, in bits
     */
    protected int dataLength;

    /**
     * samples
     */
    protected int samples;

    /**
     * data bytes
     */
    protected byte[] data = new byte[0];

    /**
     * Constructor
     */
    public IntercomSignalPdu() {
        setPduType((short) 31);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + entityId.getMarshalledSize();  // entityId
        marshalSize = marshalSize + 2;  // communicationsDeviceID
        marshalSize = marshalSize + 2;  // encodingScheme
        marshalSize = marshalSize + 2;  // tdlType
        marshalSize = marshalSize + 4;  // sampleRate
        marshalSize = marshalSize + 2;  // dataLength
        marshalSize = marshalSize + 2;  // samples
        marshalSize = marshalSize + data.length;

        return marshalSize;
    }

    public void setEntityId(EntityID pEntityId) {
        entityId = pEntityId;
    }

    public EntityID getEntityId() {
        return entityId;
    }

    public void setCommunicationsDeviceID(int pCommunicationsDeviceID) {
        communicationsDeviceID = pCommunicationsDeviceID;
    }

    public int getCommunicationsDeviceID() {
        return communicationsDeviceID;
    }

    public void setEncodingScheme(int pEncodingScheme) {
        encodingScheme = pEncodingScheme;
    }

    public int getEncodingScheme() {
        return encodingScheme;
    }

    public void setTdlType(int pTdlType) {
        tdlType = pTdlType;
    }

    public int getTdlType() {
        return tdlType;
    }

    public void setSampleRate(long pSampleRate) {
        sampleRate = pSampleRate;
    }

    public long getSampleRate() {
        return sampleRate;
    }

    public int getDataLength() {
        return data.length;
    }

    /**
     * Note that setting this value will not change the marshalled value. The
     * list whose length this describes is used for that purpose. The
     * getdataLength method will also be based on the actual list length rather
     * than this value. The method is simply here for java bean completeness.
     */
    public void setDataLength(int pDataLength) {
        dataLength = pDataLength;
    }

    public void setSamples(int pSamples) {
        samples = pSamples;
    }

    public int getSamples() {
        return samples;
    }

    public void setData(byte[] pData) {
        data = pData;
    }

    public byte[] getData() {
        return data;
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
        entityId.marshal(buff);
        buff.putShort((short) communicationsDeviceID);
        buff.putShort((short) encodingScheme);
        buff.putShort((short) tdlType);
        buff.putInt((int) sampleRate);
        buff.putShort((short) data.length);
        buff.putShort((short) samples);
        buff.put(data);
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

        entityId.unmarshal(buff);
        communicationsDeviceID = (int) (buff.getShort() & 0xFFFF);
        encodingScheme = (int) (buff.getShort() & 0xFFFF);
        tdlType = (int) (buff.getShort() & 0xFFFF);
        sampleRate = buff.getInt();
        dataLength = (int) (buff.getShort() & 0xFFFF);
        samples = (int) (buff.getShort() & 0xFFFF);
        data = new byte[dataLength];
        buff.get(data);
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

        if (!(obj instanceof IntercomSignalPdu)) {
            return false;
        }

        final IntercomSignalPdu rhs = (IntercomSignalPdu) obj;

        if (!(entityId.equals(rhs.entityId))) {
            ivarsEqual = false;
        }
        if (!(communicationsDeviceID == rhs.communicationsDeviceID)) {
            ivarsEqual = false;
        }
        if (!(encodingScheme == rhs.encodingScheme)) {
            ivarsEqual = false;
        }
        if (!(tdlType == rhs.tdlType)) {
            ivarsEqual = false;
        }
        if (!(sampleRate == rhs.sampleRate)) {
            ivarsEqual = false;
        }
        if (!(dataLength == rhs.dataLength)) {
            ivarsEqual = false;
        }
        if (!(samples == rhs.samples)) {
            ivarsEqual = false;
        }
        if (!(Arrays.equals(data, rhs.data))) {
            ivarsEqual = false;
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
