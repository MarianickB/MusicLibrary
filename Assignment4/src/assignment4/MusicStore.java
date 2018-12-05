package assignment4;

import java.util.ArrayList;

public class MusicStore {
	//ADD YOUR CODE BELOW HERE

	private MyHashTable <String, Song> titles = new MyHashTable <String, Song>(100);
	private MyHashTable <String, ArrayList<Song>> artists = new MyHashTable <String, ArrayList<Song>>(100);
	private MyHashTable <Integer, ArrayList<Song>> years = new MyHashTable <Integer, ArrayList<Song>>(100);


	//ADD YOUR CODE ABOVE HERE


	public MusicStore(ArrayList<Song> songs) {
		//ADD YOUR CODE BELOW HERE

		for(Song song : songs) {
			//Song temp = song;
			String title = song.getTitle();
			titles.put(title, song);
		}

		//System.out.println(titles.size());


		for(Song song : songs) {
			//Song temp = song;
			String artist = song.getArtist();

			if (artists.get(artist) == null) {
				ArrayList<Song> songList = new ArrayList<Song>();
				songList.add(song);
				artists.put(artist, songList);
			}

			else {

				artists.get(artist).add(song);
			}

			//System.out.println(artists.get(artist));

		}

		for(Song song : songs) {
			//Song temp = song;
			int year = song.getYear();

			if (years.get(year) == null) {
				ArrayList<Song> songList = new ArrayList<Song>();
				songList.add(song);
				years.put(year, songList);
			}

			else {
				years.get(year).add(song);
			}

			//System.out.println(years.size());


		}
		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Add Song s to this MusicStore
	 */
	public void addSong(Song s) {
		// ADD CODE BELOW HERE

		String artist = s.getArtist();
		String title = s.getTitle();
		int year = s.getYear();


		if(artists.get(artist).isEmpty()) {

			ArrayList<Song> songList = new ArrayList<Song>();
			songList.add(s);
			artists.put(artist, songList);

		}

		else {

			artists.get(artist).add(s);

		}

		if(years.get(year).isEmpty()) {

			ArrayList<Song> songList = new ArrayList<Song>();
			songList.add(s);
			years.put(year, songList);

		}

		else {

			years.get(year).add(s);

		}

			titles.put(title, s);		
			

		// ADD CODE ABOVE HERE
	}

	/**
	 * Search this MusicStore for Song by title and return any one song 
	 * by that title 
	 */
	public Song searchByTitle(String title) {
		//ADD CODE BELOW HERE

		Song s = this.titles.get(title);


		if(s == null) {

			return null;

		}


		return s; 

		//ADD CODE ABOVE HERE
	}

	/**
	 * Search this MusicStore for song by `artist' and return an 
	 * ArrayList of all such Songs.
	 */
	public ArrayList<Song> searchByArtist(String artist) {
		//ADD CODE BELOW HERE

		ArrayList<Song> songArtist = this.artists.get(artist);

		if(songArtist == null) {

			return null;

		}

		return songArtist;


		//ADD CODE ABOVE HERE
	}

	/**
	 * Search this MusicSotre for all songs from a `year'
	 *  and return an ArrayList of all such  Songs  
	 */
	public ArrayList<Song> searchByYear(Integer year) {
		//ADD CODE BELOW HERE

		ArrayList<Song> songYear = this.years.get(year);

		if(songYear == null) {

			return null;

		}

		return songYear;


		//ADD CODE ABOVE HERE

	}
}
