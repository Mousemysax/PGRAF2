package model.objectdata;

public class Line {
    public final Point2D start;
    public final Point2D end;

    public Line(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }

    public Line(Point2D start, Point2D end, int color) {
        this.start = start;
        this.end = end;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.start = new Point2D(x1, y1);
        this.end = new Point2D(x2, y2);
    }

    public Line(int x1, int y1, int x2, int y2, int color) {
        this.start = new Point2D(x1, y1);
        this.end = new Point2D(x2, y2);
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }

    public Line orientatedY(){
        if(start.y > end.y){
            return new Line(end,start);
        }
        return new Line(start,end);
    }

    public Line orientatedX(){
        if(start.x > end.x){
            return new Line(end,start);
        }
        return new Line(start,end);
    }
    public Line shortened(){
        return new Line(start.x,start.y,end.x,end.y-1);
    }
    public int getIntersectionY(int y){
        int y1 = this.start.y;
        int y2 = this.end.y;
        if(y <y1||y>y2){
            return -1;
        }
        int x1 = this.start.x;
        int x2 = this.end.x;
        double k = (double) (y2 - y1) / (x2 - x1);
        if(Double.isNaN(k)||Double.isInfinite(k)){
            k = Integer.MAX_VALUE;
        }
        double q = y1 - k * x1;
        return (int) ((y - q)/k);
    }

    public Point2D getIntersectionLine(Line a, Line b){
        Point2D result = new Point2D(0,0);
        int x1 = a.getStart().x;
        int x2 = a.getEnd().x;
        int x3 = b.getStart().x;
        int x4 = b.getEnd().x;
        int y1 = a.getStart().y;
        int y2 = a.getEnd().y;
        int y3 = b.getStart().y;
        int y4 = b.getEnd().y;

        double denominator = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if (denominator == 0) {
            return null;
        }

        double term1 = (x1 * y2 - x2 * y1);
        double term2 = (x3 * y4 - x4 * y3);

        result.x = (int) ((term1 * (x3 - x4) - term2 * (x1 - x2)) / denominator);
        result.y = (int) ((term1 * (y3 - y4) - term2 * (y1 - y2)) / denominator);

//        if (!a.isOnLine(result)||!b.isOnLine(result))
//            return null;

        return result;


    }

    public boolean isOnLine(Point2D p){

        return (p.x>start.x&&p.x<end.x||p.x>end.x&&p.x<start.x)&&(p.y>start.y&&p.y<end.y||p.y>end.y&&p.y<start.y);
    }

    public int getDistanceToPoint(Point2D point){
        int y1 = this.start.y;
        int y2 = this.end.y;
        int x1 = this.start.x;
        int x2 = this.end.x;
        int px = point.x;
        int py = point.y;

        double n = (y2 - y1) * px - (x2 - x1) * py + x2 * y1 - y2 * x1;
        double d = Math.sqrt((y2 - y1)*(y2 - y1) + (x2 - x1)*(x2 - x1));

        return (int) (n/d);
    }

    public int getSide(Point2D p) {

        double x1 = start.x;
        double y1 = start.y;
        double x2 = end.x;
        double y2 = end.y;

        double px = p.x;
        double py = p.y;

        double value = (y2 - y1) * px - (x2 - x1) * py + x2 * y1 - y2 * x1;

        if (Math.abs(value) < 1e-9)
            return 0;

        return (value > 0) ? 1 : -1;
    }

    public Double[] getNormal(){
        Double[] normal = new Double[2];
        int y1 = this.start.y;
        int y2 = this.end.y;
        int x1 = this.start.x;
        int x2 = this.end.x;
        normal[0] = (double) (y1-y2);
        normal[1] = (double) (x2-x1);
        float length = (float) Math.sqrt(normal[0]*normal[0]+normal[1]*normal[1]);
        normal[0] /= length;
        normal[1] /= length;
        return normal;
    }

}
