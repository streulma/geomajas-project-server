/*
 * This is part of Geomajas, a GIS framework, http://www.geomajas.org/.
 *
 * Copyright 2008-2011 Geosparc nv, http://www.geosparc.com/, Belgium.
 *
 * The program is available in open source according to the GNU Affero
 * General Public License. All contributions in this program are covered
 * by the Geomajas Contributors License Agreement. For full licensing
 * details, see LICENSE.txt in the project root.
 */
package org.geomajas.gwt.client.widget;

import java.util.HashMap;

import com.smartgwt.client.widgets.Canvas;

/**
 * Layout behaving like a deck of cards. Only one card visible at a time.
 * 
 * @author Jan De Moerloose
 * 
 */
public class CardLayout extends Canvas {

	private HashMap<Object, Canvas> cards = new HashMap<Object, Canvas>();

	private Canvas currentCard;

	/**
	 * Add a card to the deck and associate it with the specified key.
	 * 
	 * @param key key associated to the card
	 * @param card the card
	 */
	public void addCard(Object key, Canvas card) {
		card.setWidth100();
		card.setHeight100();
		card.setLeft(0);
		card.setTop(0);
		addChild(card);
		cards.put(key, card);
		currentCard = card;
	}

	/**
	 * Show the card associated with the specified key.
	 * 
	 * @param key key associated to the card that should be shown
	 */
	public void showCard(Object key) {
		if (cards.containsKey(key)) {
			currentCard = cards.get(key);
			currentCard.show();
			for (Canvas card : cards.values()) {
				if (card != currentCard) {
					card.hide();
				}
			}
		}
	}

	/**
	 * Returns the current card.
	 * 
	 * @return the current card.
	 */
	public Canvas getCurrentCard() {
		return currentCard;
	}

}
