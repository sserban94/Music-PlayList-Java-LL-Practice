package com.serbanscorteanu.classes;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
	private String name;
	private String artist;
	private ArrayList<Song> songs;

	public Album(String name, String artist) {
		this.name = name;
		this.artist = artist;
		songs = new ArrayList<>();
	}

	public boolean addSong(String title, double duration) {
		// return true if added successfully
		if (findSong(title) != null) {
			System.out.println("Error - Song already in playlist");
			return false;
		}
		Song song = new Song(title, duration);
		songs.add(song);
		return true;
	}

	private Song findSong(String title) {
		// return song if found else null
		for (Song song : songs) {
			if (song.getTitle() == title) {
				return song;
			}
		}
		return null;
	}

	public boolean addToPlayList(int trackNo, LinkedList<Song> playlist) {
		// return true if it exists and successfully added else false
		// add the specific song from the album to this playlist
		if (!this.songs.isEmpty()) {
			if (trackNo > 0 && trackNo <= songs.size()) {
				Song song = songs.get(trackNo - 1);
				playlist.add(song);
				return true;
			}
		}
		return false;
	}

	public boolean addToPlayList(String title, LinkedList<Song> playlist) {
		// return true if it exists and successfully added using the title else false
		if (!this.songs.isEmpty()) {
			Song song = findSong(title);
			if (song != null) {
				playlist.add(song);
				return true;
			}
		}
		return false;
	}
}














