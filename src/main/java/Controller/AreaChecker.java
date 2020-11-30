package Controller;

import Foundation.Point;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class AreaChecker {
    private SimpleDateFormat sOut= new SimpleDateFormat("hh:mm:ss");
    private SimpleDateFormat sIn = new SimpleDateFormat("h:m:s");

    /**
     * очевидно билдит точку
     * @param x
     * @param y
     * @param r
     * @param time
     * @return
     */
    public synchronized Point build(Double x, Double y, Double r, LocalTime time) {
        System.out.println(x + " " + y + " " + r);
        Point bean = new Point();
        bean.setX(x);
        bean.setY(String.valueOf(y));
        bean.setR(r);
        bean.setTime(timeFormat(time));
        bean.setResult(result(x,y,r));
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        bean.setSession(sessionId);
        return bean;
    }

    /**
     * форматирует время в нормальное
     * @param time
     * @return
     */
    public String timeFormat(LocalTime time){
        Date date = null;
        try {
            date = sIn.parse(String.valueOf(time));
        } catch (ParseException e){
            e.printStackTrace();
        }
        return sOut.format(date);
    }
    public String result(double x, double y, double r) {
        if (x >= 0 && y >= 0 && y <= (-0.5 * x + r / 2)) {
            return "True";
        }
        if (x <= 0 && y >= 0 && (x * x + y * y) <= (r * r) / 4) {
            return "True";
        }
        if (y <= 0 && y >= -r && x >= 0 && x <= r / 2) {
            return "True";
        }
        return "False";
    }

}