package shelf;

public class EmptySpace {

    private int xs;
    private int xe;
    private int ys;
    private int ye;
    final private YShelf shelf;

    EmptySpace(int xs, int xe, int ys, int ye, YShelf shelf) {
        if (xs == xe || ys == ye)
            throw new IllegalArgumentException(" ");
        this.xs = xs;
        this.xe = xe;
        this.ys = ys;
        this.ye = ye;
        this.shelf = shelf;
    }

    int getArea(){
        return getX()*getY();
    }

    public YShelf getShelf() {
        return shelf;
    }

    public int getXs() {
        return xs;
    }

    public void setXs(int xs) {
        this.xs = xs;
    }

    public int getXe() {
        return xe;
    }

    public void setXe(int xe) {
        this.xe = xe;
    }

    public int getYs() {
        return ys;
    }

    public void setYs(int ys) {
        this.ys = ys;
    }

    public int getYe() {
        return ye;
    }

    public void setYe(int ye) {
        this.ye = ye;
    }

    public int getX(){
        return getXe()-getXs();
    }

    public int getY(){
        return getYe()-getYs();
    }
}