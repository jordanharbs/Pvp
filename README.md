# JORDY NZ PvP Tools — menu build

This is an updated source project for Minecraft Java 26.1.2 + Fabric.

## Included and implemented

- Right Shift opens the JORDY NZ PvP Tools menu in-game.
- The menu has a working ON/OFF button for the Totem Counter HUD.
- The HUD counts Totems of Undying in the player's inventory and displays the count at the top-left.

## Not implemented yet

- Fire overlay height slider / hide-fire feature. The menu shows this as a disabled upcoming feature; it does not pretend the feature works yet.
- Offhand totems are not included in the current counter.

## Build

GitHub Actions builds the mod with Java 25 and Gradle 9.7.1. The project uses the Fabric 26.1.2 documentation's key-mapping and HUD APIs.

The code has not been run through a GitHub Actions build in your repository yet. If the build reports an error, send the full error log and I can correct it.

## Upload

1. Extract the ZIP.
2. In your GitHub repository, choose **Add file → Upload files**.
3. Upload the contents of this folder, including the hidden `.github` folder.
4. Commit the upload.
5. Open **Actions**, wait for the workflow to finish, and download the `JORDY-NZ-PvP-Tools` artifact.
6. Install only the normal `.jar`, not a `-sources.jar`.


Build fix: client entrypoint Java files are placed under `src/main/java` so the standard Gradle Java source set compiles them. The mod metadata still marks the mod as client-only.
