/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils"],
function($, _, Utils) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.player = parent.player;
			this.terrain = this.player.terrain;
			this.el = "#terrain";
		};
		
		this.load = function() {
		    var that = this;
		    Utils.load("getTerrain", null, function(data) {
		        if (data.codeRetour == 0) {
		            that.terrain.create(data.terrain);
		            that.render();
		        }
		    }, "POST");
		};
		
		this.render = function() {
			$(this.el).empty();
			
			this.renderLayer("sousSol", this.terrain.get("sousSol"));
            this.renderLayer("sol", this.terrain.get("sol"));
            this.renderLayer("layer1", this.terrain.get("layer1"));
            
            this.makeEvents();
        };

        this.renderLayer = function(id, layer) {
            var container = $("<div></div>");
            container.addClass("layer");
            container.attr("id", id);
            
            for (var y in layer) {
                var line = layer[y];
                
                var tileLine = $("<div></div>");
                tileLine.addClass("tileLine");
                for (var x in line) {
                    var tuile = line[x];
                    
                    var tile = $("<div></div>");
                    tile.addClass("tile");
                    tile.addClass(tuile.id);
                    tile.attr("x", x);
                    tile.attr("y", y);
                    
                    tileLine.append(tile);
                }
                container.append(tileLine);
            }
            
            $(this.el).append(container);
        };
        
        this.refresh = function(newTerrain) {
            if (newTerrain) {
                this.refreshLayer(this.terrain.get("sousSol"), newTerrain.sousSol);
                this.refreshLayer(this.terrain.get("sol"), newTerrain.sol);
                this.refreshLayer(this.terrain.get("layer1"), newTerrain.layer1);
            }
        };
        
        this.refreshLayer = function(layer, newLayer) {
            if (newLayer) {
                for (var y in newLayer) {
                    var newLine = newLayer[y];
                    for (var x in newLine) {
                        console.log("update : ", x, y);
                        console.log(newTuile);
                        var newTuile = newLine[x];
                        layer[y][x] = newTuile;
                    }
                }
            }
        };
        
        this.makeEvents = function() {
        	var that = this;
        	$(".tile").click(function() {
        		var x = $(this).attr("x");
        		var y = $(this).attr("y");
        		that.player.useCurrentTool(x, y);
        	});
        };
		
		this.init(parent);
	};
});