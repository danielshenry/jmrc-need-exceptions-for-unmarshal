package edu.nps.moves.dis;

import java.util.*;
import java.io.*;

/**
 * Section 5.3.12.10: issued in response to a data query R or set dataR pdu.
 * Needs manual intervention to fix padding on variable datums. UNFINSIHED
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class DataReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable {

    /**
     * Request ID
     */
    protected long requestID;

    /**
     * level of reliability service used for this transaction
     */
    protected short requiredReliabilityService;

    /**
     * padding
     */
    protected int pad1;

    /**
     * padding
     */
    protected short pad2;

    /**
     * Fixed datum record count
     */
    protected long numberOfFixedDatumRecords;

    /**
     * variable datum record count
     */
    protected long numberOfVariableDatumRecords;

    /**
     * Fixed datum records
     */
    protected List< FixedDatum> fixedDatumRecords = new ArrayList< FixedDatum>();
    /**
     * Variable datum records
     */
    protected List< VariableDatum> variableDatumRecords = new ArrayList< VariableDatum>();

    /**
     * Constructor
     */
    public DataReliablePdu() {
        setPduType((short) 60);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + 4;  // requestID
        marshalSize = marshalSize + 1;  // requiredReliabilityService
        marshalSize = marshalSize + 2;  // pad1
        marshalSize = marshalSize + 1;  // pad2
        marshalSize = marshalSize + 4;  // numberOfFixedDatumRecords
        marshalSize = marshalSize + 4;  // numberOfVariableDatumRecords
        for (int idx = 0; idx < fixedDatumRecords.size(); idx++) {
            FixedDatum listElement = fixedDatumRecords.get(idx);
            marshalSize = marshalSize + listElement.getMarshalledSize();
        }
        for (int idx = 0; idx < variableDatumRecords.size(); idx++) {
            VariableDatum listElement = variableDatumRecords.get(idx);
            marshalSize = marshalSize + listElement.getMarshalledSize();
        }

        return marshalSize;
    }

    public void setRequestID(long pRequestID) {
        requestID = pRequestID;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequiredReliabilityService(short pRequiredReliabilityService) {
        requiredReliabilityService = pRequiredReliabilityService;
    }

    public short getRequiredReliabilityService() {
        return requiredReliabilityService;
    }

    public void setPad1(int pPad1) {
        pad1 = pPad1;
    }

    public int getPad1() {
        return pad1;
    }

    public void setPad2(short pPad2) {
        pad2 = pPad2;
    }

    public short getPad2() {
        return pad2;
    }

    public long getNumberOfFixedDatumRecords() {
        return (long) fixedDatumRecords.size();
    }

    /**
     * Note that setting this value will not change the marshalled value. The
     * list whose length this describes is used for that purpose. The
     * getnumberOfFixedDatumRecords method will also be based on the actual list
     * length rather than this value. The method is simply here for java bean
     * completeness.
     */
    public void setNumberOfFixedDatumRecords(long pNumberOfFixedDatumRecords) {
        numberOfFixedDatumRecords = pNumberOfFixedDatumRecords;
    }

    public long getNumberOfVariableDatumRecords() {
        return (long) variableDatumRecords.size();
    }

    /**
     * Note that setting this value will not change the marshalled value. The
     * list whose length this describes is used for that purpose. The
     * getnumberOfVariableDatumRecords method will also be based on the actual
     * list length rather than this value. The method is simply here for java
     * bean completeness.
     */
    public void setNumberOfVariableDatumRecords(long pNumberOfVariableDatumRecords) {
        numberOfVariableDatumRecords = pNumberOfVariableDatumRecords;
    }

    public void setFixedDatumRecords(List<FixedDatum> pFixedDatumRecords) {
        fixedDatumRecords = pFixedDatumRecords;
    }

    public List<FixedDatum> getFixedDatumRecords() {
        return fixedDatumRecords;
    }

    public void setVariableDatumRecords(List<VariableDatum> pVariableDatumRecords) {
        variableDatumRecords = pVariableDatumRecords;
    }

    public List<VariableDatum> getVariableDatumRecords() {
        return variableDatumRecords;
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
        buff.putInt((int) requestID);
        buff.put((byte) requiredReliabilityService);
        buff.putShort((short) pad1);
        buff.put((byte) pad2);
        buff.putInt((int) fixedDatumRecords.size());
        buff.putInt((int) variableDatumRecords.size());

        for (int idx = 0; idx < fixedDatumRecords.size(); idx++) {
            FixedDatum aFixedDatum = (FixedDatum) fixedDatumRecords.get(idx);
            aFixedDatum.marshal(buff);
        } // end of list marshalling

        for (int idx = 0; idx < variableDatumRecords.size(); idx++) {
            VariableDatum aVariableDatum = (VariableDatum) variableDatumRecords.get(idx);
            aVariableDatum.marshal(buff);
        } // end of list marshalling

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

        requestID = buff.getInt();
        requiredReliabilityService = (short) (buff.get() & 0xFF);
        pad1 = (int) (buff.getShort() & 0xFFFF);
        pad2 = (short) (buff.get() & 0xFF);
        numberOfFixedDatumRecords = buff.getInt();
        numberOfVariableDatumRecords = buff.getInt();
        for (int idx = 0; idx < numberOfFixedDatumRecords; idx++) {
            FixedDatum anX = new FixedDatum();
            anX.unmarshal(buff);
            fixedDatumRecords.add(anX);
        }

        for (int idx = 0; idx < numberOfVariableDatumRecords; idx++) {
            VariableDatum anX = new VariableDatum();
            anX.unmarshal(buff);
            variableDatumRecords.add(anX);
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

        if (!(obj instanceof DataReliablePdu)) {
            return false;
        }

        final DataReliablePdu rhs = (DataReliablePdu) obj;

        if (!(requestID == rhs.requestID)) {
            ivarsEqual = false;
        }
        if (!(requiredReliabilityService == rhs.requiredReliabilityService)) {
            ivarsEqual = false;
        }
        if (!(pad1 == rhs.pad1)) {
            ivarsEqual = false;
        }
        if (!(pad2 == rhs.pad2)) {
            ivarsEqual = false;
        }
        if (!(numberOfFixedDatumRecords == rhs.numberOfFixedDatumRecords)) {
            ivarsEqual = false;
        }
        if (!(numberOfVariableDatumRecords == rhs.numberOfVariableDatumRecords)) {
            ivarsEqual = false;
        }

        for (int idx = 0; idx < fixedDatumRecords.size(); idx++) {
            if (!(fixedDatumRecords.get(idx).equals(rhs.fixedDatumRecords.get(idx)))) {
                ivarsEqual = false;
            }
        }

        for (int idx = 0; idx < variableDatumRecords.size(); idx++) {
            if (!(variableDatumRecords.get(idx).equals(rhs.variableDatumRecords.get(idx)))) {
                ivarsEqual = false;
            }
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
