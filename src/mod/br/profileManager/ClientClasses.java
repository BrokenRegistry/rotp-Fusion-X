package mod.br.profileManager;

import rotp.model.game.GameSession;
import rotp.model.game.IGameOptions;

/**
 * @author BrokenRegistry
 * Classes that have to be passed thru Profile manager
 * Could be replaced by using {@code Object} and casting the class
 */
public class ClientClasses {
	private IGameOptions options;
	private IGameOptions options2;
	private GameSession  session;
//	private boolean Initial = false;

	/**
	 * Just a new empty class!
	 */
	public ClientClasses() {
	}
	/**
	 * @param guiObject {@code IGameOptions} to set
	 */
	public ClientClasses(IGameOptions guiObject) {
		options  = guiObject;
		options2 = guiObject;
	}
	/**
	 * @param guiObject2 {@code IGameOptions} to set
	 * @param guiObject {@code IGameOptions} to set
	 */
	public ClientClasses(IGameOptions guiObject2, IGameOptions guiObject) {
		options  = guiObject;
		options2 = guiObject2;
	}
	/**
	 * @param gameObject {@code GameSession} to set
	 */
	public ClientClasses(GameSession gameObject) {
		session  = gameObject;
		options  = gameObject.options();
		options2 = options;
	}
	/**
	 * @return the guiObject
	 */
	public IGameOptions options() {
		return options;
	}
	/**
	 * @return the second guiObject
	 */
	public IGameOptions option2() {
		return options2;
	}
//	/**
//	 * @param guiObject the guiObject to set
//	 * @return this for chaining purpose
//	 */
//	public ClientClasses setOptionsObject(IGameOptions guiObject) {
//		this.options = guiObject;
//		return this;
//	}
	/**
	 * @return the gameObject
	 */
	public GameSession session() {
		return session;
	}
//	/**
//	 * @param gameObject the gameObject to set
//	 * @return this for chaining purpose
//	 */
//	public ClientClasses setSessionObject(GameSession gameObject) {
//		this.session = gameObject;
//		return this;
//	}
//	/**
//	 * @return the initial
//	 */
//	public boolean isInitial() {
//		return Initial;
//	}
//	/**
//	 * @param initial the boolean to set
//	 */
//	public void setInitial(boolean initial) {
//		Initial = initial;
//	}
}
