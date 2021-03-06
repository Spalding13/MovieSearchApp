# MovieSearchApp
  Use your Android device to search for movie titles on IMDB using the OMDb API. 
The result is a grid page of small panels with the movie title, year of production and a movie poster.
Images are loaded with the help with the Picasso, a powerful library for downloading and caching images for Android.
Styled to match the MovieSearch Web application: https://github.com/Spalding13/MovieSearch 

This app uses the Open Movie Database API for data: http://www.omdbapi.com/ 

* Tested with Samsung Galaxy S8

# Screenshots
### Home Page:
* A LinearLayout showing a greeting message, an EditText view and a button to launch the request:
![Alt text](rsz_1panel1.jpg?raw=true)



### Results Page:
* A GridView layout showing panels in two rows, containing the movie title and year in TextViews:
![Alt text](rsz_panel2.jpg?raw=true)


## Built With
* Java
* Android Studio
* Picasso

### Possible issues
* Some oversized images might fail to load correctly. Possible reason might be in image resizing by the Picasso library
* The layouts might clip over for smaller devices. 

### Future Improvements
* Add more search options
* Use the results to get more detailed information for each movie by tapping on a panel. Information such as movie synopsis, budged, etc.

### Acknowledgments
* OMDb - Open Movie Database API
* Picasso - https://square.github.io/picasso/
