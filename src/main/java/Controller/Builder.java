package Controller;

import Foundation.Point;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Builder {
    Controller controller = new Controller();
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
    public Point build(Double x, Double y, Double r, LocalTime time) {
        Point bean = new Point();
        bean.setX(x);
        bean.setY(y);
        bean.setR(r);
        bean.setTime(timeFormat(time));
        bean.setResult(controller.result(x,y,r));
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        bean.setSession(sessionId);
        return bean;
    }
    public synchronized String timeFormat(LocalTime time){
        Date date = null;
        try {
            date = sIn.parse(String.valueOf(time));
        } catch (ParseException e){
            e.printStackTrace();
        }
        return sOut.format(date);
    }

}