package org.fest.javafx.scripts.audioconfig;

import javafx.ext.swing.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.*;

import org.fest.javafx.scripts.audioconfig.AudioConfigModel;

Stage {
  var acModel = AudioConfigModel {
    selectedDecibels: 35
  }
  title: "Audio"
  scene: Scene {
    content: [
      Rectangle {
        x: 0
        y: 0
        width: 320
        height: 45
        fill: LinearGradient {
          startX: 0.0
          startY: 0.0
          endX: 0.0
          endY: 1.0
          stops: [
            Stop {
              color: Color.web("0xAEBBCC")
              offset: 0.0
            },
            Stop {
              color: Color.web("0x6D84A3")
              offset: 1.0
            },
          ]
        }
      },
      Text {
        translateX: 65
        translateY: 12
        textOrigin: TextOrigin.TOP
        fill: Color.WHITE
        content: "Audio Configuration"
        font: Font {
          name: "Arial Bold"
          size: 20
        }
      },
      Rectangle {
        x: 0
        y: 43
        width: 320
        height: 300
        fill: Color.rgb(199, 206, 213)
      },
      Rectangle {
        x: 9
        y: 54
        width: 300
        height: 130
        arcWidth: 20
        arcHeight: 20
        fill: Color.color(1.0, 1.0, 1.0)
        stroke: Color.color(0.66, 0.67, 0.69)
      },
      Text {
        translateX: 18
        translateY: 69
        textOrigin: TextOrigin.TOP
        fill: Color.web("0x131021")
        content: bind "{acModel.selectedDecibels} dB"
        font: Font {
          name: "Arial Bold"
          size: 18
        }
      },
      SwingSlider {
        translateX: 120
        translateY: 69
        width: 175
        enabled: bind not acModel.muting
        minimum: bind acModel.minDecibels
        maximum: bind acModel.maxDecibels
        value: bind acModel.selectedDecibels with inverse
      },
      Line {
        startX: 9
        startY: 97
        endX: 309
        endY: 97
        stroke: Color.color(0.66, 0.67, 0.69)
      },
      Text {
        translateX: 18
        translateY: 113
        textOrigin: TextOrigin.TOP
        fill: Color.web("0x131021")
        content: "Muting"
        font: Font {
          name: "Arial Bold"
          size: 18
        }
      },
      SwingCheckBox {
        translateX: 280
        translateY: 113
        selected: bind acModel.muting with inverse
      },
      Line {
        startX: 9
        startY: 141
        endX: 309
        endY: 141
        stroke: Color.color(0.66, 0.67, 0.69)
      },
      Text {
        translateX: 18
        translateY: 157
        textOrigin: TextOrigin.TOP
        fill: Color.web("0x131021")
        content: "Genre"
        font: Font {
          name: "Arial Bold"
          size: 18
        }
      },
      SwingComboBox {
        translateX: 204
        translateY: 148
        width: 93
        items: bind for (genre in acModel.genres) {
          SwingComboBoxItem {
            text: genre
          }
        }
        selectedIndex: bind acModel.selectedGenreIndex with inverse
      }
    ]
  }
}