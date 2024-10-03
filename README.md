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
![screen shot](https://private-user-images.githubusercontent.com/165870164/373092505-a93d239c-ab1a-42ba-af41-8f92678d6636.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjc5MjI4MjAsIm5iZiI6MTcyNzkyMjUyMCwicGF0aCI6Ii8xNjU4NzAxNjQvMzczMDkyNTA1LWE5M2QyMzljLWFiMWEtNDJiYS1hZjQxLThmOTI2NzhkNjYzNi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDAzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAwM1QwMjI4NDBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0wYWYwYWU4M2UxMDQ4ZWJjYzk5YmRjODI0NWZhZGYxZWQxZTg1ODlhMDNkYzg5YzBiMjc0MzkzNjhkMDZmNjQ4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.u00XQcswB59Jf0KDC_fO_NtzldNcOyHeSr9OqWRiVR8)
![screen shot](https://private-user-images.githubusercontent.com/165870164/373092508-79f13d94-d5e1-4d35-9e81-c3c8f307a21a.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3Mjc5MjI4MjAsIm5iZiI6MTcyNzkyMjUyMCwicGF0aCI6Ii8xNjU4NzAxNjQvMzczMDkyNTA4LTc5ZjEzZDk0LWQ1ZTEtNGQzNS05ZTgxLWMzYzhmMzA3YTIxYS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQxMDAzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MTAwM1QwMjI4NDBaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0yZDRjYzU5MmUwMjQxYTE3ZGVkNTMxY2UyMTFlM2YxZWFhZTFhMTE4MTkxMDg2ZDNkODc5Mzc2NTAzNmYyMDZkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.tyj1_Bf777QG9yaH-YBwK-jVihSp2KuUjC8eP9YY86Q)
