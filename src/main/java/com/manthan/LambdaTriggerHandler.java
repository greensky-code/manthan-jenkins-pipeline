package com.manthan;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.event.S3EventNotification;

public class LambdaTriggerHandler implements RequestHandler<S3Event, String> {

	@Override
	public String handleRequest(S3Event input, Context context) {     
		S3EventNotification.S3EventNotificationRecord record = input.getRecords().get(0);
		System.out.println("Bucket Name is "+record.getS3().getBucket().getName());
		System.out.println("File Path is "+record.getS3().getObject().getKey());
		System.out.println("Bucket Path is "+record.getS3().getBucket().getArn());
		System.out.println("Code updated!!!!!");
				System.out.println("Code updated!!!!!");

		return record.getS3().getBucket().getName();
	}

}
