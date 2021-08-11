package com.serbanscorteanu.main;

import java.util.ArrayList;
import java.util.LinkedList;

import com.serbanscorteanu.classes.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Album> albums = new ArrayList<>();

		Album album = new Album("Nightflight to Venus", "Boney M");
		album.addSong("Nightflight to Venus", 4.46);
		album.addSong("Rasputin", 5.57);
		album.addSong("Painter Man", 3.1);
		album.addSong("He Was a Steppenwolf", 6.51);
		album.addSong("King of the Road", 2.35);
		album.addSong("Rivers of Babylon", 4.18);
		album.addSong("Voodoonight", 3.31);
		album.addSong("Brown Girl in the Ring", 4.02);
		album.addSong("Never Change Lovers in the Middle of the Night", 5.32);
		album.addSong("Heart of Gold", 4.00);
		albums.add(album);

		album = new Album("The Best of ABBA", "ABBA");
		album.addSong("Waterloo", 2.41);
		album.addSong("Ring Ring", 3.01);
		album.addSong("Honey, Honey", 2.52);
		album.addSong("Mama Mia", 3.32);
		album.addSong("People Need Love", 2.40);
		album.addSong("Nina, Pretty Ballerina", 2.50);
		album.addSong("I Do, I Do, I Do, I Do, I Do", 3.15);
		album.addSong("SOS", 3.22);
		album.addSong("Dance (While the Music Still Goes On)", 3.12);
		album.addSong("Bang-A-Boomerang)", 3.00);
		album.addSong("Hast Manana", 3.05);
		album.addSong("So Long", 3.06);
		albums.add(album);

		LinkedList<Song> playlist = new LinkedList<>();
		albums.get(0).addToPlayList("Rasputin", playlist);
		albums.get(0).addToPlayList("Rivers of Babylon", playlist);
		
		// the only one not in the album yet
		System.out.println(albums.get(0).addToPlayList("Brown Girl in the Ring", playlist)); 
		
		System.out.println(albums.get(0).addToPlayList(3, playlist));
		
		System.out.println(albums.get(1).addToPlayList(1, playlist));
		System.out.println(albums.get(1).addToPlayList(4, playlist));
		System.out.println(albums.get(1).addToPlayList(13, playlist)); // no track with this number

		for (Song song : playlist) {
			System.out.println(song.toString());
		}
	}

}
