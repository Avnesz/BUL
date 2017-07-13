package bean;

public class Player {
    private String login;

    public static Player newUser(final String pLogin) {
        final Player player = new Player();
        player.setLogin(pLogin);
        return player;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *            the login to set
     */
    public void setLogin(final String login) {
        this.login = login;
    }
}
