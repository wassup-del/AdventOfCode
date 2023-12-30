//Bharath Yelamali
//Adv Programmin P2
//this program creates a list of tiles and can add tiles
//draw tiles, raise tiles, lower tiles
//delete tiles, delete tiles in a certain space,
//and shuffle the tiles

import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;

public class TileManager {
	private ArrayList<Tile> TileList;

	// constructor
	public TileManager() {
		TileList = new ArrayList<Tile>();
	}

	// adds a tile to the list
	public void addTile(Tile rect) {
		TileList.add(rect);
	}

	// draws all the tiles from beginning of list to end
	public void drawAll(Graphics g) {
		for (int i = 0; i < TileList.size(); i++) {
			TileList.get(i).draw(g);
		}
	}

	// Check to see if the points, x,y
	// is inside the tile;
	// input: coordinates x and y, and the Tile
	// output: true if x and y points are in tile
	// false if not
	private boolean TileContainsPoint(Tile t, int x, int y) {

		if ((x > t.getX()) && (x < t.getX() + t.getWidth()) && 
				(y > t.getY()) && (y < t.getY() + t.getHeight())) {
			return true;
		} else {
			return false;
		}
	}

	public int tileSeek(int x, int y) {
		int num = 0;
		// go to each tile in the list
		for (int i = TileList.size() - 1; i >= 0; i--) {
			Tile currentTile = TileList.get(i);
			// if clicked move tile to the top
			if (TileContainsPoint(currentTile, x, y) == true) {
				num = i;
			}

		}
		return num;
	}
	// move the topmost tile to the top of list.
	// input: x and y coordinates
	// output: none
	public void raise(int x, int y) {
		int i = tileSeek(x, y);
		if (i == 1) {
			TileList.remove(i);
			TileList.add(TileList.size(), TileList.get(i));
			return;
		}

	}

	// move topmost tile to bottom of list
	// input: x and y coordinates
	// output: none
	public void lower(int x, int y) {
		int i = tileSeek(x, y);
		if (i == 1) {
			TileList.remove(i);
			TileList.add(0, TileList.get(i));
			return;

		}
	}

	// if coordinates touch any tiles,
	// delete topmost of tile from list
	// input: x and y coordinates
	// output: none
	public void delete(int x, int y) {
		// go to each tile in the list
		for (int i = TileList.size() - 1; i >= 0; i--) {
			Tile currentTile = TileList.get(i);
			if (TileContainsPoint(currentTile, x, y) == true) {
				// if clicked
				TileList.remove(i);
				return;
			}
		}
	}
	// if coordinates touch any tiles,
	// delete all tiles from list
	// input: x and y coordinates
	// output: none
	public void deleteAll(int x, int y) {
		// go to each tile in the list
		for (int i = TileList.size() - 1; i >= 0; i--) {
			Tile currentTile = TileList.get(i);

			if (TileContainsPoint(currentTile, x, y) == true) {
				// if clicked
				TileList.remove(i);
			}
		}
	}

	// every tile moves to a random position.
	// input: width and height of the screen
	// output: none
	// exception: if x/y limit is equal to or
	// smaller than 0, throw IllegalArgumentException.
	public void shuffle(int width, int height) {
		// go to each tile in the list

		for (int i = TileList.size() - 1; i >= 0; i--) {
			Tile currentTile = TileList.get(i);
			int xlimit = width - currentTile.getWidth(); // x
			int ylimit = height - currentTile.getHeight();// y

			// checks to see if x/y limit is too small.
			if (xlimit <= 0 || ylimit <= 0) {
				throw new IllegalArgumentException("Width and/or height is too small!");
			}

			// move the rectangle somewhere random on the screen
			int randomX = (int) (Math.random() * (xlimit));
			int randomY = (int) (Math.random() * (ylimit));
			currentTile.setX(randomX);
			currentTile.setY(randomY);

			// shuffle the spot of the tile in the list
			Collections.shuffle(TileList);

		}
	}

}
