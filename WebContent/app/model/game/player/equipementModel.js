/*global define */
define(["jquery"], 
function($) {
    'use strict';

    return function() {
        this.main = {
                gauche : null,
                droite : null,
                graine : null
        };
        
        this.cursor = null;
        
        /**
         * Permet de selectionner un objet de l'inventaire
         */
        this.pickItem = function(itemId, inventory) {
            var item = inventory.get(itemId);
        	var main = "gauche";
            
            if (item.isGraine) main = "graine";
            else {
                if (!this.main.gauche) main = "gauche";
                else if (!this.main.droite) main = "droite";
                else if (this.cursor) main = this.cursor;
            }
            this.setMain(main, item);
            if (!this.cursor) this.setCursor(main);
        };
        
        /**
         * Permet de mettre un objet dans une main
         */
        this.setMain = function(main, item) {
        	var itemId = "";
        	if (item) itemId = item.id;
        	
            this.main[main] = itemId;
        	$(".interface-outils ul .outil#"+main+" .item").attr("class", "item "+itemId);
            if (item && item.nbr > 0) {
                $(".interface-outils ul .outil#"+main+" .nbr").html(item.nbr);
                $(".interface-outils ul .outil#"+main+" .nbr").show();
            }else {
            	$(".interface-outils ul .outil#"+main+" .nbr").hide();
            }
        };
        
        /**
         * Change la position du curseur
         */
        this.setCursor = function(main) {
        	this.cursor = main;
        	$(".interface-outils ul .outil").each(function(index, element) {
                $(element).removeClass("choose");
            });
            $(".interface-outils ul .outil#"+main).addClass("choose");
        };

        /**
         * Raffraichie le nombre d'item en main
         */
        this.refresh = function(inventory) {
            var itemId = this.main.graine;
            var item = inventory.get(itemId);
            
            if (item.nbr > 0) {
                this.setMain("graine", item);
            }else {
                this.setMain("graine", null);
                if (this.cursor == "graine") this.setCursor(null);
            }
        };
        
        /**
         * Renvoit l'objet utilise
         */
        this.getCurrent = function() {
            var main = this.cursor;
            return this.main[main];
        };
        
    };
});