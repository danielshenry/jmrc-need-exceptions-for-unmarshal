package edu.nps.moves.dis;

import java.io.*;

/**
 * Section 5.3.3.2. Information about a collision. COMPLETE
 *
 * Copyright (c) 2008-2016, MOVES Institute, Naval Postgraduate School. All
 * rights reserved. This work is licensed under the BSD open source license,
 * available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class CollisionPdu extends EntityInformationFamilyPdu implements Serializable {

    /**
     * ID of the entity that issued the collision PDU
     */
    protected EntityID issuingEntityID = new EntityID();

    /**
     * ID of entity that has collided with the issuing entity ID
     */
    protected EntityID collidingEntityID = new EntityID();

    /**
     * ID of event
     */
    protected EventID eventID = new EventID();

    /**
     * ID of event
     */
    protected short collisionType;

    /**
     * some padding
     */
    protected byte pad = (byte) 0;

    /**
     * velocity at collision
     */
    protected Vector3Float velocity = new Vector3Float();

    /**
     * mass of issuing entity
     */
    protected float mass;

    /**
     * Location with respect to entity the issuing entity collided with
     */
    protected Vector3Float location = new Vector3Float();

    /**
     * Constructor
     */
    public CollisionPdu() {
        setPduType((short) 4);
        setProtocolFamily((short) 1);
    }

    public int getMarshalledSize() {
        int marshalSize = 0;

        marshalSize = super.getMarshalledSize();
        marshalSize = marshalSize + issuingEntityID.getMarshalledSize();  // issuingEntityID
        marshalSize = marshalSize + collidingEntityID.getMarshalledSize();  // collidingEntityID
        marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
        marshalSize = marshalSize + 1;  // collisionType
        marshalSize = marshalSize + 1;  // pad
        marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
        marshalSize = marshalSize + 4;  // mass
        marshalSize = marshalSize + location.getMarshalledSize();  // location

        return marshalSize;
    }

    public void setIssuingEntityID(EntityID pIssuingEntityID) {
        issuingEntityID = pIssuingEntityID;
    }

    public EntityID getIssuingEntityID() {
        return issuingEntityID;
    }

    public void setCollidingEntityID(EntityID pCollidingEntityID) {
        collidingEntityID = pCollidingEntityID;
    }

    public EntityID getCollidingEntityID() {
        return collidingEntityID;
    }

    public void setEventID(EventID pEventID) {
        eventID = pEventID;
    }

    public EventID getEventID() {
        return eventID;
    }

    public void setCollisionType(short pCollisionType) {
        collisionType = pCollisionType;
    }

    public short getCollisionType() {
        return collisionType;
    }

    public void setPad(byte pPad) {
        pad = pPad;
    }

    public byte getPad() {
        return pad;
    }

    public void setVelocity(Vector3Float pVelocity) {
        velocity = pVelocity;
    }

    public Vector3Float getVelocity() {
        return velocity;
    }

    public void setMass(float pMass) {
        mass = pMass;
    }

    public float getMass() {
        return mass;
    }

    public void setLocation(Vector3Float pLocation) {
        location = pLocation;
    }

    public Vector3Float getLocation() {
        return location;
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
        issuingEntityID.marshal(buff);
        collidingEntityID.marshal(buff);
        eventID.marshal(buff);
        buff.put((byte) collisionType);
        buff.put((byte) pad);
        velocity.marshal(buff);
        buff.putFloat((float) mass);
        location.marshal(buff);
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

        issuingEntityID.unmarshal(buff);
        collidingEntityID.unmarshal(buff);
        eventID.unmarshal(buff);
        collisionType = (short) (buff.get() & 0xFF);
        pad = buff.get();
        velocity.unmarshal(buff);
        mass = buff.getFloat();
        location.unmarshal(buff);
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

        if (!(obj instanceof CollisionPdu)) {
            return false;
        }

        final CollisionPdu rhs = (CollisionPdu) obj;

        if (!(issuingEntityID.equals(rhs.issuingEntityID))) {
            ivarsEqual = false;
        }
        if (!(collidingEntityID.equals(rhs.collidingEntityID))) {
            ivarsEqual = false;
        }
        if (!(eventID.equals(rhs.eventID))) {
            ivarsEqual = false;
        }
        if (!(collisionType == rhs.collisionType)) {
            ivarsEqual = false;
        }
        if (!(pad == rhs.pad)) {
            ivarsEqual = false;
        }
        if (!(velocity.equals(rhs.velocity))) {
            ivarsEqual = false;
        }
        if (!(mass == rhs.mass)) {
            ivarsEqual = false;
        }
        if (!(location.equals(rhs.location))) {
            ivarsEqual = false;
        }

        return ivarsEqual && super.equalsImpl(rhs);
    }
} // end of class
