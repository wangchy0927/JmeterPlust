package com.thunisoft;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class Assistant extends AbstractJavaSamplerClient {

	private SampleResult results;
	private static final Logger log = LoggingManager.getLoggerForClass();
	
	private final static String YYSCORE = "英语分数";
	private final static String SXSCORE = "数学分数";
	private final static String YWSCORE = "语文分数";
	private final static String TOTALSCORE = "总分数";
	private final static String COUNT = "运行次数";
	
	
	private int ywScore ; 
	private int sxScore ;
	private int yyScore ;
	private int totalScore ;
	private int sum ;

	public Arguments getDefaultParameters() {
		Arguments args = new Arguments();
		
		args.addArgument(YYSCORE, "10");
		args.addArgument(SXSCORE, "20");
		args.addArgument(YWSCORE, "30");
		args.addArgument(TOTALSCORE, "100");
		args.addArgument(COUNT, "4");
		
		return args;
	}

	public void setupTest(JavaSamplerContext arg0) {
		log.info("开始只执行一次");
		ywScore = arg0.getIntParameter(YYSCORE);
		sxScore = arg0.getIntParameter(SXSCORE);
		yyScore = arg0.getIntParameter(YWSCORE);
		totalScore = arg0.getIntParameter(TOTALSCORE);
		sum = ywScore + sxScore + yyScore;
	}
	
	public SampleResult runTest(JavaSamplerContext arg0) {
		String encoding = "UTF-8";
		log.info("编码为:"+encoding);
		results = new SampleResult();
		results.sampleStart();
		int count = arg0.getIntParameter(COUNT);
		results.setSuccessful(true);
		for(int i=1;i<=count;i++){
			log.info("我正在执行第一次请求");
			SampleResult subResult = new SampleResult();
			boolean flag = sum+i == totalScore+i;
			if(flag == false)
				results.setSuccessful(false);
			subResult.setDataEncoding(encoding);
			subResult.setSuccessful(flag);
			subResult.setSampleLabel("用例标题:"+i);
			subResult.setRequestHeaders("实际总分数为:"+(sum+i));
		
			subResult.setResponseData("预计总分数为:"+(totalScore+i),encoding);
			results.addSubResult(subResult);
		}
		results.sampleEnd();
		return results;
	}
	
	public void teardownTest(JavaSamplerContext arg0) {
		log.info("只执行一次");
	}
}
