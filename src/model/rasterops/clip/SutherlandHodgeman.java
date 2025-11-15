package model.rasterops.clip;

import model.objectdata.Line;
import model.objectdata.Point2D;
import model.objectdata.Polygon;

import java.awt.*;

public class SutherlandHodgeman {


    public Polygon clip(Polygon p,Polygon clip){
        Color c = p.getColor();
        Polygon result = p;
        Line[] clipLines= new Line[clip.size()];
        for (int i = 0; i < clip.size(); i++) {
            clipLines[i] = new Line(clip.getItem(i),clip.getItem((i+1)%clip.size()));
        }
        for(Line line:clipLines){
            p = result;
            result = new Polygon();

            for (int i = 0; i < p.size(); i++) {
                Point2D currentPoint = p.getItem(i);
                Point2D prevPoint = p.getItem((i-1+p.size()) % p.size());
                Point2D intersection = line.getIntersectionLine(line,new Line(prevPoint,currentPoint));

                if(line.getSide(currentPoint)>0){
                    if(line.getSide(prevPoint)<0&&intersection!=null){
                        result.addItem(intersection);
                    }
                    result.addItem(currentPoint);
                }
                else if(line.getSide(prevPoint)>0&&intersection!=null){
                    result.addItem(intersection);
                }
            }
        }
        result.setColor(c);
        return  result;
    }
}
