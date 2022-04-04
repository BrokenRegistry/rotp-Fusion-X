package mod.br.settings;

import java.util.LinkedHashMap;
import java.util.List;

import br.config.AbstractGroup;
import br.config.AbstractCfgFile;
import rotp.Rotp;
import rotp.model.game.IGameOptions;

public class UserSettings extends AbstractCfgFile<IGameOptions> {
    
    // ------------------------------------------------------------------------
    // Constructors
    //
	public UserSettings() {}
	
	// ========================================================================
	//  Abstract Methods
	//
	@Override protected String getFilePath () { return Rotp.jarPath(); }
	@Override protected String getFileName () { return "Presets.cfg"; }
	@Override protected String getSettingUserActionName () { return "PRESET ACTION"; }
	@Override protected List<String> getSettingUserActionOptions () { 
				return List.of("File To UI", "File To Game", 
						"UI To File", "Game To File", "Initial To File",
						"UI Update File", "Game Update File", "Initial Update File");
    			}
	@Override protected String getSettingUserActionFirst () { return "UI To File"; }
    /*
	 * Add all the groups to the Map with an easy key
	 */
	@Override
	protected void initGroupMap(IGameOptions options) {
      groupMap = new LinkedHashMap<String, AbstractGroup<IGameOptions>>();
      groupMap.put("RACE",     new Group_Race(options));
      groupMap.put("GALAXY",   new Group_Galaxy(options));
      groupMap.put("ADVANCED", new Group_Advanced(options));
      groupMap.put("MODNAR",   new Group_Modnar(options));
      groupMap.put("BR",       new Group_BrokenRegistry(options));
	}
}
