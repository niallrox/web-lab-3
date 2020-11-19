package Controller;

import Foundation.Data;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@ManagedBean(name = "controller", eager = true)
@SessionScoped
public class Controller {
    /**
     * Класс осуществляет проверку и хранит коллекцию с результатами
     */
    @ManagedProperty(value = "#{data}")
    private List<Data> data = Collections.synchronizedList(new ArrayList<>());
    private int id = 0;
    private double x;
    private double y;
    private double r;
    private String condition;

    public Controller(){

    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public double getX() {
        return x;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public List<Data> getData() {
        return data;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    /**
     * Отслеживает попадание точки в график
     * @param x
     * @param y
     * @param r
     * @return
     */
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
    private boolean checkData(double x, double y, double r) {
        Double[] xInterval = {-4.0, -3.0, -2.0, -1.0, 0.0, 1.0, 2.0, 3.0, 4.0};
        Double[] rInterval = {1.0, 1.5, 2.0, 2.5, 3.0};
        if (Arrays.asList(xInterval).contains(x) && (y > -3 && y < 3) && Arrays.asList(rInterval).contains(r)) {
            return true;
        } else return false;
    }
}

