package Database;

import Controller.Builder;
import Foundation.Data;
import Foundation.Point;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Класс для генерации айди сессии и соответсвенно взаимодействий связанных с ее созданием и уничтожением
 */
@WebListener
public class SessionId implements HttpSessionListener {

    private Database database;

    /**
     * отслеживает создание сессии и загружает из бд элементы ей принадлежащей
     * @param event
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        Data data = new Data();
        database = new Database();
        String session = event.getSession().getId();
        System.out.println("session created: " + session);
        try {
            data.init(event.getSession().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * отслеживает уничтожение сессии и удаляет из бд элементы принадлежащие ей
     * @param event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("session destroyed: " + event.getSession().getId());
        try {
            database.clearSQL(event.getSession().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
