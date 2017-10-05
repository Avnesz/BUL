/*global define */
define(["jquery", 
        "app/model/game/player/inventoryModel",
        "app/model/game/lieu/terrainModel",
        "app/model/game/player/equipementModel"], 
function($, Inventory, Terrain, Equipement) {
	'use strict';

	return function(token) {
	    this.token = null;
	    this.terrain = new Terrain();
	    this.inventory = new Inventory();
	    this.equipement = new Equipement();
	    this.position = {
	    		x : 150,
	    		y : 150
	    };
	    
	    this.speed = 2;
	    this.destination = null;
	    
	    this.init = function(token) {
	        this.token = token;
	    };
	    
	    this.addToInventory = function(itemId, nbr) {
	    	this.inventory.put(itemId, nbr);
	    };
	    /**
	     * Choisit l'item et le place dans une main
	     */
	    this.pickItem = function(itemId) {
	    	this.equipement.pickItem(itemId, this.inventory);
	    };
	    this.setCursor = function(main) {
	        this.equipement.setCursor(main);
	    };
	    this.useCurrentTool = function(x, y) {
	        this.inventory.use(this.equipement.getCurrent(), x, y, this);
	        this.equipement.refresh(this.inventory);
	    };
	    
	    this.move = function(position) {
	        this.destination = {
	                x : position.left,
	                y : position.top
	        };
	    };
	    
	    this.init(token);
	};
});