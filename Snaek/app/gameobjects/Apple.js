/* An Apple in the game */
var Apple = function (context, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, snake1, snake2, img) {

    this.context = context;
    this.GAME_WIDTH = GAME_WIDTH;
    this.GAME_HEIGHT = GAME_HEIGHT;
    this.RASTER_SIZE = RASTER_SIZE;
    this.img = img;

        this.xPos = this.getRandPos(GAME_WIDTH);
        this.yPos = this.getRandPos(GAME_HEIGHT);

    
    this.validatePos(snake1);
    if(snake2 != null)
        this.validatePos(snake2);
        
};

Apple.prototype.getRandPos = function (axis) {
    return Math.floor((Math.random() * axis) / this.RASTER_SIZE) * this.RASTER_SIZE;
}

Apple.prototype.draw = function () {
    this.context.drawImage(this.img, this.xPos, this.yPos, this.RASTER_SIZE, this.RASTER_SIZE);
};

Apple.prototype.validatePos = function (snake) {
    while(!(snake.validateApplePosition(this.xPos, this.yPos))) {
         this.xPos = this.getRandPos(this.GAME_WIDTH);
        this.yPos = this.getRandPos(this.GAME_HEIGHT);
    }
};