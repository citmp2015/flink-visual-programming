package org.tuberlin.de.common.model.interfaces.datasink.file.write;

import org.tuberlin.de.common.model.interfaces.datasink.file.DataSinkFile;

/**
 * Created by oxid on 1/9/16.
 *
 * writeAsText() / TextOuputFormat - Writes elements line-wise as Strings. The Strings are obtained by calling the toString() method of each element.
 *
 */
public interface DataSinkFileWriteAsText extends DataSinkFile{

    //path from DataSinkFile
    //TODO textData.writeAsText("file:///my/result/on/localFS", WriteMode.OVERWRITE); additional parameters ?


}
