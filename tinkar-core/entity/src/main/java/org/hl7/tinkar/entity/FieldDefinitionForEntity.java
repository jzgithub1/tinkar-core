package org.hl7.tinkar.entity;


import io.activej.bytebuf.ByteBuf;
import org.hl7.tinkar.common.service.PrimitiveData;
import org.hl7.tinkar.component.FieldDefinition;
import org.hl7.tinkar.entity.internal.Get;
import org.hl7.tinkar.terms.ConceptFacade;

public class FieldDefinitionForEntity
    implements FieldDefinition {

    protected PatternEntityVersion enclosingVersion;
    protected int dataTypeNid;
    protected int purposeNid;
    protected int meaningNid;

    @Override
    public ConceptFacade dataType() {
        return Get.entityService().getEntityFast(dataTypeNid);
    }

    @Override
    public ConceptFacade purpose() {
        return Get.entityService().getEntityFast(purposeNid);
    }

    @Override
    public ConceptFacade meaning() {
        return Get.entityService().getEntityFast(meaningNid);
    }

    /**
     * TODO interface for write, fill, etc.
     * @param readBuf
     */
    public void fill(PatternEntityVersion enclosingVersion, ByteBuf readBuf) {
        this.enclosingVersion = enclosingVersion;
        dataTypeNid = readBuf.readInt();
        purposeNid = readBuf.readInt();
        meaningNid = readBuf.readInt();
    }

    public void fill(PatternEntityVersion enclosingVersion, FieldDefinition fieldDefinition) {
        this.enclosingVersion = enclosingVersion;
        dataTypeNid = Get.entityService().nidForComponent(fieldDefinition.dataType());
        purposeNid = Get.entityService().nidForComponent(fieldDefinition.purpose());
        meaningNid = Get.entityService().nidForComponent(fieldDefinition.meaning());
    }

    public void write(ByteBuf writeBuf) {
        writeBuf.writeInt(dataTypeNid);
        writeBuf.writeInt(purposeNid);
        writeBuf.writeInt(meaningNid);
    }

    public static FieldDefinitionForEntity make(PatternEntityVersion enclosingVersion, ByteBuf readBuf) {
        FieldDefinitionForEntity fieldDefinitionForEntity = new FieldDefinitionForEntity();
        fieldDefinitionForEntity.fill(enclosingVersion, readBuf);
        return fieldDefinitionForEntity;
    }

    public static FieldDefinitionForEntity make(PatternEntityVersion enclosingVersion, FieldDefinition fieldDefinition) {
        FieldDefinitionForEntity fieldDefinitionForEntity = new FieldDefinitionForEntity();
        fieldDefinitionForEntity.fill(enclosingVersion, fieldDefinition);
        return fieldDefinitionForEntity;
    }

    @Override
    public String toString() {
        return "FieldDef{t: " +
                PrimitiveData.text(dataTypeNid) + " p: " +
                PrimitiveData.text(purposeNid) + " m: " +
                PrimitiveData.text(meaningNid) +
                '}';
    }
}