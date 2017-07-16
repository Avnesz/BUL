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
			this.el = "#terrain";
		};
		
		this.load = function() {
		    var that = this;
		    Utils.load("getTerrain", null, function(data) {
		        if (data.codeRetour == 0) {
		            that.player.terrain = data.terrain;
		            that.render();
		        }
		    }, "POST");
		};
		
		this.render = function() {
			$(this.el).empty();
			
			this.renderLayer("sousSol", this.player.terrain.sousSol);
            this.renderLayer("sol", this.player.terrain.sol);
            this.renderLayer("layer1", this.player.terrain.layer1);       
            
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
                    tile.addClass(tuile);
                    tile.attr("x", x);
                    tile.attr("y", y);
                    
                    tileLine.append(tile);
                }
                container.append(tileLine);
            }
            
            $(this.el).append(container);
        };
        
        /**
         * Rafraichit certaines partis du terrain qui ont été modifiées
         */
        this.refresh = function(terrain) {
			this.refreshLayer("sousSol", terrain.sousSol);
            this.refreshLayer("sol", terrain.sol);
            this.refreshLayer("layer1", terrain.layer1);       
        };
        this.refreshLayer = function(id, layer) {
            for (var y in layer) {
                var line = layer[y];
                for (var x in line) {
                    var tuile = line[x];
                    $("#"+id).find(".tile[x="+x+"][y="+y+"]").attr("class", "tile "+tuile);
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