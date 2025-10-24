package com.tekpyramid.movietek.Exception;

public class DuplicateRecord extends RuntimeException {
    public DuplicateRecord(String message) {
        super(message);
    }

    DuplicateRecord(){
        super("Aldready Exists");
    }
}
