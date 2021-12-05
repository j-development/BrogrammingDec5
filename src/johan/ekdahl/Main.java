package johan.ekdahl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int n = 500;
        String[] inputArray = new String[500];
        List<Lines> validLines = new ArrayList<>();



        try {
            BufferedReader BF = new BufferedReader(new FileReader("input/text.txt"));
            String line;
            int i = 0;
            while ((line = BF.readLine()) != null) {
                inputArray[i++] = line;
            }
        }
        catch (Exception e){
            e.printStackTrace();
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


        for (Lines validLine : validLines) {
            if (validLine.x1 == validLine.x2) {
                for (int j = Math.min(validLine.y1, validLine.y2); j < (Math.max(validLine.y1, validLine.y2) + 1); j++) {
                    matrix[validLine.x2][j] += 1;
                }
            } else if (validLine.y1 == validLine.y2) {
                for (int j = Math.min(validLine.x1, validLine.x2); j < (Math.max(validLine.x1, validLine.x2) + 1); j++) {
                    matrix[j][validLine.y2] += 1;
                }
            } else {
                if (validLine.x1 < validLine.x2 && validLine.y1 < validLine.y2) {
                    int k = validLine.y1;
                    for (int j = validLine.x1; j < (validLine.x2 + 1); j++) {
                        matrix[j][k] += 1;
                        k++;
                    }
                } else if (validLine.x1 > validLine.x2 && validLine.y1 > validLine.y2) {
                    int k = validLine.y1;
                    for (int j = validLine.x1; j > (validLine.x2 - 1); j--) {
                        matrix[j][k] += 1;
                        k--;
                    }
                } else if (validLine.x1 > validLine.x2 && validLine.y1 < validLine.y2) {
                    int k = validLine.y1;
                    for (int j = validLine.x1; j > (validLine.x2 - 1); j--) {
                        matrix[j][k] += 1;
                        k++;
                    }
                } else if (validLine.x1 < validLine.x2 && validLine.y1 > validLine.y2) {
                    int k = validLine.y1;
                    for (int j = validLine.x1; j < (validLine.x2 + 1); j++) {
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

