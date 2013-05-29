package map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

public class SkyroomMap extends TiledMap {

	public SkyroomMap(String ref) throws SlickException {
		super(ref);
		// TODO Auto-generated constructor stub
	}
	
	public SkyroomMap(String ref, String string) throws SlickException {
		super(ref,string);
	}

	public void destroyTile() throws SlickException{
		for(TileSet ts:tileSets){
			if(ts.tiles!=null)
				ts.tiles.destroy();
		}
	}

	
}
