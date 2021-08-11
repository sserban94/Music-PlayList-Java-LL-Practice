package com.serbanscorteanu.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

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

//		for (Song song : playlist) {
//			System.out.println(song.toString());
//		}

		// Create menu of options - Quit, Skip forward, Skip backwards, Replay current,
		// Remove current song from pl
		// List songs in playlist
		// Optional - remove current song from playList

		printPlaylist(playlist);
		System.out.println();
		play(playlist);

	}

	public static void printPlaylist(LinkedList<Song> playList) {
		Iterator<Song> playlistIterator = playList.iterator();
		while (playlistIterator.hasNext()) {
			Song song = playlistIterator.next();
			System.out.println(song.toString());
		}
	}

	public static void play(LinkedList<Song> playList) {
		// need to get input from user => need scanner
		Scanner scanner = new Scanner(System.in);
		boolean quit = false; // use for the while loop - while quit is false show the menu

		// this will keep track of the position of the iterator
		// remember the iterator is always between elements
		// REMEMBER LL Cycle Problem & Floyd's Tortoise and Hare Algorithm
		boolean goingForward = true;

		// create the iterator
		ListIterator<Song> playlistIterator = playList.listIterator();

		// check if there are any songs
		if (playList.isEmpty()) {
			System.out.println("Error. No songs in the playlist");
		} else {
			System.out.println("Now playing " + playlistIterator.next()); // this will start playback
			printMenu();
		}

		// start creating the actual functionality of the menu
		while (!quit) {
			while (true) {
				// make sure the user isn't trying to force an error
				if (scanner.hasNextInt()) {
					int option = scanner.nextInt();
					scanner.nextLine(); // avoid bugs by skipping line
					switch (option) {
					case 0:
						System.out.println("Music playback is over");
						quit = true;
						break;
					case 1:
						if (!goingForward) { // check if playback went backwards the previous time
							if (playlistIterator.hasNext()) { // if it's not the end of the playlist (cursor after last
																// elem)
								playlistIterator.next(); // skip one element
								goingForward = true;
							}
						}
						if (playlistIterator.hasNext()) {
							System.out.println("Now playing " + playlistIterator.next().getTitle());
						} else {
							System.out.println("Reached the end of the playlist");
							goingForward = false; // if left true it would skip 1 when going back
						}
						break;
					case 2:
						if (goingForward) { // check if playback went forward last time
							if (playlistIterator.hasPrevious()) {
								playlistIterator.previous();
								goingForward = false;
							}
						}
						if (playlistIterator.hasPrevious()) {
							System.out.println("Now playing " + playlistIterator.previous().getTitle());
						} else {
							System.out.println("Reached the beginning of the playlist");
							goingForward = true;
						}
						break;
					case 3:
						if (goingForward) {
							if (playlistIterator.hasPrevious()) {
								System.out.println("Now playing " + playlistIterator.previous().getTitle());
								goingForward = false;
							} else {
								System.out.println("Now playing " + playlistIterator.next().getTitle());
								goingForward = true;
							}
						}
						else {
							if (playlistIterator.hasNext()) {
								System.out.println("Now playing " + playlistIterator.next().getTitle());
								goingForward = true;
							} else {
								System.out.println("Now playing " + playlistIterator.previous().getTitle());
								goingForward = false;
							}
						}
						break;
					case 4:
						if (goingForward) {
							if (playlistIterator.hasPrevious()) {
								System.out.println("Deleting: " + playlistIterator.previous().getTitle());
								playlistIterator.remove();
								System.out.println("Done");
								goingForward = false;
							} else {
								System.out.println("Deleting: " + playlistIterator.next().getTitle());
								playlistIterator.remove();
								goingForward = true;
							}
						}
						else {
							if (playlistIterator.hasNext()) {
								System.out.println("Deleting " + playlistIterator.next().getTitle());
								playlistIterator.remove();
								System.out.println("Done");
								goingForward = true;
							} else {
								System.out.println("Now playing " + playlistIterator.previous().getTitle());
								playlistIterator.remove();
								System.out.println("Done");
								goingForward = false;
							}
						}
						break;
					case 5:
						System.out.println("Songs in playlist: ");
						while (playlistIterator.hasNext()) {
							System.out.println(playlistIterator.next());
							goingForward = true;
						}
						break;
					case 6:
						printMenu();
						break;
					}
				} else {
					scanner.nextLine();
					System.out.println("Only digits from 0 - 6 accepted");
					continue;
				}
			}
		}

	}

	public static void printMenu() {
		System.out.println("Available actions:");
		System.out.println("press ");
		System.out.println("0 - to quit");
		System.out.println("1 - to skip forward");
		System.out.println("2 - to skip backwards");
		System.out.println("3 - to replay current song");
		System.out.println("4 - to remove current song");
		System.out.println("5 - to show playlist");
		System.out.println("6 - to show menu");
	}

}
