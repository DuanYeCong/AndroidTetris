package com.example.androidtetris;

import android.util.Log;

import java.util.Random;

/**
 * Created by duanyecong on 17-9-25.
 */

public class ShapeFactory {
    public Shape shape;

    public int shapes[][][][] =
            {
                    {
                            {
                                    {1, 1, 0, 0},
                                    {1, 0, 0, 0},
                                    {1, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {1, 0, 0, 0},
                                    {1, 1, 1, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {1, 1, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {1, 1, 1, 0},
                                    {0, 0, 1, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },


                    },
                    //方块形状1
                    {
                            {
                                    {1, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {1, 1, 1, 0},
                                    {1, 0, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {1, 0, 0, 0},
                                    {1, 0, 0, 0},
                                    {1, 1, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {0, 0, 1, 0},
                                    {1, 1, 1, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },


                    },
                    //方块形状2
                    {
                            {
                                    {1, 1, 0, 0},
                                    {0, 1, 1, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 1, 0, 0},
                                    {1, 1, 0, 0},
                                    {1, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {1, 0, 0, 0},
                                    {1, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 0, 0, 0},
                            },
                    },
                    //方块形状3

                    {
                            {
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                            },

                            {
                                    {1, 1, 1, 1},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            }


                    },
                    //方块形状4
                    {
                            {
                                    {1, 1, 1, 0},
                                    {0, 1, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {1, 0, 0, 0},
                                    {1, 1, 0, 0},
                                    {1, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {0, 1, 0, 0},
                                    {1, 1, 1, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            },

                            {
                                    {0, 1, 0, 0},
                                    {1, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 0, 0, 0},
                            },

                    },

                    //方块形状5

                    {
                            {
                                    {1, 1, 0, 0},
                                    {1, 1, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            }
                    //方块形状6
                    }

            };
            public Shape getShape(ComeBackAdapter listener){
                Log.d("TAG===>","getShape");
                shape = new Shape(listener, shapes[new Random().nextInt(shapes.length)], 0);
                return shape;
            }
}
