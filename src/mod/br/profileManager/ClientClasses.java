package mod.br.profileManager;

import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

/**
 * @author BrokenRegistry
 * Classes that have to be passed thru Profile manager
 * Could be replaced by using {@code Object} and casting the class
 */
public class ClientClasses {
	private IGameOptions guiObject;
	private GameSession  gameObject;
	private boolean Initial = false;

	/**
	 * Just a new empty class!
	 */
	public ClientClasses() {
	}

	/**
	 * @param guiObject {@code IGameOptions} to set
	 */
	public ClientClasses(IGameOptions guiObject) {
		this.guiObject = guiObject;
	}

	/**
	 * @param gameObject {@code GameSession} to set
	 */
	public ClientClasses(GameSession gameObject) {
		this.gameObject = gameObject;
	}

	/**
	 * @return the guiObject
	 */
	public IGameOptions getGuiObject() {
		return guiObject;
	}

	/**
	 * @param guiObject the guiObject to set
	 * @return this for chaining purpose
	 */
	public ClientClasses setGuiObject(IGameOptions guiObject) {
		this.guiObject = guiObject;
		return this;
	}

	/**
	 * @return the gameObject
	 */
	public GameSession getGameObject() {
		return gameObject;
	}

	/**
	 * @param gameObject the gameObject to set
	 * @return this for chaining purpose
	 */
	public ClientClasses setGameObject(GameSession gameObject) {
		this.gameObject = gameObject;
		return this;
	}


	/**
	 * @return the initial
	 */
	public boolean isInitial() {
		return Initial;
	}


	/**
	 * @param initial the initial to set
	 */
	public void setInitial(boolean initial) {
		Initial = initial;
	}
}
