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

import java.util.Iterator;


public class MapLoader {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Layers
    private final int[] SEA_LAYER = new int[] {0};
    private final int[] TUNDRA_LAYER = new int[] {1};
    private final int[] GRASSLAND_LAYER = new int[] {2};
    private final int[] MOUNTAIN_LAYER = new int[] {3};

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

    public void render() {
        this.renderer.render(SEA_LAYER);
        this.renderer.render(TUNDRA_LAYER);
        this.renderer.render(GRASSLAND_LAYER);
        this.renderer.render(MOUNTAIN_LAYER);

        AnimatedTiledMapTile.updateAnimationBaseTime();
    }
}
