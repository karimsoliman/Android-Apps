package com.example.android.miwok;

public class word {
    private String miwokTranslation;
    private String defaultTranslation;
    private int ImageResourceId = NoImageProvided;
    private int soundResourceId;
    private static final int NoImageProvided = -1;

    public word(String miwokTranslation, String defaultTranslation, int soundId){
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.soundResourceId = soundId;
    }

    public word (String miwokTranslation, String defaultTranslation, int soundId, int imageId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.ImageResourceId = imageId;
        this.soundResourceId = soundId;
    }

    public String getMiwokTranslation(){
        return this.miwokTranslation;
    }

    public String getDefaultTranslation(){
        return this.defaultTranslation;
    }

    public int getImageResourceId() { return this.ImageResourceId; }

    public boolean hasImage(){
        return ImageResourceId != NoImageProvided;
    }

    public int getSoundResourceId() { return this.soundResourceId;}

    @Override
    public String toString(){
        return "word:{ " +
                "MiwokTranslation: " + miwokTranslation + "/ " +
                "EnglishTranslation: " + defaultTranslation + "/ "+
                "ImageResourceId: " + ImageResourceId + "/ " +
                "soundResourceId: " + soundResourceId + " }";
    }
}
