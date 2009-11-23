package org.fest.javafx.scripts.audioconfig;

import javafx.scene.Scene;

/**
 * The model class that the AudioConfigMain.fx script uses
 */
public class AudioConfigModel {
    /**
    * The minimum audio volume in decibels
     */
    public var minDecibels:Integer = 0;

    /**
    * The maximum audio volume in decibels
     */
    public var maxDecibels:Integer = 160;

    /**
    * The selected audio volume in decibels
     */
    public var selectedDecibels:Integer = 0;

    /**
    * Indicates whether audio is muted
     */
    public var muting:Boolean = false;


    /**
    * List of some musical genres
     */
    public var genres = [
        "Chamber",
        "Country",
        "Cowbell",
        "Metal",
        "Polka",
        "Rock"
    ];

    /**
    * Index of the selected genre
     */
    public var selectedGenreIndex:Integer = 0 on replace {
        if (genres[selectedGenreIndex] == "Chamber") {
            selectedDecibels = 80;
        }
        else
        if (genres[selectedGenreIndex] == "Country") {
            selectedDecibels = 100;
        }
        else
        if (genres[selectedGenreIndex] == "Cowbell") {
            selectedDecibels = 150;
        }
        else
        if (genres[selectedGenreIndex] == "Metal") {
            selectedDecibels = 140;
        }
        else
        if (genres[selectedGenreIndex] == "Polka") {
            selectedDecibels = 120;
        }
        else
        if (genres[selectedGenreIndex] == "Rock") {
            selectedDecibels = 130;
        }
  };
}
 