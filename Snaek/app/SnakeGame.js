var game = (function () {

    // ============= VARIABLES =========================
    // =================================================
    {
        var privateContext;
        var privateCanvas;

        /* Game Constants */
        var GAME_WIDTH;
        var GAME_HEIGHT;
        var RASTER_SIZE; // i.e. size of snake elements and apples

        var sounds;
        var snake;
        var snake2;
        var apple;
        var counter;
        var appleCount = 0;


        var stages = [STAGE_MAIN = 1, STAGE_SINGLE = 2, STAGE_MULTI = 3, STAGE_GAMEOVER = 4, STAGE_HELP1 = 5, STAGE_HELP2 = 6, STAGE_HELP3 = 7, STAGE_HELP4 = 8];

        var currentStage = 1;

        var CONTROLS_ARROWS = [37, 38, 39, 40];
        var CONTROLS_NUM = [100, 104, 102, 101];
        var CONTROLS_WASD = [65, 87, 68, 83];

        /* Variables and constants to control framerate */
        var FPS = 10; /* change this to change framerate in the game */
        var now;
        var then = Date.now();
        var interval = 1000 / FPS;
        var delta;

        var imgBackground = new Image();
        imgBackground.src = "images/Background.png";
        var imgMainMenu = new Image();
        imgMainMenu.src = "images/Main_Menu.png";
        var imgGameOver = new Image();
        imgGameOver.src = "images/Game_Over.png";
        var imgHelp1 = new Image();
        imgHelp1.src = "images/Help_1.png";
        var imgHelp2 = new Image();
        imgHelp2.src = "images/Help_2.png";
        var imgHelp3 = new Image();
        imgHelp3.src = "images/Help_3.png";
        var imgHelp4 = new Image();
        imgHelp4.src = "images/Help_4.png";

        var iconHelp = new Image();
        iconHelp.src = "images/Help_Icon.png";
        var iconSize = new Image();
        iconSize.src = "images/Size_Icon.png";
        var iconSound = new Image();
        iconSound.src = "images/Sound_Icon.png";
        var iconMuted = new Image();
        iconMuted.src = "images/Muted_Icon.png";
        var iconArrowR = new Image();
        iconArrowR.src = "images/Arrow_Right.png";
        var iconArrowL = new Image();
        iconArrowL.src = "images/Arrow_Left.png";

        var objApple = new Image();
        objApple.src = "images/Apple.png";
        var objStrawberry = new Image();
        objStrawberry.src = "images/Strawberry.png";

        var buttons = [];
    }

    // ============= COLLISIONS ========================
    // =================================================
    {
        function privateCollisionWithApple(snake) {
            if (snake.elements[0].xPos == apple.xPos &&
                snake.elements[0].yPos == apple.yPos) {
                sounds.eating.play();

                snake.addElem(-RASTER_SIZE, -RASTER_SIZE);

                counter.increase(snake);
                //increase speed every 3 apples
                appleCount += 1;
                if (appleCount == 2)
                    apple = new Apple(privateContext, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, snake, snake2, objStrawberry);
                else
                    apple = new Apple(privateContext, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, snake, snake2, objApple);

                if (appleCount == 3) {
                    if (interval * FPS >= 200)
                        interval = ((interval * FPS) - 50) / FPS;

                    appleCount = 0;
                    return true;
                }
            } else return false;
        }

        function privateCollisionWithSnake(snake1, snake2) {

            var xPosHead = snake1.elements[0].xPos;
            var yPosHead = snake1.elements[0].yPos;

            for (var i = 1; i < snake2.elements.length; i++) {
                if (xPosHead == snake2.elements[i].xPos &&
                    yPosHead == snake2.elements[i].yPos) {

                    if (snake1 != snake2) {
                        console.log("cutting Snake at pos: " + i);
                        privateCutSnake(snake1, snake2, i);
                    }
                    return true;
                }
            }
            return false;
        }

        function privateCollisionWithBorder(snake) {

            var xPosHead = snake.elements[0].xPos;
            var yPosHead = snake.elements[0].yPos;

            if (xPosHead < 0 || xPosHead >= GAME_WIDTH ||
                yPosHead < 0 || yPosHead >= GAME_HEIGHT)
                return true;
            else return false;
        }
    }

    // ============= MENUS =============================
    // =================================================
    {
        function privateHelp() {
            switch (currentStage) {
                case STAGE_HELP1:
                    privateContext.drawImage(imgHelp1, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                    break;
                case STAGE_HELP2:
                    privateContext.drawImage(imgHelp2, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                    break;
                case STAGE_HELP3:
                    privateContext.drawImage(imgHelp3, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                    break;
                case STAGE_HELP4:
                    privateContext.drawImage(imgHelp4, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                    break;
            }
            privateContext.drawImage(iconArrowR, 7 * GAME_WIDTH / 9, 7 * GAME_HEIGHT / 9, GAME_WIDTH / 9, GAME_HEIGHT / 9);
        }

        function privateMainMenu() {
            currentStage = STAGE_MAIN;
            sounds.music.pause();
            sounds.mainMenu.play();

            privateContext.drawImage(imgMainMenu, 0, 0, GAME_WIDTH, GAME_HEIGHT);
            privateContext.drawImage(iconHelp, 0.8 * GAME_WIDTH, 0.4 * GAME_HEIGHT, 0.1 * GAME_WIDTH, 0.1 * GAME_HEIGHT);

            if (sounds.mainMenu.muted == false)
                privateContext.drawImage(iconSound, 0.8 * GAME_WIDTH, 0.4 * GAME_HEIGHT + 0.2 * GAME_HEIGHT, 0.1 * GAME_WIDTH, 0.1 * GAME_HEIGHT);
            else privateContext.drawImage(iconMuted, 0.8 * GAME_WIDTH, 0.4 * GAME_HEIGHT + 0.2 * GAME_HEIGHT, 0.1 * GAME_WIDTH, 0.1 * GAME_HEIGHT);
        }

        function privateGameOver() {

            privateContext.drawImage(imgBackground, 0, 0, GAME_WIDTH, GAME_HEIGHT);
            apple.draw();
            snake.draw();
            if (snake2 != null)
                snake2.draw();

            counter.draw();

            privateContext.fillStyle = "white";
            privateContext.font = "" + 0.6 * GAME_WIDTH / 9 + "px Arial";
            privateContext.fillText("GAME OVER", 0.3 * GAME_WIDTH, 2 * GAME_HEIGHT / 9);
            privateContext.font = "" + 0.3 * GAME_WIDTH / 9 + "px Arial";

            if (snake2 != null) {
                if (counter.score > counter.score2)
                    privateContext.fillText("Madame Purple wins!", 0.2 * GAME_WIDTH, 2.5 * GAME_HEIGHT / 9);
                else if (counter.score2 > counter.score)
                    privateContext.fillText("Greenie Boy wins!", 0.2 * GAME_WIDTH, 2.5 * GAME_HEIGHT / 9);
                else privateContext.fillText("It's a tie!", 0.2 * GAME_WIDTH, 2.5 * GAME_HEIGHT / 9);
            }
            privateContext.fillText("press ESC to play again", 0.2 * GAME_WIDTH, GAME_HEIGHT / 9);

            privateContext.drawImage(iconArrowL, GAME_WIDTH / 9, 7 * GAME_HEIGHT / 9, 2 * GAME_WIDTH / 9, 2 * GAME_HEIGHT / 9);
        }
    }

    // ============= OTHER METHODS =====================
    // =================================================
    {

        function privateDraw() {
            window.requestAnimationFrame(privateDraw);

            now = Date.now();
            delta = now - then;

            if (delta > interval) {
                then = now - (delta % interval);

                // draw and check collisions here...
                privateContext.clearRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                switch (currentStage) {
                    case STAGE_MAIN:
                        sounds.mainMenu.play();
                        privateMainMenu();
                        break;

                    case STAGE_SINGLE:
                        privateContext.drawImage(imgBackground, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                        sounds.music.play();

                        if (!privateCollisionWithBorder(snake) && !privateCollisionWithSnake(snake, snake)) {
                            privateCollisionWithApple(snake);
                            snake.getNewPos();

                        } else {
                            sounds.gameOver.play();
                            currentStage = STAGE_GAMEOVER;
                        }
                        counter.draw();
                        apple.draw();
                        snake.draw();

                        break;

                    case STAGE_MULTI:
                        privateContext.drawImage(imgBackground, 0, 0, GAME_WIDTH, GAME_HEIGHT);
                        sounds.music.play();

                        var snake1collided = (privateCollisionWithBorder(snake) || privateCollisionWithSnake(snake, snake));
                        var snake2collided = (privateCollisionWithBorder(snake2) || privateCollisionWithSnake(snake2, snake2));

                        if (snake1collided && snake2collided) {
                            sounds.gameOver.play();
                            currentStage = STAGE_GAMEOVER;
                        } else if ((snake1collided && (counter.score2 > counter.score)) || (snake2collided && (counter.score > counter.score2))) {
                            sounds.gameOver.play();
                            currentStage = STAGE_GAMEOVER;
                        }

                        if (!snake1collided) {
                            privateCollisionWithApple(snake);
                            snake.getNewPos();
                        }
                        if (!snake2collided) {
                            privateCollisionWithApple(snake2);
                            snake2.getNewPos();
                        }

                        privateCollisionWithSnake(snake, snake2);
                        privateCollisionWithSnake(snake2, snake);

                        counter.draw();
                        apple.draw();
                        snake.draw();
                        snake2.draw();
                        break;

                    case STAGE_GAMEOVER:
                        privateGameOver();
                        break;

                    case STAGE_HELP1:
                    case STAGE_HELP2:
                    case STAGE_HELP3:
                    case STAGE_HELP4:
                        sounds.mainMenu.play();
                        privateHelp();
                }
            }
        }

        function privateCutSnake(snake1, snake2, pos) {
            sounds.eating.play();
            var xPos = snake1.elements[0].xPos;
            var yPos = snake1.elements[0].yPos;
            
            snake2.elements = snake2.elements.slice(0, pos);
            
            if (!privateCollisionWithBorder(snake2)) {
                for (var i = 0; i < snake2.elements.length - pos; i++)
                    snake1.addElem(-(i + 1) * RASTER_SIZE, -(i + 1) * RASTER_SIZE);
                counter.increase(snake2);
                counter.increase(snake1);
            }

          //  else if (!privateCollisionWithBorder(snake1)) {
               

              //      counter.increase(snake2);
          //  }
        }

        function privateStartSingleplayer() {

            sounds.mainMenu.pause();
            currentStage = STAGE_SINGLE;
            interval = 1000 / FPS;

            snake = new Snake(CONTROLS_WASD, privateContext, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, "green", false);
            snake2 = null;
            apple = new Apple(privateContext, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, snake, null, objApple);
            counter = new Counter(privateContext, GAME_WIDTH, GAME_HEIGHT);

            window.requestAnimationFrame(privateDraw);
        }

        function privateStartMultiplayer() {

            privateStartSingleplayer();
            currentStage = STAGE_MULTI;
            counter.score = 3;
            counter.score2 = 3;
            snake2 = new Snake(CONTROLS_ARROWS, privateContext, GAME_WIDTH, GAME_HEIGHT, RASTER_SIZE, "rgba(226,0,116,1)", true);

            window.requestAnimationFrame(privateDraw);
        }

        function privateKeyPressed(keyEvent) {
            keyEvent.preventDefault();
            if (keyEvent.keyCode == 27) { // ESC
                currentStage = STAGE_MAIN;
            } else if (keyEvent.keyCode == 13) // RETURN
                privateStartSingleplayer();
            else if (keyEvent.keyCode == 8) // BACKSPACE
                privateStartMultiplayer();
            else if (keyEvent.keyCode == 67) { // C
                toggleMute();
            } else {
                if (currentStage != STAGE_GAMEOVER) {
                    snake.changeDirection(keyEvent.keyCode);
                    if (currentStage == STAGE_MULTI)
                        snake2.changeDirection(keyEvent.keyCode);
                }
            }
        }

        function toggleMute() {
            sounds.gameOver.muted = !sounds.gameOver.muted;
            sounds.eating.muted = !sounds.eating.muted;
            sounds.music.muted = !sounds.music.muted;
            sounds.mainMenu.muted = !sounds.mainMenu.muted;
        }
    }

    // ============= INITIALIZATION ====================
    // =================================================
    {

        function privateInitButtons() {

            buttons[0] = new Button("StartSingle", GAME_WIDTH / 9, 4 * GAME_HEIGHT / 9, 5 * GAME_WIDTH / 9, 1.4 * GAME_HEIGHT / 9);
            buttons[1] = new Button("StartMulti", 1.2 * GAME_WIDTH / 9, 5.5 * GAME_HEIGHT / 9, 5.3 * GAME_WIDTH / 9, 1.5 * GAME_HEIGHT / 9);
            buttons[2] = new Button("Help", 0.8 * GAME_WIDTH, 0.4 * GAME_HEIGHT, 0.1 * GAME_WIDTH, 0.1 * GAME_HEIGHT);
            buttons[3] = new Button("Mute", 0.8 * GAME_WIDTH, 0.6 * GAME_HEIGHT, 0.1 * GAME_WIDTH, 0.1 * GAME_HEIGHT);
            buttons[4] = new Button("ArrowR", 7 * GAME_WIDTH / 9, 7 * GAME_HEIGHT / 9, 0.1 * GAME_WIDTH, 0.1 * GAME_WIDTH);
            buttons[5] = new Button("ArrowL", GAME_WIDTH / 9, 7 * GAME_HEIGHT / 9, 2 * GAME_WIDTH, 2 * GAME_WIDTH);
        }

        function privateGetKeyStrokes() {
            privateCanvas.setAttribute('tabindex', '0');
            privateCanvas.focus();
            privateCanvas.addEventListener("keydown", privateKeyPressed, false);
            privateCanvas.addEventListener("click", privateMouseClicked, false);
        }

        function privateMouseDirection(x, y) {
            var direction;
            var originX = GAME_WIDTH / 2;
            var originY = GAME_HEIGHT / 2;

            x -= originX;
            y -= originY;

            if (x > 0 && y > 0) {
                if (x > y)
                    direction = snake.KEY_RIGHT;
                else if (x < y)
                    direction = snake.KEY_DOWN;
            }
            if (x < 0 && y < 0) {
                if (x < y)
                    direction = snake.KEY_LEFT;
                else
                    direction = snake.KEY_UP;
            }
            if (x > 0 && y < 0) {
                if (x < -y)
                    direction = snake.KEY_UP;
                else direction = snake.KEY_RIGHT;
            }
            if (x < 0 && y > 0) {
                if (-x < y)
                    direction = snake.KEY_DOWN;
                else direction = snake.KEY_LEFT;
            }

            snake.changeDirection(direction);

        }

        function privateMouseClicked() {
            var bound = privateCanvas.getBoundingClientRect();
            var x = event.clientX - bound.left - privateCanvas.clientLeft;
            var y = event.clientY - bound.top - privateCanvas.clientTop;

            privateContext.filLStyle = "red";
            privateContext.fillRect(x, y, 10, 10);

            if (currentStage == STAGE_MAIN) {
                if (buttons[0].checkPos(x, y))
                    privateStartSingleplayer();
                else if (buttons[1].checkPos(x, y))
                    privateStartMultiplayer();
                else if (buttons[2].checkPos(x, y))
                    currentStage = STAGE_HELP1;
                else if (buttons[3].checkPos(x, y))
                    toggleMute();
            } else if (currentStage >= STAGE_HELP1 && currentStage < STAGE_HELP4) {
                if (buttons[4].checkPos(x, y))
                    currentStage++;
            } else if (currentStage == STAGE_HELP4) {
                if (buttons[4].checkPos(x, y))
                    currentStage = STAGE_MAIN;
            } else if (currentStage == STAGE_GAMEOVER) {
                if (buttons[5].checkPos(x, y))
                    currentStage = STAGE_MAIN;
            } else if (currentStage == STAGE_SINGLE || currentStage == STAGE_MULTI)
                privateMouseDirection(x, y);
        }

        function privateSetContext(canvas) {
            privateCanvas = canvas;
            privateContext = canvas.getContext("2d");
        }

        function publicInit(canvas) {
            GAME_HEIGHT = canvas.height;
            GAME_WIDTH = canvas.width;
            RASTER_SIZE = GAME_WIDTH / 30;

            privateSetContext(canvas);
            privateGetKeyStrokes();
            privateInitButtons();

            sounds = new SnakeSoundPlayer();

            privateDraw();
        }

        return {
            init: publicInit
        };
    }
})();
