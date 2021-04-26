package org.hl7.tinkar.entity;

import io.activej.bytebuf.ByteBuf;
import org.hl7.tinkar.component.Chronology;
import org.hl7.tinkar.component.ConceptChronology;
import org.hl7.tinkar.component.ConceptVersion;
import org.hl7.tinkar.component.Version;
import org.hl7.tinkar.component.FieldDataType;
import org.hl7.tinkar.terms.ConceptFacade;

public final class ConceptEntity
        extends Entity<ConceptEntityVersion>
        implements ConceptChronology<ConceptEntityVersion>, ConceptFacade {

    private ConceptEntity() {
        super();
    }
    @Override
    protected int subclassFieldBytesSize() {
        return 0; // no additional fields...
    }

    @Override
    protected ConceptEntityVersion makeVersion(ByteBuf readBuf, byte formatVersion) {
        return ConceptEntityVersion.make(this, readBuf, formatVersion);
    }

    @Override
    protected ConceptEntityVersion makeVersion(Version version) {
        return ConceptEntityVersion.make(this, (ConceptVersion) version);
    }

    @Override
    public FieldDataType dataType() {
        return FieldDataType.CONCEPT_CHRONOLOGY;
    }

    @Override
    protected void finishEntityWrite(ByteBuf readBuf) {
        // No additional fields to write.
    }

    @Override
    protected void finishEntityRead(ByteBuf readBuf, byte formatVersion) {
        // no extra fields to read.
    }

    @Override
    protected void finishEntityRead(Chronology chronology) {
        // no extra fields to read.
    }

    public static ConceptEntity make(ByteBuf readBuf, byte entityFormatVersion) {
        ConceptEntity conceptFacade = new ConceptEntity();
        conceptFacade.fill(readBuf, entityFormatVersion);
        return conceptFacade;
    }

    public static ConceptEntity make(ConceptChronology other) {
        ConceptEntity conceptFacade = new ConceptEntity();
        conceptFacade.fill(other);
        return conceptFacade;
    }
}