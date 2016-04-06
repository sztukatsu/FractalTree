import java.awt.*;import javax.swing.JPanel;
public class KochPanel extends JPanel
{
   int offset=7;
   private final int PANEL_WIDTH = 600;private final int PANEL_HEIGHT = 600;
   private int current,comp;
   public KochPanel (int currentOrder,int currentComp)
   {
      comp = currentComp;
      current=currentOrder;
      setBackground(Color.black);
      setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
    }
   
   private void drawFractal(Graphics page,int x1,int y1,double t,int order,Color color,int comp)
   {
      if (order==0)return;
      int x2=x1+(int)(Math.cos(Math.toRadians(t))*order*comp);
      int y2=y1+(int)(Math.sin(Math.toRadians(t))*order*comp);
      int gb = (int)(Math.random()*125);
      page.setColor(color);
      page.drawLine(x1,y1,x2,y2);
      drawFractal(page,x2,y2,t-20,order-1,new Color(0,gb+120,gb+120),comp);
      drawFractal(page,x2,y2,t+30,order-1,new Color(gb+120,0,0),comp);
    }
   public void paintComponent (Graphics page)
   {
      super.paintComponent(page);
      page.translate(300,400);
      //page.drawLine(0,100,0,0);
      drawFractal(page,0,100,-90,current,new Color(0,200,200),comp);
   }
   public void setOrder(int order){current=order;}
   public int getOrder(){return current;}
   public void setComp(int level){comp=level;}
}
