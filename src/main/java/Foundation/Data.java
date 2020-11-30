package Foundation;

import Controller.AreaChecker;
import Database.Database;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    @Inject
    private Point point;

    private String session;
    @Inject
    private Database database;

    /**
     * вызвается при создании сессии и добавляет в коллекцию все точки ей принадлежащие
     *
     * @param session
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized void init(String session) throws SQLException, IOException, ClassNotFoundException {
        database.connectSQL("database.properties");
        pointList.addAll(database.loadFromSQL(session));
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
        Double x = point.getX();
        Double y = Double.parseDouble(point.getY());
        Double r = point.getR();
        Point bean = areaChecker.build(x, y, r, LocalTime.now());
        if (!(bean == null)) {
            pointList.add(bean);
            System.out.println(pointList.toString());
//            database.addToSQL(bean);
        }
    }

    /**
     * удаляет данные из бд этой сессии при краше сервера/краше сессии
     * @param session
     * @throws SQLException
     */
    public synchronized void destroy(String session) throws SQLException {
        database.clearSQL(session);
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }
}