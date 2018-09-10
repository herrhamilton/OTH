/* The snake in the game */
var Snake = function (controls, context, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, color, isSecondPlayer) {

    this.elements = [];
    this.context = context;
    this.GAME_WIDTH = GAME_WIDTH;
    this.GAME_HEIGHT = GAME_HEIGHT;
    this.RASTER_SIZE = RASTER_SIZE;
    this.color = color;

    this.KEY_LEFT = controls[0];
    this.KEY_UP = controls[1];
    this.KEY_RIGHT = controls[2];
    this.KEY_DOWN = controls[3];

    this.direction = this.KEY_UP;
    this.hasDirection = true;
    this.isSecondPlayer = isSecondPlayer;
    // use different colors for the two Snakes
    if (this.isSecondPlayer) {
        for (var i = 0; i < 3; i++) {
            this.addElem(((GAME_WIDTH / RASTER_SIZE) / 2 + 5) * RASTER_SIZE, ((GAME_WIDTH / RASTER_SIZE) / 2) * RASTER_SIZE + i * RASTER_SIZE);
        }

        this.headL = new Image();
        this.headL.src = "images/Snake_HeadL.png";
        this.headR = new Image();
        this.headR.src = "images/Snake_HeadR.png";
        this.headU = new Image();
        this.headU.src = "images/Snake_HeadU.png";
        this.headD = new Image();
        this.headD.src = "images/Snake_HeadD.png";

        this.bodyUD = new Image();
        this.bodyUD.src = "images/Snake_BodyUD.png";
        this.bodyLR = new Image();
        this.bodyLR.src = "images/Snake_BodyLR.png";

        this.tailL = new Image();
        this.tailL.src = "images/Snake_TailL.png";
        this.tailR = new Image();
        this.tailR.src = "images/Snake_TailR.png";
        this.tailU = new Image();
        this.tailU.src = "images/Snake_TailU.png";
        this.tailD = new Image();
        this.tailD.src = "images/Snake_TailD.png";

        this.curveDR = new Image();
        this.curveDR.src = "images/Snake_CurveDR.png";
        this.curveLD = new Image();
        this.curveLD.src = "images/Snake_CurveLD.png";
        this.curveUR = new Image();
        this.curveUR.src = "images/Snake_CurveUR.png";
        this.curveLU = new Image();
        this.curveLU.src = "images/Snake_CurveLU.png";

    } 
    else {
        for (var i = 0; i < 3; i++) {
            this.addElem(((GAME_WIDTH / RASTER_SIZE) / 2 - 5) * RASTER_SIZE, ((GAME_WIDTH / RASTER_SIZE) / 2) * RASTER_SIZE + i * RASTER_SIZE);
        }
        
          this.headL = new Image();
    this.headL.src = "images/Snake2_HeadL.png";
    this.headR = new Image();
    this.headR.src = "images/Snake2_HeadR.png";
    this.headU = new Image();
    this.headU.src = "images/Snake2_HeadU.png";
    this.headD = new Image();
    this.headD.src = "images/Snake2_HeadD.png";

    this.bodyUD = new Image();
    this.bodyUD.src = "images/Snake2_BodyUD.png";
    this.bodyLR = new Image();
    this.bodyLR.src = "images/Snake2_BodyLR.png";

    this.tailL = new Image();
    this.tailL.src = "images/Snake2_TailL.png";
    this.tailR = new Image();
    this.tailR.src = "images/Snake2_TailR.png";
    this.tailU = new Image();
    this.tailU.src = "images/Snake2_TailU.png";
    this.tailD = new Image();
    this.tailD.src = "images/Snake2_TailD.png";

    this.curveDR = new Image();
    this.curveDR.src = "images/Snake2_CurveDR.png";
    this.curveLD = new Image();
    this.curveLD.src = "images/Snake2_CurveLD.png";
    this.curveUR = new Image();
    this.curveUR.src = "images/Snake2_CurveUR.png";
    this.curveLU = new Image();
    this.curveLU.src = "images/Snake2_CurveLU.png";
    }

};

