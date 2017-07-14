/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils"],
function($, _, Utils) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.el = "#terrain";
		};
		
		this.load = function() {
		    var that = this;
		    Utils.load("getTerrain", null, function(data) {
		        if (data.codeRetour == 0) {
		            that.terrain = data.terrain;
		            that.render();
		        }
		    }, "POST");
		};
		
		this.render = function() {
            this.renderLayer("sousSol", this.terrain.sousSol);
            this.renderLayer("sol", this.terrain.sol);
            this.renderLayer("layer1", this.terrain.layer1);       
            
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
        
        this.makeEvents = function() {
        	var that = this;
        	$(".tile").click(function() {
        		var x = $(this).attr("x");
        		var y = $(this).attr("y");
        		that.terrain.sol[y][x] = "trou";
        		console.log("#sol .tile[x="+x+"][y="+y+"]");
        		$("#sol .tile[x="+x+"][y="+y+"]").attr("class", "tile trou");
        	});
        };
		
		this.init(parent);
	};
});