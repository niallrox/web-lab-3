package Database;

import Foundation.Data;

import javax.inject.Inject;
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
    @Inject
    private Data data;
    /**
     * отслеживает создание сессии
     * @param event
     */
    @Override
    public void sessionCreated(HttpSessionEvent event) {
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
     * отслеживает уничтожение сессии
     * @param event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        try {
            data.destroy(event.getSession().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
