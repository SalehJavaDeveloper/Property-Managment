package com.example.property.enumuration;

public enum MediaFileType {
    PNG("png"),
    IMG("img"),
    JPG("jpg"),
    JPEG("jpeg");

    private final String extension;

    MediaFileType(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
