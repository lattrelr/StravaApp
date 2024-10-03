# StravaApp
Android compose app written in kotlin

On startup will check if a valid token is stored in the shared pref database.
If there is no token, it will redirect to OAUTH2 strava login.
When strava login returns, it will run again and get a token with the code.

Once a valid token is acquired, it will fetch "running" segments in Chicago
and display them in the view.

### BUILD
Import into Android Studio.</br>
Created with "Android Studio Koala Feature Drop | 2024.1.2 Patch"</br>
Uses retrofit2 for auth and REST calls</br>

### TEST
Tested on emulator in Android Studio</br>
--Medium Phone API 35, Android 15</br>

### TODOs
Auth:</br>
-Update to use AppAuth instead of doing it the hard way</br>
-Clean up oauth2 logic.  There is probably too much work done in the view.</br>
-Add some sort of logout button</br>
-Maybe make a view just for auth</br>
-Security issues.  Client secret stored in plaintext in code. Token printed throughout.</br>

Segments:</br>
-Add user input to pick location to view segments</br>
-Could add screen when you click on the segment to show more details (on a map?)</br>

Error handling:</br>
-Particularly in coroutines and API calls.  It mostly assumes success.</br>

### SCREENSHOTS
![screen shot](https://github.com/user-attachments/assets/6655fc17-af61-4296-9f99-c8695579c751)
![screen shot](https://github.com/user-attachments/assets/5eb536d8-77c4-4cf2-9e0d-2e8fc4f1da36)
