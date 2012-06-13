/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package gr.ntua.ivml.mint.pi.messages;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaValidation implements org.apache.thrift.TBase<SchemaValidation, SchemaValidation._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SchemaValidation");

  private static final org.apache.thrift.protocol.TField SCHEMA_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("schema_id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField VALID_FIELD_DESC = new org.apache.thrift.protocol.TField("valid", org.apache.thrift.protocol.TType.BOOL, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SchemaValidationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SchemaValidationTupleSchemeFactory());
  }

  public int schema_id; // required
  public boolean valid; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SCHEMA_ID((short)1, "schema_id"),
    VALID((short)2, "valid");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // SCHEMA_ID
          return SCHEMA_ID;
        case 2: // VALID
          return VALID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __SCHEMA_ID_ISSET_ID = 0;
  private static final int __VALID_ISSET_ID = 1;
  private BitSet __isset_bit_vector = new BitSet(2);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SCHEMA_ID, new org.apache.thrift.meta_data.FieldMetaData("schema_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.VALID, new org.apache.thrift.meta_data.FieldMetaData("valid", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SchemaValidation.class, metaDataMap);
  }

  public SchemaValidation() {
  }

  public SchemaValidation(
    int schema_id,
    boolean valid)
  {
    this();
    this.schema_id = schema_id;
    setSchema_idIsSet(true);
    this.valid = valid;
    setValidIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SchemaValidation(SchemaValidation other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    this.schema_id = other.schema_id;
    this.valid = other.valid;
  }

  public SchemaValidation deepCopy() {
    return new SchemaValidation(this);
  }

  @Override
  public void clear() {
    setSchema_idIsSet(false);
    this.schema_id = 0;
    setValidIsSet(false);
    this.valid = false;
  }

  public int getSchema_id() {
    return this.schema_id;
  }

  public SchemaValidation setSchema_id(int schema_id) {
    this.schema_id = schema_id;
    setSchema_idIsSet(true);
    return this;
  }

  public void unsetSchema_id() {
    __isset_bit_vector.clear(__SCHEMA_ID_ISSET_ID);
  }

  /** Returns true if field schema_id is set (has been assigned a value) and false otherwise */
  public boolean isSetSchema_id() {
    return __isset_bit_vector.get(__SCHEMA_ID_ISSET_ID);
  }

  public void setSchema_idIsSet(boolean value) {
    __isset_bit_vector.set(__SCHEMA_ID_ISSET_ID, value);
  }

  public boolean isValid() {
    return this.valid;
  }

  public SchemaValidation setValid(boolean valid) {
    this.valid = valid;
    setValidIsSet(true);
    return this;
  }

  public void unsetValid() {
    __isset_bit_vector.clear(__VALID_ISSET_ID);
  }

  /** Returns true if field valid is set (has been assigned a value) and false otherwise */
  public boolean isSetValid() {
    return __isset_bit_vector.get(__VALID_ISSET_ID);
  }

  public void setValidIsSet(boolean value) {
    __isset_bit_vector.set(__VALID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SCHEMA_ID:
      if (value == null) {
        unsetSchema_id();
      } else {
        setSchema_id((Integer)value);
      }
      break;

    case VALID:
      if (value == null) {
        unsetValid();
      } else {
        setValid((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SCHEMA_ID:
      return Integer.valueOf(getSchema_id());

    case VALID:
      return Boolean.valueOf(isValid());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SCHEMA_ID:
      return isSetSchema_id();
    case VALID:
      return isSetValid();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SchemaValidation)
      return this.equals((SchemaValidation)that);
    return false;
  }

  public boolean equals(SchemaValidation that) {
    if (that == null)
      return false;

    boolean this_present_schema_id = true;
    boolean that_present_schema_id = true;
    if (this_present_schema_id || that_present_schema_id) {
      if (!(this_present_schema_id && that_present_schema_id))
        return false;
      if (this.schema_id != that.schema_id)
        return false;
    }

    boolean this_present_valid = true;
    boolean that_present_valid = true;
    if (this_present_valid || that_present_valid) {
      if (!(this_present_valid && that_present_valid))
        return false;
      if (this.valid != that.valid)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(SchemaValidation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    SchemaValidation typedOther = (SchemaValidation)other;

    lastComparison = Boolean.valueOf(isSetSchema_id()).compareTo(typedOther.isSetSchema_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSchema_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.schema_id, typedOther.schema_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValid()).compareTo(typedOther.isSetValid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.valid, typedOther.valid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SchemaValidation(");
    boolean first = true;

    sb.append("schema_id:");
    sb.append(this.schema_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("valid:");
    sb.append(this.valid);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SchemaValidationStandardSchemeFactory implements SchemeFactory {
    public SchemaValidationStandardScheme getScheme() {
      return new SchemaValidationStandardScheme();
    }
  }

  private static class SchemaValidationStandardScheme extends StandardScheme<SchemaValidation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SchemaValidation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SCHEMA_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.schema_id = iprot.readI32();
              struct.setSchema_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VALID
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.valid = iprot.readBool();
              struct.setValidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, SchemaValidation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SCHEMA_ID_FIELD_DESC);
      oprot.writeI32(struct.schema_id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(VALID_FIELD_DESC);
      oprot.writeBool(struct.valid);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SchemaValidationTupleSchemeFactory implements SchemeFactory {
    public SchemaValidationTupleScheme getScheme() {
      return new SchemaValidationTupleScheme();
    }
  }

  private static class SchemaValidationTupleScheme extends TupleScheme<SchemaValidation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SchemaValidation struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSchema_id()) {
        optionals.set(0);
      }
      if (struct.isSetValid()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetSchema_id()) {
        oprot.writeI32(struct.schema_id);
      }
      if (struct.isSetValid()) {
        oprot.writeBool(struct.valid);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SchemaValidation struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.schema_id = iprot.readI32();
        struct.setSchema_idIsSet(true);
      }
      if (incoming.get(1)) {
        struct.valid = iprot.readBool();
        struct.setValidIsSet(true);
      }
    }
  }

}
