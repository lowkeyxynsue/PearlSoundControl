# Xynsue (source project)

Target: Minecraft Java 1.21.11, Fabric, Java 21.

## Features in this starter build
- Mod Menu configuration entry
- Moon/fire enable switches and HEX fields (default `#7A00FF`)
- Ender Pearl throw volume and Enderman teleport/landing volume controls
- Config saved to `.minecraft/config/xynsue.json`

## Important status
This is a **source project**, not a compiled JAR. It has not been compiled or tested in PojavLauncher/MojoLauncher. The build environment used to prepare it had no network access to download Gradle and Minecraft dependencies. Do not rename the ZIP to `.jar`.

The purple moon/fire PNG overrides are generated starter textures. The configuration currently stores the HEX values; applying arbitrary live HEX values to the texture atlas still needs a runtime texture recolour implementation. The included PNGs use the default purple `#7A00FF`.

## Build
Use a computer or Android Java IDE/terminal with internet access and JDK 21:
1. Open this folder as a Gradle project.
2. Run `gradle build` (or generate/use a Gradle wrapper).
3. The output JAR should appear in `build/libs/`.
4. Install Fabric API and Mod Menu 17.0.0 for Minecraft 1.21.11 in the same `mods` folder.

## Install after successful build
Copy `build/libs/Xynsue-1.0.0.jar` into the instance's `mods` folder. Keep Fabric API and Mod Menu installed. Restart the game, open Mod Menu, select Xynsue, and open its settings.

## Notes
- Pearl landing is represented by the Enderman teleport sound (`entity.enderman.teleport`) in this first implementation.
- The mod is client-side; it does not need to be installed on the server.
