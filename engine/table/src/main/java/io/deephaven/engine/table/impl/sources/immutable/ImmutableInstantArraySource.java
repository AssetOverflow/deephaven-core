/**
 * Copyright (c) 2016-2023 Deephaven Data Labs and Patent Pending
 */
package io.deephaven.engine.table.impl.sources.immutable;

import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.impl.ImmutableColumnSourceGetDefaults;
import io.deephaven.time.DateTimeUtils;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

/**
 * ImmutableArraySource for {@link Instant}s. Allows reinterpretation as long.
 */
public class ImmutableInstantArraySource extends ImmutableNanosBasedTimeArraySource<Instant>
        implements ImmutableColumnSourceGetDefaults.ForLongAsInstant {

    public ImmutableInstantArraySource() {
        super(Instant.class);
    }

    public ImmutableInstantArraySource(final @NotNull long[] nanos) {
        super(Instant.class, new ImmutableLongArraySource(nanos));
    }

    public ImmutableInstantArraySource(final @NotNull ImmutableLongArraySource nanoSource) {
        super(Instant.class, nanoSource);
    }

    @Override
    protected Instant makeValue(long nanos) {
        return DateTimeUtils.makeInstant(nanos);
    }

    @Override
    protected long toNanos(Instant value) {
        return DateTimeUtils.toEpochNano(value);
    }

    @Override
    public ColumnSource<Instant> toInstant() {
        return this;
    }
}