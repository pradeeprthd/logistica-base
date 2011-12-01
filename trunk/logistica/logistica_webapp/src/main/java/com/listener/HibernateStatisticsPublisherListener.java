package com.listener;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.jmx.StatisticsService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class HibernateStatisticsPublisherListener implements ServletContextListener {

    //private static final Log LOG = LogFactory.getLog(HibernateStatisticsPublisherListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(
            servletContextEvent.getServletContext());

        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName on = new ObjectName("Hibernate:type=statistics");
            StatisticsService mBean = (StatisticsService) appCtx.getBean("statisticsService");
            mBeanServer.registerMBean(mBean, on);
        } catch (Exception e) {
            System.out.println("Error registering Hibernate StatisticsService in the MBean server: " + e);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
