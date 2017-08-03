/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function() {
	    this.terrain = null;
	    this.modifications = [];
	    
	    this.get = function(x, y) {
	        if (x != undefined && y != undefined) return this.terrain.layers[y][x]; 
            return this.terrain.layers;  
        };
	    
	    this.create = function(terrain) {
	        this.terrain = terrain;
	    };
	    
	    this.resetModification = function() {
	        this.modifications = [];
	    };
	    
	    this.modify = function(layer, x, y, id) {
	        this.modifications.push({
	            x : x,
	            y : y,
	            id : id,
	            layer : layer
	        });
	        this.update(layer, x, y, id);
	    };
	    
	    this.update = function(layer, x, y, id) {
	        var terrainLayers = this.get(x, y);
            terrainLayers[layer].id = id;
            $("#"+layer).find(".tile[x="+x+"][y="+y+"]").attr("class", "tile "+id);
        };
	};
});