Snake.prototype.draw = function () {
    // head
    switch (this.direction) {
        case this.KEY_LEFT:
            this.context.drawImage(this.headL, this.elements[0].xPos, this.elements[0].yPos, this.RASTER_SIZE, this.RASTER_SIZE);

            break;
        case this.KEY_RIGHT:
            this.context.drawImage(this.headR, this.elements[0].xPos, this.elements[0].yPos, this.RASTER_SIZE, this.RASTER_SIZE);


            break;
        case this.KEY_UP:
            this.context.drawImage(this.headU, this.elements[0].xPos, this.elements[0].yPos, this.RASTER_SIZE, this.RASTER_SIZE);

            break;
        case this.KEY_DOWN:
            this.context.drawImage(this.headD, this.elements[0].xPos, this.elements[0].yPos, this.RASTER_SIZE, this.RASTER_SIZE);

            break;
    }

    //body
    var oldX = this.elements[0].xPos;
    var oldY = this.elements[0].yPos;
    var thisX;
    var thisY;
    if(this.elements.length>1) {
        thisX = this.elements[1].xPos;
        thisY = this.elements[1].yPos;
    }

    for (var i = 1; i < this.elements.length - 1; i++) {
        nextX = this.elements[i + 1].xPos;
        nextY = this.elements[i + 1].yPos;

        if (oldX == thisX) {
            if (oldY > thisY) {
                if (nextX == thisX)

                    this.context.drawImage(this.bodyUD, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);

                else if (nextX < thisX)
                    this.context.drawImage(this.curveLD, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else this.context.drawImage(this.curveDR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
            } else {
                if (nextX == thisX)
                    this.context.drawImage(this.bodyUD, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else if (nextX < thisX)
                    this.context.drawImage(this.curveLU, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else this.context.drawImage(this.curveUR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
            }
        } else {
            if (oldX > thisX) {
                if (nextY == thisY)
                    this.context.drawImage(this.bodyLR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else if (nextY < thisY)
                    this.context.drawImage(this.curveUR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else this.context.drawImage(this.curveDR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
            } else {
                if (nextY == thisY)
                    this.context.drawImage(this.bodyLR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else if (nextY < thisY)
                    this.context.drawImage(this.curveLU, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
                else this.context.drawImage(this.curveLD, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
            }
        }

        oldX = thisX;
        oldY = thisY;
        thisX = nextX;
        thisY = nextY;
    }
    // tail
    if (oldX == thisX) {
        if (oldY > thisY)
            this.context.drawImage(this.tailD, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
        else this.context.drawImage(this.tailU, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);

    } else {
        if (oldX > thisX)
            this.context.drawImage(this.tailR, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
        else this.context.drawImage(this.tailL, thisX, thisY, this.RASTER_SIZE, this.RASTER_SIZE);
    }



    this.hasDirection = false;

};

Snake.prototype.changeDirection = function (direction) {

    if (!this.hasDirection) {

        switch (this.direction) {
            case this.KEY_LEFT:
            case this.KEY_RIGHT:
                if (direction == this.KEY_UP || direction == this.KEY_DOWN) {
                    this.direction = direction;
                    this.hasDirection = true;
                }
                break;
            case this.KEY_UP:
            case this.KEY_DOWN:
                if (direction == this.KEY_LEFT || direction == this.KEY_RIGHT) {
                    this.direction = direction;
                    this.hasDirection = true;
                }
                break;
        }
    }
};

Snake.prototype.addElem = function (xPos, yPos) {
    var elem = new SnakeElement(xPos, yPos);
    this.elements.push(elem);
};

Snake.prototype.getNewPos = function () {

    if (this.direction != null) {
        for (var i = this.elements.length - 1; i > 0; i--) {
            this.elements[i].xPos = this.elements[i - 1].xPos;
            this.elements[i].yPos = this.elements[i - 1].yPos;
        }

        switch (this.direction) {
            case this.KEY_LEFT:
                this.elements[0].xPos -= this.RASTER_SIZE;
                break;
            case this.KEY_RIGHT:
                this.elements[0].xPos += this.RASTER_SIZE;
                break;
            case this.KEY_UP:
                this.elements[0].yPos -= this.RASTER_SIZE;
                break;
            case this.KEY_DOWN:
                this.elements[0].yPos += this.RASTER_SIZE;
                break;
        }
    }
};
// prevent apple from spawning at a snake
Snake.prototype.validateApplePosition = function (xPos, yPos) {

    for (var i = 0; i < this.elements.length; i++) {
        if (this.elements[i].xPos == xPos && this.elements[i].yPos == yPos) {
            return false;
        }
    }
    return true;
};
