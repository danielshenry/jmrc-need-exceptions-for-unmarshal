package edu.nps.moves.dis;

import java.util.*;
import java.io.*;

/**
 * Section 5.3.6.11. Reports occurance of a significant event to the simulation
 * manager. COMPLETE
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EventReportPdu extends SimulationManagementFamilyPdu implements Serializable {

    /**
     * Type of event
     */
    protected long eventType;

    /**
     * padding
     */
    protected long padding1 = (long) 0;

    /**
     * Number of fixed datum records
     */
    protected long numberOfFixedDatumRecords;

    /**
     * Number of variable datum records
     */
    protected long numberOfVariableDatumRecords;

    /**
     * variable length list of fixed datums
     */
    protected List< FixedDatum> fixedDatums = new ArrayList< FixedDatum>();
    /**
     * variable length list of variable length datums
     */
    protected List< VariableDatum> variableDatums = new ArrayList< VariableDatum>();

    /**
     * Constructor
     */
    public EventReportPdu() {
        setPduType((short) 21);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + 4;  // eventType
        marshalSize = marshalSize + 4;  // padding1
        marshalSize = marshalSize + 4;  // numberOfFixedDatumRecords
        marshalSize = marshalSize + 4;  // numberOfVariableDatumRecords
        for (int idx = 0; idx < fixedDatums.size(); idx++) {
            FixedDatum listElement = fixedDatums.get(idx);
            marshalSize = marshalSize + listElement.getMarshalledSize();
        }
        for (int idx = 0; idx < variableDatums.size(); idx++) {
            VariableDatum listElement = variableDatums.get(idx);
            marshalSize = marshalSize + listElement.getMarshalledSize();
        }

        return marshalSize;
    }

    public void setEventType(long pEventType) {
        eventType = pEventType;
    }

    public long getEventType() {
        return eventType;
    }

    public void setPadding1(long pPadding1) {
        padding1 = pPadding1;
    }

    public long getPadding1() {
        return padding1;
    }

    public long getNumberOfFixedDatumRecords() {
        return (long) fixedDatums.size();
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
        return (long) variableDatums.size();
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

    public void setFixedDatums(List<FixedDatum> pFixedDatums) {
        fixedDatums = pFixedDatums;
    }

    public List<FixedDatum> getFixedDatums() {
        return fixedDatums;
    }

    public void setVariableDatums(List<VariableDatum> pVariableDatums) {
        variableDatums = pVariableDatums;
    }

    public List<VariableDatum> getVariableDatums() {
        return variableDatums;
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
        buff.putInt((int) eventType);
        buff.putInt((int) padding1);
        buff.putInt((int) fixedDatums.size());
        buff.putInt((int) variableDatums.size());

        for (int idx = 0; idx < fixedDatums.size(); idx++) {
            FixedDatum aFixedDatum = (FixedDatum) fixedDatums.get(idx);
            aFixedDatum.marshal(buff);
        } // end of list marshalling

        for (int idx = 0; idx < variableDatums.size(); idx++) {
            VariableDatum aVariableDatum = (VariableDatum) variableDatums.get(idx);
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

        eventType = buff.getInt();
        padding1 = buff.getInt();
        numberOfFixedDatumRecords = buff.getInt();
        numberOfVariableDatumRecords = buff.getInt();
        for (int idx = 0; idx < numberOfFixedDatumRecords; idx++) {
            FixedDatum anX = new FixedDatum();
            anX.unmarshal(buff);
            fixedDatums.add(anX);
        }

        for (int idx = 0; idx < numberOfVariableDatumRecords; idx++) {
            VariableDatum anX = new VariableDatum();
            anX.unmarshal(buff);
            variableDatums.add(anX);
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

        if (!(obj instanceof EventReportPdu)) {
            return false;
        }

        final EventReportPdu rhs = (EventReportPdu) obj;

        if (!(eventType == rhs.eventType)) {
            ivarsEqual = false;
        }
        if (!(padding1 == rhs.padding1)) {
            ivarsEqual = false;
        }
        if (!(numberOfFixedDatumRecords == rhs.numberOfFixedDatumRecords)) {
            ivarsEqual = false;
        }
        if (!(numberOfVariableDatumRecords == rhs.numberOfVariableDatumRecords)) {
            ivarsEqual = false;
        }

        for (int idx = 0; idx < fixedDatums.size(); idx++) {
            if (!(fixedDatums.get(idx).equals(rhs.fixedDatums.get(idx)))) {
                ivarsEqual = false;
            }
        }

        for (int idx = 0; idx < variableDatums.size(); idx++) {
            if (!(variableDatums.get(idx).equals(rhs.variableDatums.get(idx)))) {
                ivarsEqual = false;
            }
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
