package Foundation;
public class Point{

    private Double X;
    private String Y;
    private Double R;
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
