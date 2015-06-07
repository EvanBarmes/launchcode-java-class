package com.EvanBarmes.JavaTuts;


public class Pyramid {

    private String _pyramid;

    public void Pyramid() {

    }

    public void BuildPyramid(int height) {

        StringBuilder str = new StringBuilder();

        int row = 1;

        while (row <= height) {

            for (int i = 0; i <= height; i++) {

                if (i < height - row) str.append(" ");
                else str.append('#');

            }

            str.append("\n");
            row++;

        }

        _pyramid = str.toString();

    }

    @Override
    public String toString() {
        return _pyramid;
    }


}
