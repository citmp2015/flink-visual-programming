package org.tuberlin.de.common.model.interfaces.datasink.file.write;

import org.tuberlin.de.common.model.interfaces.datasink.file.DataSinkFile;

/**
 * Created by oxid on 1/9/16.
 *
 * writeAsFormattedText() / TextOutputFormat - Write elements line-wise as Strings. The Strings are obtained by calling a user-defined format() method for each element.
 *
 */
public interface DataSinkFileWriteAsFormattedText extends DataSinkFile{

    //path from DataSinkFile
    //TODO values.writeAsFormattedText("file:///path/to/the/result/file", new TextFormatter<Tuple2<Integer, Integer>>() { public String format (Tuple2<Integer, Integer> value) {return value.f1 + " - " + value.f0;}});
    //TODO embedded class
}
