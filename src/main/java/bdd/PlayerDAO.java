package bdd;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import servlet.abstrait.GeneralException;
import utils.Constantes;
import utils.JsonUtils;
import bean.Player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PlayerDAO {
	private static PlayerDAO instance;
    private final List<Player> listPlayer = new ArrayList<Player>();

	private PlayerDAO() throws GeneralException {
		restore();
	}

	/**
	 * Singleton
	 * 
	 * @return
	 * @throws GeneralException
	 */
	public static synchronized PlayerDAO getInstance() throws GeneralException {
		if (instance == null) {
			instance = new PlayerDAO();
		}
		return instance;
	}

	/**
	 * Permet de restorer les donnees utilisateur
	 * 
	 * @throws GeneralException
	 */
	public void restore() throws GeneralException {
        final String json = JsonUtils.load(Constantes.PLAYER_PATH);
		final Gson gson = Constantes.GSON;
        listPlayer.clear();
        final Type listType = new TypeToken<ArrayList<Player>>() {
		}.getType();
        final List<Player> users = gson.fromJson(json, listType);
		if (users != null) {
            listPlayer.addAll(users);
		}
	}

	/**
	 * Permet de raffraichir les donnees utilisateur
	 * 
	 * @throws GeneralException
	 */
	public void refresh() throws GeneralException {
		final Gson gson = Constantes.GSON;
        final String json = gson.toJson(listPlayer);
        JsonUtils.save(Constantes.PLAYER_PATH, json);
	}

    /**
     * Renvoi l'utilisateur donnee
     * 
     * @param titre
     * @return
     */
    public Player getPlayer(final String login) {
        for (final Player player : listPlayer) {
            final String playerLogin = player.getLogin();

            if (login.equals(playerLogin)) {
                return player;
			}
		}
		return null;
	}

    /**
     * enregistre le joueur
     * 
     * @param player
     * @throws GeneralException
     */
    public void saveUser(final Player player) throws GeneralException {
        listPlayer.add(player);
		refresh();
	}
}
