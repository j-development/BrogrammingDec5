package johan.ekdahl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int n = 500;
        String[] inputArray = new String[500];
        List<Lines> validLines = new ArrayList<Lines>();

        Scanner scan = new Scanner(System.in);

        for(int i = 0; i < n; i++){
            inputArray[i] = scan.nextLine();
        }


        for(int i = 0; i < n; i++){
            String[] splitArrow = inputArray[i].split(" -> ");

            String[] firstCoordinates = splitArrow[0].split(",");
            String[] secondCoordinates = splitArrow[1].split(",");

            int[] coordinates = new int[]{Integer.parseInt(firstCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(secondCoordinates[1])};

            if (coordinates[0] == coordinates[2] || coordinates[1] == coordinates[3]) {
                validLines.add(new Lines(coordinates[0],coordinates[1],coordinates[2],coordinates[3]));
            }
            else if ((Math.max(coordinates[0], coordinates[2]) - Math.min(coordinates[0], coordinates[2])) == (Math.max(coordinates[1], coordinates[3]) - Math.min(coordinates[1], coordinates[3]))) {
                validLines.add(new Lines(coordinates[0],coordinates[1],coordinates[2],coordinates[3]));
            }
        }

        int[][] matrix = new int[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                matrix[i][j] = 0;
            }
        }


        for (int i = 0; i < validLines.size(); i++) {
            if(validLines.get(i).x1 == validLines.get(i).x2){
                for (int j = Math.min(validLines.get(i).y1, validLines.get(i).y2); j < (Math.max(validLines.get(i).y1, validLines.get(i).y2)+1); j++) {
                    matrix[validLines.get(i).x2][j] += 1;
                }
            }
            else if(validLines.get(i).y1 == validLines.get(i).y2){
                for (int j = Math.min(validLines.get(i).x1, validLines.get(i).x2); j < (Math.max(validLines.get(i).x1, validLines.get(i).x2)+1); j++) {
                    matrix[j][validLines.get(i).y2] += 1;
                }
            }
            else{
                if (validLines.get(i).x1 < validLines.get(i).x2 && validLines.get(i).y1 < validLines.get(i).y2) {
                    int k = validLines.get(i).y1;
                    for (int j = validLines.get(i).x1; j < (validLines.get(i).x2+1); j++){
                        matrix[j][k] += 1;
                        k++;
                    }
                }
                else if (validLines.get(i).x1 > validLines.get(i).x2 && validLines.get(i).y1 > validLines.get(i).y2) {
                    int k = validLines.get(i).y1;
                    for (int j = validLines.get(i).x1; j > (validLines.get(i).x2-1); j--){
                        matrix[j][k] += 1;
                        k--;
                    }
                }
                else if (validLines.get(i).x1 > validLines.get(i).x2 && validLines.get(i).y1 < validLines.get(i).y2) {
                    int k = validLines.get(i).y1;
                    for (int j = validLines.get(i).x1; j > (validLines.get(i).x2-1); j--){
                        matrix[j][k] += 1;
                        k++;
                    }
                }
                else if (validLines.get(i).x1 < validLines.get(i).x2 && validLines.get(i).y1 > validLines.get(i).y2) {
                    int k = validLines.get(i).y1;
                    for (int j = validLines.get(i).x1; j < (validLines.get(i).x2+1); j++){
                        matrix[j][k] += 1;
                        k--;
                    }
                }


            }
        }


        int counter = 0;
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                if (matrix[i][j] >= 2) {
                    counter++;
                }
            }
        }

        System.out.println(counter);

    }





    }

