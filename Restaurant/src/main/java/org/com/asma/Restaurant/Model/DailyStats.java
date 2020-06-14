package org.com.asma.Restaurant.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class DailyStats {
private String today;
private double todayAvgIncome;
public DailyStats() {	}

public DailyStats(String today, double todayAvgIncome) {

	this.today = today;
	this.todayAvgIncome = todayAvgIncome;
}

public String getToday() {
	return today;
}
public void setToday(String date) {
	this.today = date;
}
public double getTodayAvgIncome() {
	return todayAvgIncome;
}
public void setTodayAvgIncome(double todayAvgIncome) {
	this.todayAvgIncome = todayAvgIncome;
}


}
