The purpose of this mod is to play with the parameters before the game is started.
It don't need to have variables saved from a session to another, and then should not change the saved games structure. And it’s important not to!
In a way to be certain to not change the saved games, this mod won't add any variable to the existing classes.
Instead, its class will have static variables that can be initialized from any class without being stored by them.
This may not be the cleanest way to integrate my mod, but it’ll be the safest.