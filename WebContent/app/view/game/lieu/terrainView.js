/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "app/model/game/server/getTerrainModel"],
function($, _, Utils, GetTerrainModel) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.player = parent.player;
			this.terrain = this.player.terrain;
			this.getTerrainModel = new GetTerrainModel();
			this.el = "#terrain";
		};
		
		this.load = function() {
		    var that = this;
		    this.getTerrainModel.send(null, this.player.token, function(data) {
		        if (data.codeRetour == 0) {
		            that.terrain.create(data.terrain);
		            that.render();
		        }
		    });
		};
		
		this.render = function() {
			$(this.el).empty();
			
			var terrain = this.terrain.get();
			
			var sousSol = this.createLayer("sousSol");
			var sol = this.createLayer("sol");
			var layer1 = this.createLayer("layer1");
			for (var y in terrain) {
                var line = terrain[y];
                var sousSolLine = this.createLine();
                var solLine = this.createLine();
                var layer1Line = this.createLine();
                for (var x in line) {
                    var layers = line[x];
                    var sousSolTile = this.createTile(x, y, layers.sousSol);
                    var solTile = this.createTile(x, y, layers.sol);
                    var layer1Tile = this.createTile(x, y, layers.layer1);
                    
                    sousSolLine.append(sousSolTile);
                    solLine.append(solTile);
                    layer1Line.append(layer1Tile);
                }
                sousSol.append(sousSolLine);
                sol.append(solLine);
                layer1.append(layer1Line);
            }
            
            $(this.el).append(sousSol);
            $(this.el).append(sol);
            $(this.el).append(layer1);
            
            this.makeEvents();
        };
        
        this.createLayer = function(layerName) {
            var layer = $("<div></div>");
            layer.addClass("layer");
            layer.attr("id", layerName);
            return layer;
        };
        this.createLine = function() {
            var line = $("<div></div>");
            line.addClass("tileLine");
            return line;
        };
        this.createTile = function(x, y, tileBean) {
            var tile = $("<div></div>");
            tile.addClass("tile");
            tile.addClass(tileBean.id);
            tile.attr("x", x);
            tile.attr("y", y);
            return tile;
        };

        this.refresh = function(modifications) {
            if (modifications) {
                for (var i in modifications) {
                    var modification = modifications[i];
                    
                    var x = modification.x;
                    var y = modification.y;
                    var id = modification.id;
                    var layer = modification.layer;
                    
                    this.terrain.update(layer, x, y, id);
                }
            }
        };
        
        this.makeEvents = function() {
        	var that = this;
        	$(".tile").click(function() {
        		that.player.move($(this).offset());
        	});
        };
		
		this.init(parent);
	};
});