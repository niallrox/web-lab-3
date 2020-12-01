package Database;

import Foundation.Point;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;

@Named("Database")
@SessionScoped
public class Database implements Serializable {
    private Connection connect;
    private PreparedStatement ps;
    @Resource(lookup = "java:jboss/Database")
    private DataSource ds;
    /**
     * возвращает коллекцию результатов принадлежащих этой сессии
     *
     * @return
     * @throws SQLException
     * @throws NullPointerException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public LinkedList<Point> loadFromSQL(String session) throws SQLException, NullPointerException, IOException, ClassNotFoundException {
        connect = ds.getConnection();
        LinkedList<Point> col = new LinkedList<>();
        ps = connect.prepareStatement("SELECT * FROM data WHERE session = ?;");
        ps.setString(1, session);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Point point = new Point();
            point.setX(res.getDouble("x"));
            point.setY(String.valueOf(res.getDouble("y")));
            point.setR(res.getDouble("r"));
            point.setResult(res.getString("result"));
            point.setTime(res.getString("time"));
            col.add(point);
        }
        connect.close();
        return col;
    }

    /**
     * добавляет элемент в бд
     *
     * @param data
     * @throws SQLException
     * @throws NullPointerException
     */
    public void addToSQL(Point data) throws SQLException {
        connect = ds.getConnection();
        ps = connect.prepareStatement("INSERT INTO data (x, y, r, " +
                "result, time, session) " +
                " VALUES ( ?, ?, ?, ?, ?, ?);");
        ps.setDouble(1, data.getX());
        ps.setDouble(2, Double.parseDouble(data.getY()));
        ps.setDouble(3, data.getR());
        ps.setString(4, data.getResult());
        ps.setString(5, data.getTime());
        ps.setString(6, data.getSession());
        ps.execute();
        connect.close();
    }

    /**
     * очищаются элемент принадлижащие этой сессии
     *
     * @param session
     * @throws SQLException
     */
    public void clearSQL(String session) throws SQLException {
        connect = ds.getConnection();
        ps = connect.prepareStatement("DELETE FROM data WHERE session = ?;");
        ps.setString(1, session);
        ps.execute();
        connect.close();
    }
}