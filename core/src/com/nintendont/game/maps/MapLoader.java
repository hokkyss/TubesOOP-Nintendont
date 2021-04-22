package com.nintendont.game.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapGroupLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.nintendont.game.entities.Direction;

import java.util.Iterator;


public class MapLoader {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Layers
    private final int[] SEA_LAYER = new int[] {0};
    private final int[] TUNDRA_LAYER = new int[] {1};
    private final int[] GRASSLAND_LAYER = new int[] {2};
    private final int[] MOUNTAIN_LAYER = new int[] {3};
    private final int[] BORDER_LAYER = new int[] {4};

    public MapLoader() {
        this.map = new TmxMapLoader().load("Maps/Map.tmx");
        this.renderer = new OrthogonalTiledMapRenderer(map);
    }

    public TiledMap getMap() {
        return this.map;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return this.renderer;
    }

    public Batch getBatch() {
        return this.renderer.getBatch();
    }

    public void centerToScreen(OrthographicCamera camera) {
        TiledMapTileLayer layer0 = (TiledMapTileLayer) map.getLayers().get(0);
        Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2, layer0.getHeight() * layer0.getTileHeight() / 2, 0);
        camera.position.set(center);
    }

    public void initAnimationTiles() {
        // Flowers
        Array<StaticTiledMapTile> frameTiles = new Array<StaticTiledMapTile>();
        Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("Flowers1").iterator();

        while (tiles.hasNext()) {
            TiledMapTile tile = tiles.next();
            if (tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals("flowers")) {
                frameTiles.add((StaticTiledMapTile) tile);
            }
        }

        AnimatedTiledMapTile flowersFrame = new AnimatedTiledMapTile(1/ 2.5f, frameTiles);

        MapGroupLayer groupLayer = (MapGroupLayer) map.getLayers().get(GRASSLAND_LAYER[0]);

        TiledMapTileLayer flowerLayer = (TiledMapTileLayer) groupLayer.getLayers().get("Flowers");

        for(int x = 0; x < flowerLayer.getWidth(); x++)
            for (int y = 0; y < flowerLayer.getHeight(); y++) {
                if (flowerLayer.getCell(x, y) != null) {
                    TiledMapTile tile = flowerLayer.getCell(x, y).getTile();
                    if (tile.getProperties().containsKey("animation") && tile.getProperties().get("animation", String.class).equals("flowers")) {
                        flowerLayer.getCell(x, y).setTile(flowersFrame);
                    }
                }
            }
    }

    public boolean isWalkable(int currX, int currY, int dx, int dy) {
        Direction dir = Direction.getDirection(dx, dy);
        TiledMapTile tile;

        int x = currX + dx;
        int y = currY + dy;

        // check TUNDRA layer
        MapGroupLayer tundraGroupLayer = (MapGroupLayer) map.getLayers().get(TUNDRA_LAYER[0]);

        TiledMapTileLayer tundraLayer1 = (TiledMapTileLayer) tundraGroupLayer.getLayers().get("Tundra");
        TiledMapTileLayer tundraLayer2 = (TiledMapTileLayer) tundraGroupLayer.getLayers().get("Tundra 2");

        if (tundraLayer1.getCell(x, y) != null) {
            tile = tundraLayer1.getCell(x, y).getTile();
            if (!isWalkableTile(tile))
                return false;
        }

        if (tundraLayer2.getCell(x, y) != null) {
            tile = tundraLayer2.getCell(x, y).getTile();
            if (!isWalkableTile(tile))
                return false;
        }

        // check MOUNTAIN layer
        MapGroupLayer mountainGroupLayer = (MapGroupLayer) map.getLayers().get(MOUNTAIN_LAYER[0]);

        TiledMapTileLayer mountainLayer1 = (TiledMapTileLayer) mountainGroupLayer.getLayers().get("Mountain");
        TiledMapTileLayer mountainLayer2 = (TiledMapTileLayer) mountainGroupLayer.getLayers().get("Mountain 2");

        // special case for mountain top block
        if (mountainLayer1.getCell(currX, currY) != null) {
            TiledMapTile currentTile = mountainLayer1.getCell(currX, currY).getTile();

            // if in special block, and wants to go up, not walkable
            if (dir == Direction.UP &&
                    currentTile.getProperties().containsKey("specialBlocked") &&
                    currentTile.getProperties().get("specialBlocked", Boolean.class)
            )
                return false;
        }

        if (mountainLayer1.getCell(x, y) != null) {
            tile = mountainLayer1.getCell(x, y).getTile();

            // if not in special block, wants to go down to special block, not walkable
            if (dir == Direction.DOWN &&
                tile.getProperties().containsKey("specialBlocked") &&
                tile.getProperties().get("specialBlocked", Boolean.class)
            )
                return false;

            if (!isWalkableTile(tile))
                return false;
        }
        if (mountainLayer2.getCell(x, y) != null) {
            tile = mountainLayer2.getCell(x, y).getTile();
            if (!isWalkableTile(tile))
                return false;
        }


        // check SEA layer
        TiledMapTileLayer seaLayer = (TiledMapTileLayer) map.getLayers().get(BORDER_LAYER[0]);
        if (seaLayer.getCell(x, y) != null) {
            tile = seaLayer.getCell(x, y).getTile();
            if (!isWalkableTile(tile))
                return false;
        }

        return true;
    }

    public boolean isWalkableTile(TiledMapTile tile) {
        return !(tile.getProperties().containsKey("blocked") &&
                tile.getProperties().get("blocked", Boolean.class));
    }

    public Terrain getTerrain(int x, int y) {
        // check TUNDRA layer
        MapGroupLayer tundraGroupLayer = (MapGroupLayer) map.getLayers().get(TUNDRA_LAYER[0]);

        TiledMapTileLayer tundraLayer1 = (TiledMapTileLayer) tundraGroupLayer.getLayers().get("Tundra");
        TiledMapTileLayer tundraLayer2 = (TiledMapTileLayer) tundraGroupLayer.getLayers().get("Tundra 2");

        if (tundraLayer1.getCell(x, y) != null || tundraLayer2.getCell(x, y) != null) {
            return Terrain.TUNDRA;
        }

        // check MOUNTAIN layer
        MapGroupLayer mountainGroupLayer = (MapGroupLayer) map.getLayers().get(MOUNTAIN_LAYER[0]);

        TiledMapTileLayer mountainLayer1 = (TiledMapTileLayer) mountainGroupLayer.getLayers().get("Mountain");
        TiledMapTileLayer mountainLayer2 = (TiledMapTileLayer) mountainGroupLayer.getLayers().get("Mountain 2");

        if (mountainLayer1.getCell(x, y) != null || mountainLayer2.getCell(x, y) != null) {
            return Terrain.MOUNTAIN;
        }

        // check GRASSLAND layer
        MapGroupLayer grasslandGroupLayer = (MapGroupLayer) map.getLayers().get(GRASSLAND_LAYER[0]);

        TiledMapTileLayer grasslandLayer1 = (TiledMapTileLayer) grasslandGroupLayer.getLayers().get("Grassland");
        TiledMapTileLayer grasslandLayer2 = (TiledMapTileLayer) grasslandGroupLayer.getLayers().get("Flowers");

        if (grasslandLayer1.getCell(x, y) != null || grasslandLayer2.getCell(x, y) != null) {
            return Terrain.GRASSLAND;
        }

        // check SEA layer
        TiledMapTileLayer seaLayer = (TiledMapTileLayer) map.getLayers().get(SEA_LAYER[0]);
        if (seaLayer.getCell(x, y) != null) {
            return Terrain.SEA;
        }

        return null;
    }

    public void render() {
        this.renderer.render(SEA_LAYER);
        this.renderer.render(TUNDRA_LAYER);
        this.renderer.render(GRASSLAND_LAYER);
        this.renderer.render(MOUNTAIN_LAYER);
        this.renderer.render(BORDER_LAYER);

        AnimatedTiledMapTile.updateAnimationBaseTime();
    }
}
