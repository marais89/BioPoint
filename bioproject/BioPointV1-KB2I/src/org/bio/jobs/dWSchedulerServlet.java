package org.bio.jobs;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.bio.jobs.LoadDataToDW;
@WebServlet(loadOnStartup=3)
public class dWSchedulerServlet extends HttpServlet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Timer timer = new Timer();
private LoadDataToDW dw = new LoadDataToDW(); 
	private  TimerTask task = new TimerTask() {

		public void run() {
			// job code here
			Date dateTime = Calendar.getInstance().getTime();
			System.out.println("Run Job");
			String[] args = {""};
			dw.runJobInTOS(args);
			//loadMvt();
		}

	};

	@SuppressWarnings("deprecation")
	public void init() throws ServletException {
		Date dateTime = Calendar.getInstance().getTime();
		// period to 23:59
	 	int period = ((24 - dateTime.getHours()) * 3600 + (60 - dateTime.getMinutes() * 60)) * 3600;
		dateTime.setHours(dateTime.getHours() + 1);
		timer.scheduleAtFixedRate(
				task,
				dateTime,
				period);

	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException{

	}

	public void destroy() {
		timer.cancel();
	}

	
	public void runJobDw() {
		System.out.println("enter");
		

	}
	
	
}