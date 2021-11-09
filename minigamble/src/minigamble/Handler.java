package minigamble;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<Entity> entidades = new LinkedList<Entity>();
	
	public void tick() {
		for (int i= 0 ; i < entidades.size(); i++) {
			Entity tempEntidad = entidades.get(i);
			tempEntidad.tick();
			
		}
	}

	public void render(Graphics g) {
		for (int i= 0 ; i < entidades.size(); i++) {
			Entity tempEntidad = entidades.get(i);
			tempEntidad.render(g);
			
		}
	}
	
	public void addEntity(Entity entidad) {
		this.entidades.add(entidad);
		
	}
	
	public void removeObject(Entity entidad) {
		this.entidades.remove(entidad);
	}
}
