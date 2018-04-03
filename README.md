# CSI3370
This is the term project for CSI3370 at Oakland University Winter 2018.
Authors: Nick Furlo, Kyle Smith, Kip Tucker, Graham Walker, Hassan Abdullah, Anton Cataldo, Jonathan Lacoursiere

To set up the project by pulling from GitHub, do the following
1) Open android studio, select checkout from version control > github
2) Enter github login info if this is your first time using it with AS
3) Enter URL for github repository
4) Clone the repository
5) Click yes to open
6) Click Cancel when asked to add files from ./idea to git
7) Let the gradle build finish
8) Right click the app folder > Synchronize 'app'
9) Set up a virtual or physical android device and begin coding!

Use the VCS menu to Commit Changes or Update Project.


Troubleshooting

"Error: Please select Android DSK" - Resync Gradle with icon next to save icon. Alternatively File>Sync roject with gradle files  

"Gradle sync failed: java.lang.NullPointerException" - Clean project under Build menu and make project.

Run button greyed out - Resync Gradle with icon next to save icon.

"Google Play Services update needed" - Deploy to physical device or create a new virtual device with the Google Play store (with AVD manager) and update play services in the Play Store (on new virtual device)
