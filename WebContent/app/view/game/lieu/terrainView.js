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
            this.renderLayer(this.terrain.sol);
            this.renderLayer(this.terrain.layer1);
        };

        this.renderLayer = function(id, layer) {
            var container = $("<div></div>");
            container.addClass("page");
            container.attr("id", id);
            
            for (var i in layer) {
                var line = layer[i];
                
                var tileLine = $("<div></div>");
                tileLine.addClass("tileLine");
                for (var j in line) {
                    var tuile = line[j];
                    
                    var tile = $("<div></div>");
                    tile.addClass("tile");
                    tile.addClass(tuile);
                    
                    tileLine.append(tile);
                }
                container.append(tileLine);
            }
            
            $(this.el).append(container);
        };
		
		this.init(parent);
	};
});