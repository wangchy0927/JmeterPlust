package com.thunisoft;


import org.apache.jmeter.assertions.AssertionResult;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class Assistant extends AbstractJavaSamplerClient {

	private SampleResult results;

	public Arguments getDefaultParameters() {
		Arguments args = new Arguments();
		args.addArgument("数学分数", "10");
		args.addArgument("语文分数", "20");
		args.addArgument("total", "12314");
		return args;
	}

	public void setupTest(JavaSamplerContext arg0) {
	}
	
	public SampleResult runTest(JavaSamplerContext arg0) {
		results = new SampleResult();
		results.setSuccessful(true);
		for(int i=0;i<=3;i++){
			SampleResult subResult = new SampleResult();
			int mathSocre = arg0.getIntParameter("数学分数");
			int chinaSocre = arg0.getIntParameter("语文分数");
			int total = arg0.getIntParameter("total");
			int sum = mathSocre + chinaSocre + i;
			boolean flag = sum == total;
			if(flag == false)
				results.setSuccessful(false);
			subResult.setSuccessful(flag);
			subResult.setSampleLabel("title:"+i);
			subResult.setRequestHeaders("request");
			subResult.setResponseData("response");
			results.addSubResult(subResult);
		}
		return results;
	}
}
