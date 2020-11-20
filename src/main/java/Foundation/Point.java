package Foundation;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("oneRowBean")
@SessionScoped
public class Point implements Serializable {

    private Double X = 0d;
    private Double Y;
    private Double R = 1d;
    private String result;
    private String time;
    private String session;

    public Point(){}

    public String getResult() {return result;}

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getTime() {return time;}
    public Double getX() {return X;}
    public Double getY() {return Y;}
    public Double getR() {return R;}


    public void setR(Double r) {R = r;}
    public void setX(Double x) {X = x;}
    public void setY(Double y) {Y = y;}

    public void setTime(String time) {
        this.time = time;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "Point{" +
                "X=" + X +
                ", Y=" + Y +
                ", R=" + R +
                ", result='" + result + '\'' +
                ", time='" + time + '\'' +
                ", session='" + session + '\'' +
                '}';
    }
}
