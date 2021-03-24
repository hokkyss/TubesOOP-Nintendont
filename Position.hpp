#ifndef POSITION_HPP
#define POSITION_HPP

class Position{
  private:
    int x;
    int y;
  public:
    Position();
    Position(int x, int y);
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    void moveBy(int x, int y);
    void set(int x, int y);
    bool operator==(Position p);
};

#endif