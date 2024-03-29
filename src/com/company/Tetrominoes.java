package com.company;

public class Tetrominoes {
    public static final int[][][] I ={
            {
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
            },
            {
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
            },
            {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
            },
            {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
            }
    };

    public static final int[][][] O ={
            {
                    {0, 0, 0, 0},
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
            },
    };

    public static final int[][][] J ={
            {
                    {1, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0},
            },
            {
                    {0, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0},
            },
            {
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 1},
            },
            {
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 0},
            }
    };

    public static final int[][][] L ={
            {
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0},
            },
            {
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 1},
            },
            {
                    {0, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0},
            },
            {
                    {1, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0},
            }
    };

    public static final int[][][] T ={
            {
                    {0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0},
            },
            {
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 1, 0},
            },
            {
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 1, 0},
            },
            {
                    {0, 1, 0},
                    {1, 1, 0},
                    {0, 1, 0},
            }
    };

    public static final int[][][] S ={
            {
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0},
            },
            {
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 1},
            },
            {
                    {0, 0, 0},
                    {0, 1, 1},
                    {1, 1, 0},
            },
            {
                    {1, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0},
            }
    };

    public static final int[][][] Z ={
            {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0},
            },
            {
                    {0, 0, 1},
                    {0, 1, 1},
                    {0, 1, 0},
            },
            {
                    {0, 0, 0},
                    {1, 1, 0},
                    {0, 1, 1},
            },
            {
                    {0, 1, 0},
                    {1, 1, 0},
                    {1, 0, 0},
            }
    };


    public int coords[][][];

    public Tetrominoes() {
        this.coords = new int[][][]{{{}}};
    }

    public Tetrominoes(int[][][] coords) {
        this.coords = coords;
    }
}
