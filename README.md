# Remnants of the Precursors
Remnants of the Precursors is a Java-based modernization of the original Master of Orion game from 1993.
# Links
Official website: https://www.remnantsoftheprecursors.com/<br>
Community subreddit: https://www.reddit.com/r/rotp/<br>
Download build: https://rayfowler.itch.io/remnants-of-the-precursors/<br>
Download source: https://github.com/rayfowler/rotp-public/<br>
# Xilmi Fusion Mod
This is a fork with Xilmi Fusion mod<br>
Download site: https://github.com/Xilmi/rotp-coder/releases/<br>
# Added features by BrokenRegistry
## Extended Configuration file: Presets.cfg
Allowing full or partial control over most settings for new game start.
### Working sequence:
- The Standard configuration file (Remnant.cfg) is loaded.
- The Extended presets file (Presets.cfg) is loaded and activated if ENABLE is either "Load" or "Both".
- The Player may change the parameters with the standard in game interface.
- When the "Start" button is activated, the presets file is updated if ENABLE is either "Save" or "Both".
### Player Presets
The player may add as many presets as necessary.<br>
Each preset is given a name. They are listed at the begining of the file and associated with an action.<br>
Possible actions:<br>
__Load:__ To Load the following presets (if not empty nor invalid)... The last one win! <br>
__Save:__ The following presets will be set to the last in game configuration. <br>
__Update:__ If not empty, the following presets will be set to the last in game configuration. <br>
__Default:__ The following presets will be set to the default configuration. (The one before the presets file was loaded)<br>
Then all the useful settings are listed with their presets.
## Extended Settings
### Selection of the distance between opponents:
- Option to specify a typical number of stars around each opponent.<br>
- Option to maximize Distances between opponents.
# Planned additional features
## Extended Configurationfile: Postsets.cfg
About the same things as Preset.cfg, but applied when loading a game.
## Some more extended Settings
more options to configure galaxy.

