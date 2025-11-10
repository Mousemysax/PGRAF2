package model.rasterops.clip;

import model.objectdata.Line;
import model.objectdata.Polygon;

public class SutherlandHodgeman {


    public Polygon clip(Polygon p,Polygon clip){
        Polygon result = new Polygon();
        Line[] clipLines= new Line[clip.size()];
        for (int i = 0; i < clip.size(); i++) {
            clipLines[i] = new Line(clip.getItem(i),clip.getItem((i+1)%clip.size()));
        }
        for(Line line:clipLines){
            result = new Polygon();

        }

        return  result;
    }
}
