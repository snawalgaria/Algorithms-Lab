import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Rumours {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            int n = Integer.parseInt(br.readLine());

            List<Point2D> cords = new ArrayList<>();
            for(int i=0;i<n;i++){
                String[] cord = br.readLine().split(" ");

                cords.add(new Point2D.Double(Double.parseDouble(cord[0]),Double.parseDouble(cord[1])));
            }

            int len = cords.size();

            double area=0.0D;
            for(int i=0;i<len;i++){
            	
                Point2D curr = cords.get(i);
                Point2D next = cords.get((i+1)%len);
                
                area+= curr.getX() * next.getY() - curr.getY() * next.getX();
            }

            StringBuilder out = new StringBuilder();
            out.append("Case #").append(tc).append(": ").append(Math.abs(area/2));

            System.out.println(out);
                br.readLine();
        }
    }


}


