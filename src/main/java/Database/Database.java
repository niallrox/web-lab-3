package Database;

import Foundation.Point;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

public class Database implements Serializable {
    private Connection connect;
    private PreparedStatement ps;
    private Statement statement;
    /**
     * Подключается к базе данных
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connectSQL(String file) throws IOException, ClassNotFoundException, SQLException {
        FileInputStream bd = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(bd);
        String url = properties.getProperty("location");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        Class.forName("org.postgresql.Driver");
        connect = DriverManager.getConnection(url, login, password);
        statement = connect.createStatement();
    }
    /**
     * возвращает коллекцию результатов принадлежащих этой сессии
     * @return
     * @throws SQLException
     * @throws NullPointerException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public synchronized LinkedList<Point> loadFromSQL(String session) throws SQLException, NullPointerException, IOException, ClassNotFoundException {
        LinkedList<Point> col = new LinkedList<>();
        ps = connect.prepareStatement("SELECT * FROM data WHERE session = ?;");
        ps.setString(7,session);
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            Point point = new Point();
            point.setId(res.getInt("id"));
            point.setX(res.getDouble("x"));
            point.setY(res.getDouble("y"));
            point.setR(res.getDouble("r"));
            point.setResult(res.getString("result"));
            point.setTime(res.getString("time"));
            col.add(point);
        }
        return col;
    }

    /**
     * добавляет элемент в бд
     * @param data
     * @throws SQLException
     * @throws NullPointerException
     */
    public synchronized void addToSQL(Point data) throws SQLException, NullPointerException {
        ps = connect.prepareStatement("INSERT INTO data (id, x, y, r, " +
                "result, time, session) " +
                "VALUES (currval('idSGsequence'), ?, ?, ?, ?, ?, ?);");
        ps.setDouble(1, data.getX());
        ps.setDouble(2, data.getY());
        ps.setDouble(3, data.getR());
        ps.setString(4, data.getResult());
        ps.setString(5, data.getTime());
        ps.setString(6, data.getSession());
        ps.execute();
    }

    /**
     * Возвращает следующее значение для id
     * @return
     * @throws SQLException
     */
    public synchronized int getSQLId() throws SQLException {
        ResultSet res = statement.executeQuery("SELECT nextval('idSGsequence');");
        res.next();
        return res.getInt(1);
    }

    /**
     * очищаются элемент принадлижащие этой сессии(вставить в том месте где отлавливается завершение сессии)
     * @param session
     * @throws SQLException
     */
    public synchronized void clearSQL(String session) throws SQLException {
        ps = connect.prepareStatement("DELETE FROM data WHERE session = ?;");
        ps.setString(7, session);
        ps.execute();
    }
}