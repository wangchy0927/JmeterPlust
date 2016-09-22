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
		args.addArgument("��ѧ����", "10");
		args.addArgument("���ķ���", "20");
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
			int mathSocre = arg0.getIntParameter("��ѧ����");
			int chinaSocre = arg0.getIntParameter("���ķ���");
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
