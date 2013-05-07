package game;

import static org.lwjgl.opengl.GL11.*;  
  
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.Random;  
import java.util.concurrent.Callable;  
  
import org.newdawn.slick.AppGameContainer;  
import org.newdawn.slick.BasicGame;  
import org.newdawn.slick.GameContainer;  
import org.newdawn.slick.Graphics;  
import org.newdawn.slick.Input;  
import org.newdawn.slick.SlickException;  
import org.newdawn.slick.geom.Line;  
import org.newdawn.slick.geom.Vector2f;  
import org.newdawn.slick.opengl.SlickCallable;  
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
  
public class Movement extends BasicGameState {  
  
    public Movement(int i) {  
        
    }  
  

  
    public static class LightingBoltEffect {  
  
        Collection<Line> segments;  
  
        int totalTime;  
  
        int currentTime;  
  
        private float lineWidth;  
  
        public LightingBoltEffect(int time, Collection<Line> segments, float lineWidth) {  
            this.totalTime = time;  
            this.segments = segments;  
            this.currentTime = time;  
            this.lineWidth = lineWidth;  
        }  
  
        public void update(int delta) {  
            currentTime -= delta;  
            if (currentTime <= 0)  
                currentTime = 0;  
        }  
  
        public void render() {  
            float alpha = (float) currentTime / (float) totalTime;  
            glPushMatrix();  
            glColor4f(alpha, alpha, alpha, alpha);  
            glLineWidth(lineWidth);  
            glBegin(GL_LINES);  
            {  
                for (Line segment : segments) {  
                    glVertex(segment.getStart());  
                    glVertex(segment.getEnd());  
                }  
            }  
            glEnd();  
            glPopMatrix();  
        }  
  
        public boolean isDone() {  
            return currentTime <= 0;  
        }  
          
  
        public void glVertex(Vector2f v) {  
            glVertex3f(v.x, v.y, 0);  
        }  
          
    }  
  
    
    public void init(GameContainer gc,StateBasedGame sbg) throws SlickException {  
    	generateLightingBolt(new Vector2f(150, 240), new Vector2f(290,150), 100);  
    }  
  
    protected void generateLightingBolt(Vector2f p0, Vector2f p1, int duration) {  
        Collection<Line> segments = new ArrayList<Line>();  
          
        segments.add(new Line(p0, p1));  
          
        float offset = 200f;  
        double probability = 0.3; // probability to generate new partitions  
        float height = 50.0f;   
          
        Random random = new Random();  
          
        int partitions = 4;  
          
        for (int i = 0; i < partitions; i++) {  
          
            Collection<Line> newSegments = new ArrayList<Line>();  
          
            for (Line segment : segments) {  
          
                Vector2f midPoint = segment.getStart().copy().add(segment.getEnd()).scale(0.5f);  
          
                Vector2f perpendicular = midPoint.copy().add(90);  
                perpendicular.normalise().scale(random.nextFloat() * offset - (offset / 2));  
                midPoint.add(perpendicular);  
                  
                if (random.nextFloat() < probability) {  
                    // generate new branch  
                    Vector2f direction = midPoint.copy().sub(segment.getStart());  
                    direction.add(random.nextFloat() * height);  
                    newSegments.add(new Line(midPoint.copy(), midPoint.copy().add(direction)));  
                }  
          
                newSegments.add(new Line(segment.getStart().copy(), midPoint.copy()));  
                newSegments.add(new Line(midPoint.copy(), segment.getEnd().copy()));  
            }  
          
            segments = newSegments;  
          
            offset /= 2;  
        }  
        lightingBoltEffect = new LightingBoltEffect(duration, segments, 2.0f);  
    }  
  
    private LightingBoltEffect lightingBoltEffect;  
  
    public void update(GameContainer container, StateBasedGame sbg,int delta) throws SlickException {  
        lightingBoltEffect.update(delta);  
        if (!lightingBoltEffect.isDone())  
            return;  
  
        Input input = container.getInput();  
        if (!input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))  
            return;  
  
        int mouseX = input.getMouseX();  
        int mouseY = input.getMouseY();  
  
        Random random = new Random();  
        int duration = random.nextInt() % 600 + 100;  
        generateLightingBolt(new Vector2f(mouseX, mouseY), new Vector2f((mouseX + 300), mouseY), duration);  
    }  
  
    public void render(GameContainer container, StateBasedGame sbg,Graphics g) throws SlickException {  
        SlickCallable.enterSafeBlock();  
        lightingBoltEffect.render();  
        SlickCallable.leaveSafeBlock();  
    }

	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 100;
	}  
}