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

## Profiles Manager extended configuration file: Profiles.cfg

This mod allows to save and reuse user preferred configurations as profiles.<br>
Each profiles may affects all parameters or only some!<br>
Several partial profiles can be loaded together to be combined.<br>
For example, you may have some profiles that influences the galaxy size, shape and quality, while some other influences the races and their relations.<br>
There is an option to ask for a random parameter.<br>
Change may be done whwen loadin a game file.<br>
- And more... See User Manual.


### <u>User interfaces</u>

- "L" to **L**oad local to the GUI profiles parameters. (Race, Galaxy and Advanced)<br>
- "Shift-L" to **L**oad global profiles parameters.<br>
- "R" & "Shift-R" to load local and global Alternate profiles (As I use it as **_R**andom settings).<br>
- "D"  & "Shift-D" to reload the local and global **D**efault settings.<br>
- "I"  & "Shift-I" to reload the local and global **I**nitial settings.<br>
- "U" to **U**pdate profiles settings file.<br>
- And more... See User Manual.
  
  ### <u>Player Presets</u>
  
  The player may add as many profiles as necessary.<br>
  Each profiles is given a name. They are listed at the begining of the file and associated with an action.<br>
  Possible actions:<br>
  __Load:__ To Load the following profile (if not empty nor invalid)... The last one win! <br>
  __Savegui:__ The following profile will be set to the last in Gui configuration. <br>
  __Getgui:__ If not empty, the following profile will be set to the last Gui configuration. <br>
  __Savedefault:__ The following profile will be set to the default configuration. (The vanilla values)<br>
- And more... See User Manual.
  
  ### <u>Player Settings</u>
  
  Then all the useful parameters are listed with their allowed option.<br>
  _LOCAL ENABLE_<br>
  Each setting may be localy enabled / disabled for Load and Save options<br>
  - LOCAL ENABLE OPTIONS = [No, Save, Load, Both]<br>

_Value = Random_<br>

- Any setting may  be set to: Random<br>
- A range may be specified for the randomisation: Random min max<br>
- For String values, the range specify the options list range (start at 0)<br>
- _For String values, the allowed list may be specified: Range o1, o2, o3 (Not yet functional)_<br>
- And more... See User Manual.

  ## Extended Settings
  
  ### Selection of the distance between Empires:
- The default Star spacing can be adjusted.<br>
- The probability of Star Type can be adjusted.<br>
- The probability of Planet Type can be adjusted.<br>
- The available opponent Races can be filtered.<br>
- The rolling sequence of the flags can be changed.<br>
- Some Galaxy Pam zooming factors can be adjusted.<br>

---

---

### Additional features / changelog<br>

- 2022.06.27
  - Added option to break lines.
  - Added some comments
  - Try to have a non blank user manual!
- 0.95.00 (2022-06-26)
  - Full rewriting of the mod!
  - Added user Manual: Profiles.pdf
- 0.91.1beta (2022-04-02)
  - Added: Player color change in game file
  - Implemented change in game file -> (Beta)
  - Changed back file name from PresetsTest.cfg to Presets.cfg ... Sorry for the mess
- 0.90.2 (2022-04-02)
  - Updated to Xilmi Fusion 2020.03.29 <br>
  - Total reconstruction and stabilisation of the mod, now closer to Java's philosophy <br>
- 0.87.5 (2022-03-22)
  - No code change, but the mini Jar and window .exe should now be OK! <br>
- 0.87.4 (2022-03-22)
  - Remote build different from the local one... Try again <br>
- 0.87.3 (2022-03-22)
  - Update now working as it should<br>
- 0.87.2 (2022-03-18)
  - Update now working as it should<br>
- 0.87.1 (2022-03-18)
  - Updated to Xilmi Fusion 1.03.10<br>
  - Restored Maximize Empires spacing...<br>
  - Implemented Press "D" to reload **D**efault settings.<br>
  - Implemented Press "L" to load **L**ocal GUI settings. (Race, Galaxy and Advanced)<br>
  - Implemented Press "G" to Load **G**lobal GUI settings.<br>
  - Implemented Press "U" to **U**pdate global GUI settings.<br>
  - The presets are no more loaded at the start.<br>
  - The presets are read for each action, consequently the configuration file may be edited "live".<br>
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
