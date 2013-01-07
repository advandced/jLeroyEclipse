package fr.la.jproductbase.metier;

import java.io.Serializable;
import java.util.*;

public class Chrono implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Calendar m_start;
	Calendar m_stop;

	public Chrono() {
		m_start = new GregorianCalendar();
		m_stop = new GregorianCalendar();
	}

	public void start() {
		m_start.setTime(new Date());
	}

	public void stop() {
		m_stop.setTime(new Date());
	}

	public long getMilliSec() {
		return (m_stop.getTimeInMillis() - m_start.getTimeInMillis());
	}

	public long getSec() {
		return (m_stop.getTimeInMillis() - m_start.getTimeInMillis()) / 1000;
	}

	public void printMilliSec() {
		if (getMilliSec() <= 0)
			System.out.println("Vous n'avez pas arrété le chronomètre");
		else
			System.out.println("Temps d'exécution : " + getMilliSec() + " ms");
	}
}
