var Button = function (name, xPos, yPos, width, height) {
    this.name = name;
    this.xPos = xPos;
    this.yPos = yPos;
    this.width = width;
    this.height = height;
};
// check if mouse clicked inside button
Button.prototype.checkPos = function (xPos, yPos) {
    if(xPos > this.xPos && xPos < this.xPos + this.width && yPos > this.yPos && yPos < this.yPos + this.height)
        return true;
        else return false;
};
