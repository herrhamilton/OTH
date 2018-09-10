var SnakeSoundPlayer = function() {
    this.music = new Audio();
    this.music.src = "audio/Snake_Music.mp3";
    
    this.gameOver = new Audio();
    this.gameOver.src = "audio/Snake_Game_Over.mp3";
    
    this.eating = new Audio();
    this.eating.src = "audio/Snake_Eating.mp3";
    
    this.mainMenu = new Audio();
    this.mainMenu.src = "audio/Snake_Main_Menu_Music.mp3";
};

