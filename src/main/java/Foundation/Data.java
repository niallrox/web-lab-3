package Foundation;

import Controller.AreaChecker;
import Database.Database;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Named("data")
@SessionScoped
public class Data implements Serializable {

    private Double X = 0d;
    private String Y;
    private Double R = 1d;
    private String result;
    private String time;
    private String session;

    @Inject
    private Database database;

    /**
     * вызвается при создании сессии и добавляет в коллекцию все точки ей принадлежащие
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @PostConstruct
    public void init() {
        System.out.println(database);
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        try {
            pointList.addAll(database.loadFromSQL(sessionId));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final List<Point> pointList = Collections.synchronizedList(new LinkedList<>());

    AreaChecker areaChecker = new AreaChecker();

    public List<Point> getPointList() {
        return pointList;
    }

    /**
     * добавляет постронную точку в коллекцию и в бд
     *
     * @throws SQLException
     */
    public synchronized void addTableRow() throws SQLException {
        Double x = getX();
        Double y = Double.parseDouble(getY());
        Double r = getR();
        Point bean = areaChecker.build(x, y, r, LocalTime.now());
        if (!(bean == null)) {
            pointList.add(bean);
            System.out.println(pointList.toString());
            database.addToSQL(bean);
        }
    }

    /**
     * удаляет данные из бд этой сессии при краше сервера/краше сессии
     * @throws SQLException
     */
    @PreDestroy
    public void destroy() {
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        String sessionId = session.getId();
        try {
            database.clearSQL(sessionId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String getResult() {return result;}

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {return time;}
    public Double getX() {return X;}
    public String getY() {return Y;}
    public Double getR() {return R;}


    public void setR(Double r) {R = r;}
    public void setX(Double x) {X = x;}
    public void setY(String y) {Y = y;}

    public void setTime(String time) {
        this.time = time;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "X=" + X +
                ", Y='" + Y + '\'' +
                ", R=" + R +
                ", result='" + result + '\'' +
                ", time='" + time + '\'' +
                ", session='" + session + '\'' +
                '}';
    }
}