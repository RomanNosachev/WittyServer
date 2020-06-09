package com.wittyhome.core.generator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.wittyhome.module_base.generator.Request;

@Component
public class TimeRequest 
implements Request
{
	private static final long serialVersionUID = -1991666683816399451L;

	private static DateFormat format = new SimpleDateFormat("HH:mm");
	
	@Id
	private String id;
	
	@CreatedDate
	private Date createdDate;
	
	private String timeString;
	
	public TimeRequest() {}
	
	public TimeRequest(Date date) 
	{
		setTimeString(format.format(date));
	}

	public TimeRequest(String time) 
	{
		this.setTimeString(time);
	}
	
	@Override
	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public String getId() 
	{
		return id;
	}

	@Override
	public Date getCreatedDate() 
	{
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date date) 
	{
		this.createdDate = date;
	}

	@Override
	public Request clone() 
	{
		try {
			return (Request) super.clone();
		}
		catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	@Override
	public String toString() 
	{
		return String.format("time: %s", timeString);
	}

	public String getTimeString() 
	{
		return timeString;
	}

	public Date getTime() throws ParseException
	{
		return format.parse(timeString);
	}
	
	public void setTimeString(String timeString) 
	{
		this.timeString = timeString;
	}
	
	public void setTime(Date time)
	{
		this.timeString = format.format(time);
	}
}
