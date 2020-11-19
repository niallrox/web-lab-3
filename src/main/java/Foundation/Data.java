package Foundation;

import Controller.Builder;
import Database.Database;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.LinkedList;

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
    public void init(String session) throws SQLException, IOException, ClassNotFoundException {
        database.connectSQL("database.properties");
        PointList.addAll(database.loadFromSQL(session));
    }

    private final LinkedList<Point> PointList = new LinkedList<Point>();

    Builder builder = new Builder();

    public LinkedList<Point> getPointList() {
        return PointList;
    }

    /**
     * добавляет постронную точку в коллекцию и в бд
     *
     * @throws SQLException
     */
    public void addTableRow() throws SQLException {
        Double x = point.getX();
        Double y = point.getY();
        Double r = point.getR();
        Point bean = builder.build(x, y, r, LocalTime.now());
        if (!(bean == null)) {
            PointList.add(bean);
            System.out.println(PointList.toString());
            database.addToSQL(bean);
        }
    }

    /**
     * удаляет данные из бд этой сессии при краше сервера/краше сессии
     * @param session
     * @throws SQLException
     */
    public void destroy(String session) throws SQLException {
        database.clearSQL(session);
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }
}