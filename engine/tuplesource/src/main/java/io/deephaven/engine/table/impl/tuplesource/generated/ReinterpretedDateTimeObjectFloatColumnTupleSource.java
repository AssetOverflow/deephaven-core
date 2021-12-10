package io.deephaven.engine.table.impl.tuplesource.generated;

import io.deephaven.chunk.Chunk;
import io.deephaven.chunk.FloatChunk;
import io.deephaven.chunk.LongChunk;
import io.deephaven.chunk.ObjectChunk;
import io.deephaven.chunk.WritableChunk;
import io.deephaven.chunk.WritableObjectChunk;
import io.deephaven.chunk.attributes.Values;
import io.deephaven.datastructures.util.SmartKey;
import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.TupleSource;
import io.deephaven.engine.table.WritableColumnSource;
import io.deephaven.engine.table.impl.tuplesource.AbstractTupleSource;
import io.deephaven.engine.table.impl.tuplesource.ThreeColumnTupleSourceFactory;
import io.deephaven.time.DateTime;
import io.deephaven.time.DateTimeUtils;
import io.deephaven.tuple.generated.LongObjectFloatTuple;
import io.deephaven.util.type.TypeUtils;
import org.jetbrains.annotations.NotNull;


/**
 * <p>{@link TupleSource} that produces key column values from {@link ColumnSource} types Long, Object, and Float.
 * <p>Generated by io.deephaven.replicators.TupleSourceCodeGenerator.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ReinterpretedDateTimeObjectFloatColumnTupleSource extends AbstractTupleSource<LongObjectFloatTuple> {

    /** {@link ThreeColumnTupleSourceFactory} instance to create instances of {@link ReinterpretedDateTimeObjectFloatColumnTupleSource}. **/
    public static final ThreeColumnTupleSourceFactory<LongObjectFloatTuple, Long, Object, Float> FACTORY = new Factory();

    private final ColumnSource<Long> columnSource1;
    private final ColumnSource<Object> columnSource2;
    private final ColumnSource<Float> columnSource3;

    public ReinterpretedDateTimeObjectFloatColumnTupleSource(
            @NotNull final ColumnSource<Long> columnSource1,
            @NotNull final ColumnSource<Object> columnSource2,
            @NotNull final ColumnSource<Float> columnSource3
    ) {
        super(columnSource1, columnSource2, columnSource3);
        this.columnSource1 = columnSource1;
        this.columnSource2 = columnSource2;
        this.columnSource3 = columnSource3;
    }

    @Override
    public final LongObjectFloatTuple createTuple(final long rowKey) {
        return new LongObjectFloatTuple(
                columnSource1.getLong(rowKey),
                columnSource2.get(rowKey),
                columnSource3.getFloat(rowKey)
        );
    }

    @Override
    public final LongObjectFloatTuple createPreviousTuple(final long rowKey) {
        return new LongObjectFloatTuple(
                columnSource1.getPrevLong(rowKey),
                columnSource2.getPrev(rowKey),
                columnSource3.getPrevFloat(rowKey)
        );
    }

    @Override
    public final LongObjectFloatTuple createTupleFromValues(@NotNull final Object... values) {
        return new LongObjectFloatTuple(
                DateTimeUtils.nanos((DateTime)values[0]),
                values[1],
                TypeUtils.unbox((Float)values[2])
        );
    }

    @Override
    public final LongObjectFloatTuple createTupleFromReinterpretedValues(@NotNull final Object... values) {
        return new LongObjectFloatTuple(
                TypeUtils.unbox((Long)values[0]),
                values[1],
                TypeUtils.unbox((Float)values[2])
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <ELEMENT_TYPE> void exportElement(@NotNull final LongObjectFloatTuple tuple, final int elementIndex, @NotNull final WritableColumnSource<ELEMENT_TYPE> writableSource, final long destinationRowKey) {
        if (elementIndex == 0) {
            writableSource.set(destinationRowKey, (ELEMENT_TYPE) DateTimeUtils.nanosToTime(tuple.getFirstElement()));
            return;
        }
        if (elementIndex == 1) {
            writableSource.set(destinationRowKey, (ELEMENT_TYPE) tuple.getSecondElement());
            return;
        }
        if (elementIndex == 2) {
            writableSource.set(destinationRowKey, tuple.getThirdElement());
            return;
        }
        throw new IndexOutOfBoundsException("Invalid element index " + elementIndex + " for export");
    }

    @Override
    public final Object exportToExternalKey(@NotNull final LongObjectFloatTuple tuple) {
        return new SmartKey(
                DateTimeUtils.nanosToTime(tuple.getFirstElement()),
                tuple.getSecondElement(),
                TypeUtils.box(tuple.getThirdElement())
        );
    }

    @Override
    public final Object exportElement(@NotNull final LongObjectFloatTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return DateTimeUtils.nanosToTime(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return tuple.getSecondElement();
        }
        if (elementIndex == 2) {
            return TypeUtils.box(tuple.getThirdElement());
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    public final Object exportElementReinterpreted(@NotNull final LongObjectFloatTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return tuple.getSecondElement();
        }
        if (elementIndex == 2) {
            return TypeUtils.box(tuple.getThirdElement());
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    protected void convertChunks(@NotNull WritableChunk<? super Values> destination, int chunkSize, Chunk<Values> [] chunks) {
        WritableObjectChunk<LongObjectFloatTuple, ? super Values> destinationObjectChunk = destination.asWritableObjectChunk();
        LongChunk<Values> chunk1 = chunks[0].asLongChunk();
        ObjectChunk<Object, Values> chunk2 = chunks[1].asObjectChunk();
        FloatChunk<Values> chunk3 = chunks[2].asFloatChunk();
        for (int ii = 0; ii < chunkSize; ++ii) {
            destinationObjectChunk.set(ii, new LongObjectFloatTuple(chunk1.get(ii), chunk2.get(ii), chunk3.get(ii)));
        }
        destinationObjectChunk.setSize(chunkSize);
    }

    /** {@link ThreeColumnTupleSourceFactory} for instances of {@link ReinterpretedDateTimeObjectFloatColumnTupleSource}. **/
    private static final class Factory implements ThreeColumnTupleSourceFactory<LongObjectFloatTuple, Long, Object, Float> {

        private Factory() {
        }

        @Override
        public TupleSource<LongObjectFloatTuple> create(
                @NotNull final ColumnSource<Long> columnSource1,
                @NotNull final ColumnSource<Object> columnSource2,
                @NotNull final ColumnSource<Float> columnSource3
        ) {
            return new ReinterpretedDateTimeObjectFloatColumnTupleSource(
                    columnSource1,
                    columnSource2,
                    columnSource3
            );
        }
    }
}