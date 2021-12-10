package io.deephaven.engine.table.impl.tuplesource.generated;

import io.deephaven.chunk.Chunk;
import io.deephaven.chunk.DoubleChunk;
import io.deephaven.chunk.FloatChunk;
import io.deephaven.chunk.WritableChunk;
import io.deephaven.chunk.WritableObjectChunk;
import io.deephaven.chunk.attributes.Values;
import io.deephaven.datastructures.util.SmartKey;
import io.deephaven.engine.table.ColumnSource;
import io.deephaven.engine.table.TupleSource;
import io.deephaven.engine.table.WritableColumnSource;
import io.deephaven.engine.table.impl.tuplesource.AbstractTupleSource;
import io.deephaven.engine.table.impl.tuplesource.ThreeColumnTupleSourceFactory;
import io.deephaven.tuple.generated.DoubleFloatDoubleTuple;
import io.deephaven.util.type.TypeUtils;
import org.jetbrains.annotations.NotNull;


/**
 * <p>{@link TupleSource} that produces key column values from {@link ColumnSource} types Double, Float, and Double.
 * <p>Generated by io.deephaven.replicators.TupleSourceCodeGenerator.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DoubleFloatDoubleColumnTupleSource extends AbstractTupleSource<DoubleFloatDoubleTuple> {

    /** {@link ThreeColumnTupleSourceFactory} instance to create instances of {@link DoubleFloatDoubleColumnTupleSource}. **/
    public static final ThreeColumnTupleSourceFactory<DoubleFloatDoubleTuple, Double, Float, Double> FACTORY = new Factory();

    private final ColumnSource<Double> columnSource1;
    private final ColumnSource<Float> columnSource2;
    private final ColumnSource<Double> columnSource3;

    public DoubleFloatDoubleColumnTupleSource(
            @NotNull final ColumnSource<Double> columnSource1,
            @NotNull final ColumnSource<Float> columnSource2,
            @NotNull final ColumnSource<Double> columnSource3
    ) {
        super(columnSource1, columnSource2, columnSource3);
        this.columnSource1 = columnSource1;
        this.columnSource2 = columnSource2;
        this.columnSource3 = columnSource3;
    }

    @Override
    public final DoubleFloatDoubleTuple createTuple(final long rowKey) {
        return new DoubleFloatDoubleTuple(
                columnSource1.getDouble(rowKey),
                columnSource2.getFloat(rowKey),
                columnSource3.getDouble(rowKey)
        );
    }

    @Override
    public final DoubleFloatDoubleTuple createPreviousTuple(final long rowKey) {
        return new DoubleFloatDoubleTuple(
                columnSource1.getPrevDouble(rowKey),
                columnSource2.getPrevFloat(rowKey),
                columnSource3.getPrevDouble(rowKey)
        );
    }

    @Override
    public final DoubleFloatDoubleTuple createTupleFromValues(@NotNull final Object... values) {
        return new DoubleFloatDoubleTuple(
                TypeUtils.unbox((Double)values[0]),
                TypeUtils.unbox((Float)values[1]),
                TypeUtils.unbox((Double)values[2])
        );
    }

    @Override
    public final DoubleFloatDoubleTuple createTupleFromReinterpretedValues(@NotNull final Object... values) {
        return new DoubleFloatDoubleTuple(
                TypeUtils.unbox((Double)values[0]),
                TypeUtils.unbox((Float)values[1]),
                TypeUtils.unbox((Double)values[2])
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <ELEMENT_TYPE> void exportElement(@NotNull final DoubleFloatDoubleTuple tuple, final int elementIndex, @NotNull final WritableColumnSource<ELEMENT_TYPE> writableSource, final long destinationRowKey) {
        if (elementIndex == 0) {
            writableSource.set(destinationRowKey, tuple.getFirstElement());
            return;
        }
        if (elementIndex == 1) {
            writableSource.set(destinationRowKey, tuple.getSecondElement());
            return;
        }
        if (elementIndex == 2) {
            writableSource.set(destinationRowKey, tuple.getThirdElement());
            return;
        }
        throw new IndexOutOfBoundsException("Invalid element index " + elementIndex + " for export");
    }

    @Override
    public final Object exportToExternalKey(@NotNull final DoubleFloatDoubleTuple tuple) {
        return new SmartKey(
                TypeUtils.box(tuple.getFirstElement()),
                TypeUtils.box(tuple.getSecondElement()),
                TypeUtils.box(tuple.getThirdElement())
        );
    }

    @Override
    public final Object exportElement(@NotNull final DoubleFloatDoubleTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return TypeUtils.box(tuple.getSecondElement());
        }
        if (elementIndex == 2) {
            return TypeUtils.box(tuple.getThirdElement());
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    public final Object exportElementReinterpreted(@NotNull final DoubleFloatDoubleTuple tuple, int elementIndex) {
        if (elementIndex == 0) {
            return TypeUtils.box(tuple.getFirstElement());
        }
        if (elementIndex == 1) {
            return TypeUtils.box(tuple.getSecondElement());
        }
        if (elementIndex == 2) {
            return TypeUtils.box(tuple.getThirdElement());
        }
        throw new IllegalArgumentException("Bad elementIndex for 3 element tuple: " + elementIndex);
    }

    @Override
    protected void convertChunks(@NotNull WritableChunk<? super Values> destination, int chunkSize, Chunk<Values> [] chunks) {
        WritableObjectChunk<DoubleFloatDoubleTuple, ? super Values> destinationObjectChunk = destination.asWritableObjectChunk();
        DoubleChunk<Values> chunk1 = chunks[0].asDoubleChunk();
        FloatChunk<Values> chunk2 = chunks[1].asFloatChunk();
        DoubleChunk<Values> chunk3 = chunks[2].asDoubleChunk();
        for (int ii = 0; ii < chunkSize; ++ii) {
            destinationObjectChunk.set(ii, new DoubleFloatDoubleTuple(chunk1.get(ii), chunk2.get(ii), chunk3.get(ii)));
        }
        destinationObjectChunk.setSize(chunkSize);
    }

    /** {@link ThreeColumnTupleSourceFactory} for instances of {@link DoubleFloatDoubleColumnTupleSource}. **/
    private static final class Factory implements ThreeColumnTupleSourceFactory<DoubleFloatDoubleTuple, Double, Float, Double> {

        private Factory() {
        }

        @Override
        public TupleSource<DoubleFloatDoubleTuple> create(
                @NotNull final ColumnSource<Double> columnSource1,
                @NotNull final ColumnSource<Float> columnSource2,
                @NotNull final ColumnSource<Double> columnSource3
        ) {
            return new DoubleFloatDoubleColumnTupleSource(
                    columnSource1,
                    columnSource2,
                    columnSource3
            );
        }
    }
}