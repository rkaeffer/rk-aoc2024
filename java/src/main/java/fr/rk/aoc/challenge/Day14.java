package fr.rk.aoc.challenge;

import java.util.List;
import java.util.stream.Collectors;

public final class Day14 {

    public static long getSafetyScore(List<String> input, int xMax, int yMax, int time) {
        List<Robot> robots = input.stream().map(Robot::new).collect(Collectors.toList());
        robots.forEach(r -> r.calculateFinalPos(time,xMax,yMax));
        long middleX = xMax / 2;
        long middleY = yMax / 2;
        List<Robot> leftUpQuadrant = robots.stream().filter(r -> r.finalY < middleY && r.finalX < middleX).collect(Collectors.toList());
        List<Robot> rightUpQuadrant = robots.stream().filter(r -> r.finalY < middleY && r.finalX > middleX).collect(Collectors.toList());
        List<Robot> leftDownQuadrant = robots.stream().filter(r -> r.finalY > middleY && r.finalX < middleX).collect(Collectors.toList());
        List<Robot> rightDownQuadrant = robots.stream().filter(r -> r.finalY > middleY && r.finalX > middleX).collect(Collectors.toList());
        return (long) leftUpQuadrant.size() * rightUpQuadrant.size() * leftDownQuadrant.size() * rightDownQuadrant.size();
    }

    public static long getNbStepForChristmasTree(List<String> input, int xMax, int yMax) {
        boolean unique = false;
        int time = 101;
        List<Robot> robots = input.stream().map(Robot::new).collect(Collectors.toList());
        while(!unique) {
            int finalTime = time;
            robots.forEach(r -> r.calculateFinalPos(finalTime,xMax,yMax));
            boolean curUnique = true;
            for(int y=0; y<yMax; y++) {
                for(int x=0; x<xMax; x++) {
                    int nbAtPos = getNbRobotAtPos(robots, x, y);
                    if(nbAtPos >= 2) {
                        curUnique = false;
                        break;
                    }
                }
                if(!curUnique) {
                    break;
                }
            }
            if(curUnique) {
                unique=true;
            } else {
                time++;
            }
        }
        return time;
    }

    public static int getNbRobotAtPos(List<Robot>  robots, int x, int y) {
        int nbAtPos = 0;
        for(Robot r : robots) {
            if(r.finalX == x && r.finalY == y) {
                nbAtPos++;
            }
        }
        return nbAtPos;
    }

    static class Robot {
        long initialX;
        long initialY;

        long xVelocity;
        long yVelocity;

        long finalX;
        long finalY;

        public Robot(String robotDesc) {
            String[] desc = robotDesc.split(" ");
            String[] pos = desc[0].split("=")[1].split(",");
            String[] velo = desc[1].split("=")[1].split(",");
            this.initialX = Long.parseLong(pos[0]);
            this.initialY = Long.parseLong(pos[1]);
            this.xVelocity = Long.parseLong(velo[0]);
            this.yVelocity = Long.parseLong(velo[1]);
        }

        public void calculateFinalPos(int time, int xMax, int yMax) {
             this.finalX = (this.initialX + (this.xVelocity * time)) % xMax;
             if(this.finalX < 0) {
                 this.finalX += xMax;
             }
             this.finalY = (this.initialY + (this.yVelocity * time)) % yMax;
             if(this.finalY < 0) {
                 this.finalY += yMax;
             }
        }

    }
}
