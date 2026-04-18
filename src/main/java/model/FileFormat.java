/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author alina
 */
public enum FileFormat {
    JSON(".json", "JSON"),
    XML(".xml", "XML"),
    YAML(".yaml", "YAML"),
    YML(".yml", "YAML"),
    TXT(".txt", "Text");

    private final String extension;
    private final String displayName;

    FileFormat(String extension, String displayName) {
        this.extension = extension;
        this.displayName = displayName;
    }

    public String getExtension() {
        return extension;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FileFormat fromPath(String filePath) {
        String lowerPath = filePath.toLowerCase();
        for (FileFormat format : values()) {
            if (lowerPath.endsWith(format.extension)) {
                if (format == YML) {
                    return YAML;
                }
                return format;
            }
        }
        throw new IllegalArgumentException(
            "Unsupported file format. Expected .json, .xml, .yaml, .yml or .txt"
        );
    }
}