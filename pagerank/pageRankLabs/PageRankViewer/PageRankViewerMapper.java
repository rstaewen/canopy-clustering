/* Author = jhebert@cs.washington.edu
 * License = GPL
 */
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.io.*;

public class PageRankViewerMapper extends MapReduceBase implements Mapper {

  public void map(WritableComparable key, Writable value,
                  OutputCollector output, Reporter reporter) throws IOException {

    String data = ((Text)value).toString();
    int index = data.indexOf(":");
    if (index == -1) {
      return;
    }
    String toParse = data.substring(0, index).trim();
    double currScore = Double.parseDouble(toParse);
    output.collect(new FloatWritable((float) - currScore), key);
  }
}
