package org.tuberlin.de.common.model.interfaces.datasink.print;

import org.tuberlin.de.common.model.interfaces.datasink.DataSink;

/**
 * Created by oxid on 1/9/16.
 *
 * print() / printToErr() / print(String msg) / printToErr(String msg) -
 * Prints the toString() value of each element on the standard out / strandard error stream. Optionally, a prefix (msg) can be provided which is prepended to the output.
 * This can help to distinguish between different calls to print.
 * If the parallelism is greater than 1, the output will also be prepended with the identifier of the task which produced the output.
 *
 *
 * TODO printToErr() print(Strg msg) printToErr(String msg)
 * TODO tData.writeAsCsv(...).sortLocalOutput("*", Order.ASCENDING);  .... SORT OUTPU?
 *
 */
public interface DataSinkPrint extends DataSink{

}
