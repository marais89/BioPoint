package org.bio.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.bio.model.Conge;
import org.bio.model.HistoriqueCat;
import org.bio.model.Mvt;
import org.bio.model.Personnel;
import org.bio.model.Pointage;
import org.bio.model.PointageId;
import org.bio.model.Sequence;
import org.bio.model.SequenceDetail;
import org.bio.service.FerieService;
import org.bio.service.HistoriqueCatService;
import org.bio.service.LostMvtService;
import org.bio.service.MvtService;
import org.bio.service.ParametreService;
import org.bio.service.PersonnelService;
import org.bio.service.PointageService;
import org.bio.service.SequenceService;
import org.bio.service.TerminauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class PointageServlet{
	private Timer timer = new Timer();

	private  TimerTask task = new TimerTask() {

		public void run() {
			// job code here

			System.out.println(new Date());
			//loadMvt();
		}

	};

	public void init() throws ServletException {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"conf/application-context.xml");
		ParametreService service = (ParametreService) context
				.getBean("parametreServiceImpl");
		
		PointageEngine engine = (PointageEngine) context.getBean("pointageEngineImpl");
	
		timer.scheduleAtFixedRate(
				task,
				3000,
				Integer.valueOf(service.byId("freqlecture").getValueparam()) * 1000 * 60);
	
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void destroy() {
		timer.cancel();
	}

}