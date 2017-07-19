/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function() {
	    this.terrain = null;
	    this.modifications = {
	            sousSol : [],
	            sol : [],
	            layer1 : []
	    };
	    
	    this.get = function(layer) {
            return this.terrain[layer];  
        };
	    
	    this.create = function(terrain) {
	        this.terrain = terrain;
	    };
	    
	    this.resetModification = function() {
	        this.modifications.sousSol = [];
	        this.modifications.sol = [];
	        this.modifications.layer1 = [];
	    };
	    
	    this.update = function(layer, x, y, id) {
	        var modifyLayer = this.modifications[layer];
	        modifyLayer.push({
	            x: x,
	            y: y,
	            id : id
	        });
	        
	        //var terrainLayer = this.terrain[layer];
	        //terrainLayer[y][x] = id;
	        //$("#"+layer).find(".tile[x="+x+"][y="+y+"]").attr("class", "tile "+id);
	    };
	};
});