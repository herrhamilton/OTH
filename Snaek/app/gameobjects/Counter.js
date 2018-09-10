var Counter = function (context, GAME_WIDTH, GAME_HEIGHT) {

    this.context = context;
    this.GAME_WIDTH = GAME_WIDTH;
    this.GAME_HEIGHT = GAME_HEIGHT;
    this.score = 0;
    this.score2 = null;
};

Counter.prototype.draw = function () {

    this.context.fillStyle = "white";
    this.context.font = "" + 0.4*this.GAME_WIDTH/9 + "px Arial";
    
    if (this.score2 == null) {
        this.context.fillText("Score: " + this.score, 0.4*this.GAME_WIDTH/9, 0.4*this.GAME_HEIGHT/9);
        var highscore = 0;

        if (window.localStorage.getItem("Snake_Highscore") != null)
            highscore = window.localStorage.getItem("Snake_Highscore");
        this.context.fillText("Highscore: " + highscore, 4.5*this.GAME_WIDTH/9, 0.4*this.GAME_HEIGHT/9);
    } else {
        this.context.fillText("Greenie Boy: " + this.score, 0.4*this.GAME_WIDTH/9, 0.4*this.GAME_HEIGHT/9);
        this.context.fillText("Madame Purple: " + this.score2, 4.5*this.GAME_WIDTH/9, 0.4*this.GAME_HEIGHT/9);

    }

};

Counter.prototype.increase = function (snake) {

    if (this.score2 == null) {
        this.score += 9;
        if (window.localStorage.getItem("Snake_Highscore") < this.score)
            window.localStorage.setItem("Snake_Highscore", this.score);
    } else if (!snake.isSecondPlayer)
        this.score = snake.elements.length;
    else this.score2 = snake.elements.length;
};
