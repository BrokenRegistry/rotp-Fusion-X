# Remnants of the Precursors
Remnants of the Precursors is a Java-based modernization of the original Master of Orion game from 1993.
## Links
Official website: https://www.remnantsoftheprecursors.com/<br>
Community subreddit: https://www.reddit.com/r/rotp/<br>
Download build: https://rayfowler.itch.io/remnants-of-the-precursors/<br>
Download source: https://github.com/rayfowler/rotp-public/<br>
## Xilmi Fusion Mod
This is a fork with Xilmi Fusion mod<br>
Download site: https://github.com/Xilmi/rotp-coder/releases/<br>

---
# Added features by BrokenRegistry
## Extended Configuration file: Presets.cfg
Allowing full or partial control over most settings for new game start.
- ### _Working sequence:_
  - The Standard configuration file (Remnant.cfg) is loaded.
  - The Extended presets file (Presets.cfg) is loaded and activated if ENABLE is either "Load" or "Both".
  - The Player may change the parameters with the standard in game interface.
  - When the "Start" button is activated, the presets file is updated if ENABLE is either "Save" or "Both".
### <u>Player Presets</u>
The player may add as many presets as necessary.<br>
Each preset is given a name. They are listed at the begining of the file and associated with an action.<br>
Possible actions:<br>
__Load:__ To Load the following presets (if not empty nor invalid)... The last one win! <br>
__Save:__ The following presets will be set to the last in game configuration. <br>
__Update:__ If not empty, the following presets will be set to the last in game configuration. <br>
__Default:__ The following presets will be set to the default configuration. (The one before the presets file was loaded)<br>
### <u>Player Settings</u>
Then all the useful settings are listed with their allowed option.<br>
_LOCAL ENABLE_<br>
Each setting may be localy enabled / disabled for Load and Save options<br>
- LOCAL ENABLE OPTIONS = [No, Save, Load, Both]<br>
- Any Local or Global "NO" has priority.<br>

_Value = Random_<br>
- Any setting may  be set to: Random<br>
- A range may be specified for the randomisation: Random min max<br>
- For String values, the range specify the options list range (start at 0)<br>
- _For String values, the allowed list may be specified: Range o1, o2, o3 (Not yet functional)_<br>
### <u>User interface</u>
In every configuration panel<br>
- Press "R" to reload the configuration file<br>
- Press "U" to update the configuration file<br>
## Extended Settings
### Selection of the distance between Empires:
- Option to specify a typical number of stars around each Empire.<br>
- Option to maximize Distances between Empires.

---
---
### Additional features / changelog<br>
- 0.87.1 (2022-03-)
  - Updated to Xilmi Fusion 1.03.10<br>
  - Restored Maximize Empires spacing...<br>
- 0.86.1 (2022-03-14)
  - Added user range option to randomised settings (min max)<br>
  - Added key actions to UI "R" to Reload "U" to Update<br>
- 0.85.1 (2022-03-12)
  - Added Load "Local Enable" Management<br>
  - Added option to randomise settings<br>
- 0.84.1 (2022-03-11)
  - Added Save "Local Enable" for each sections<br>
- 0.83.1 (2022-03-09)
  - New start on GitHub, extension of Xilmi Fusion MOD v1.03.6<br>

---
---
# Planned additional features
### _Extended Configurationfile:_ Postsets.cfg
- About the same things as Preset.cfg, but applied when loading a game.<br>
### _Some more extended Settings_
- More options to configure galaxy.
- Adding Modnar new races
