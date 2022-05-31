package mod.br.profileManager;

import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

/**
 * @author BrokenRegistry
 * Classes that have to be passed thru Profile manager
 * Could be replaced by using {@code Object} and casting the class
 */
public class ClientClasses {
	private IGameOptions optionsObject;
	private GameSession  sessionObject;
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
		this.optionsObject = guiObject;
	}

	/**
	 * @param gameObject {@code GameSession} to set
	 */
	public ClientClasses(GameSession gameObject) {
		this.sessionObject = gameObject;
		this.optionsObject  = gameObject.options();
	}

	/**
	 * @return the guiObject
	 */
	public IGameOptions getOptionsObject() {
		return optionsObject;
	}

	/**
	 * @param guiObject the guiObject to set
	 * @return this for chaining purpose
	 */
	public ClientClasses setOptionsObject(IGameOptions guiObject) {
		this.optionsObject = guiObject;
		return this;
	}

	/**
	 * @return the gameObject
	 */
	public GameSession getSessionObject() {
		return sessionObject;
	}

	/**
	 * @param gameObject the gameObject to set
	 * @return this for chaining purpose
	 */
	public ClientClasses setSessionObject(GameSession gameObject) {
		this.sessionObject = gameObject;
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
