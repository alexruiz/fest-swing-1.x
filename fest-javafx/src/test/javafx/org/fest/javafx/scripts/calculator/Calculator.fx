package org.fest.javafx.scripts.calculator;

import javafx.ext.swing.SwingButton;  
import javafx.stage.Stage;  
import javafx.scene.Scene;  
import javafx.scene.control.TextBox;  
import javafx.scene.layout.*;  
import javafx.scene.paint.Color;  
  
/** 
 * The "stage" for the application 
 */  
Stage {  
    var displayStr:String;  
    title: "Calculator"  
    width: 125  
    height: 165  
    scene: Scene {  
        fill: Color.BLUE  
        content: [  
            VBox {  
                spacing: 5  
                content: [  
                    TextBox {  
                        columns: 9  
                        text: bind displayStr  
                        editable: false  
                        style: "border-radius:20;"  
                    },  
                    VBox {  
                        content: [  
                            HBox {  
                                content: [  
                                    SwingButton {
                                        id: "button7"
                                        text: "7"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}7";  
                                            }  
                                    },  
                                    SwingButton {  
                                        text: "8"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}8";  
                                            }  
                                    },  
                                    SwingButton {  
                                        text: "9"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}9";  
                                            }  
                                    },  
                                ]  
                            },  
                            HBox {  
                                content: [  
                                    SwingButton {
                                        text: "4"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}4";  
                                            }  
                                    },  
                                    SwingButton {  
                                        text: "5"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}5";  
                                            }  
                                    },  
                                    SwingButton {  
                                        text: "6"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}6";  
                                            }  
                                    },  
                                ]  
                            },  
                            HBox {  
                                content: [  
                                    SwingButton {  
                                        text: "1"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}1";  
                                            }  
                                    },  
                                    SwingButton {  
                                        text: "2"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}2";  
                                            }  
                                    },  
                                    SwingButton {  
                                        text: "3"  
                                        action:  
                                            function():Void {  
                                                displayStr = "{displayStr}3";  
                                            }  
                                    },  
                                ]  
                            },  
                        ]  
                    }  
                ]  
            }  
        ]  
    }  
}  