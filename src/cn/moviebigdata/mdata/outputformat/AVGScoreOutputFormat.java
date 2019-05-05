package cn.moviebigdata.mdata.outputformat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.util.Progressable;

import cn.hadoop.mdata.common.MysqlUtil;
import cn.moviebigdata.mdata.writer.ActorAvgScoreRecordWriter;

public class AVGScoreOutputFormat implements OutputFormat<FloatWritable, Text>{

	
	private static final Log logger = LogFactory.getLog(AVGScoreOutputFormat.class);
	
	@Override
	public void checkOutputSpecs(FileSystem arg0, JobConf arg1)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecordWriter<FloatWritable, Text> getRecordWriter(FileSystem arg0,
			JobConf job, String arg2, Progressable arg3) throws IOException {
		Connection conn = null;
		try {
			conn = MysqlUtil.getInstance(job).init(job);
			if(conn!=null){
				logger.info("数据初始化成功......");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ActorAvgScoreRecordWriter(conn);
	}

}
